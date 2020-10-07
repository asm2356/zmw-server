package com.iflytek.search.service.config;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author AZhao
 */
@Component
public class ESClient {
    private Logger logger = LoggerFactory.getLogger(ESClient.class);
    private RestHighLevelClient client = ESConfig.createClient();

    public int insertDocument(String index, String type, String id, String json) {
        int result = -1;
        //创建索引请求
        IndexRequest request = new IndexRequest(index, type, id);
        request.source(json, XContentType.JSON);
        //发送请求
        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                logger.error("冲突了" + e.getDetailedMessage());
            }
            logger.error("索引异常", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //处理响应
        if (indexResponse != null) {
            logger.info("index:" + indexResponse.getIndex());
            logger.info("type:" + indexResponse.getType());
            logger.info("id:" + indexResponse.getId());
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                logger.info("新增文档成功!");
                result = 1;
            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                logger.info("修改文档成功!");
                result = 1;
            }

            // 分片处理信息
            ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            }
            // 如果有分片副本失败，可以获得失败原因信息
            if (shardInfo.getFailed() > 0) {
                for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                    String reason = failure.reason();
                    logger.info("副本失败原因：" + reason);
                    result = -1;
                }
            }
        }
        return result;
    }

    public int deleteDocument(String index, String type, String id) {
        int result = -1;
        DeleteRequest request = new DeleteRequest(index, type, id);
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);//删除立刻刷新，避免删除后一段数据才消失的问题
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = client.delete(request, RequestOptions.DEFAULT);
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                logger.info("没有找到");
                result = -1;
            }
            if (deleteResponse.getResult() == DocWriteResponse.Result.DELETED) {
                logger.info("删除成功");
                result = 1;
            }
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                logger.error("版本冲突" + e.getDetailedMessage());
            }
            logger.error("索引异常", e);
            result = -1;
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert deleteResponse != null;
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            logger.info("处理响应");
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                logger.info("failureReason" + reason);
                result = -1;
            }
        }
        return result;
    }

    public void updateDocument(String index, String type, String id, Map<String, Object> jsonMap)  {
        UpdateRequest updateRequest = new UpdateRequest(index, type, id).doc(jsonMap);
        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseIndex = updateResponse.getIndex();
        String responseType = updateResponse.getType();
        String responseId = updateResponse.getId();
        long version = updateResponse.getVersion();
        logger.info(version+"");
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            logger.info("文档创建更新");
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            logger.info("文档更新");
        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
            logger.info("文档删除更新");
        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
            logger.info("文档未更新");
        }
    }

    public String getDocument(String index, String type, String id) {
        GetRequest request = new GetRequest(index, type, id);
        //发送请求
        GetResponse getResponse = null;
        try {
            // 同步请求
            getResponse = client.get(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                logger.error("没有找到该id的文档");
            }
            if (e.status() == RestStatus.CONFLICT) {
                logger.error("获取时版本冲突了，请在此写冲突处理逻辑！");
            }
            logger.error("获取文档异常", e);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //处理响应
        if (getResponse != null) {
            if (getResponse.isExists()) { // 文档存在
                logger.info("index:" + getResponse.getIndex());
                logger.info("type:" + getResponse.getType());
                logger.info("id:" + getResponse.getId());
                logger.info("version:" + getResponse.getVersion());
                return getResponse.getSourceAsString(); //结果取成 String
            } else {
                logger.error("没有找到该id的文档");
                return null;
            }
        }
        return null;
    }

    public void bulkDocument(String index, String type, List<String> idList, List<String> jsonList) {
        // 1、创建批量操作请求参数
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < idList.size(); i++) {
            request.add(new IndexRequest(index, type, idList.get(i))
                    .source(jsonList.get(i), XContentType.JSON));
        }

        // 可选的设置
        /*
        request.timeout("2m");
        request.setRefreshPolicy("wait_for");
        request.waitForActiveShards(2);
        */

        //发送请求
        // 同步请求
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = client.bulk(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //4、处理响应
        if (bulkResponse != null) {
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                DocWriteResponse itemResponse = bulkItemResponse.getResponse();

                if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                        || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                    IndexResponse indexResponse = (IndexResponse) itemResponse;
                    logger.info("新增成功");

                } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                    UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                    logger.info("修改成功");

                } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                    DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                    logger.info("删除成功");
                }
            }
        }
    }

    public List parseResponse(SearchResponse searchResponse, Class clazz) {
        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();
        List list = new LinkedList<>();
        for (SearchHit hit : hits) {
            list.add(JSONObject.parseObject(hit.getSourceAsString(), clazz));
        }
        return list;
    }
}
