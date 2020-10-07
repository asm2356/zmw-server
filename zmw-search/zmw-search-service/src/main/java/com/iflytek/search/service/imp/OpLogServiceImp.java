package com.iflytek.search.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.common.enumeration.sys.ESIndex;
import com.iflytek.common.model.OpLogRecord;
import com.iflytek.search.api.OpLogService;
import com.iflytek.search.service.config.ESClient;
import com.iflytek.search.service.config.ESConfig;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author AZhao
 */
@Service
public class OpLogServiceImp implements OpLogService {

    @Autowired
    private ESClient esClient;
    private RestHighLevelClient restHighLevelClient = ESConfig.createClient();

    @Override
    public int insertLog(OpLogRecord opLogRecord) {
        return esClient.insertDocument(ESIndex.Op_Log.getValue(), ESIndex.Op_Log.getValue(), opLogRecord.getId(), JSON.toJSONString(opLogRecord));
    }

    @Override
    public int deleteLog(String id) {
        return esClient.deleteDocument(ESIndex.Op_Log.getValue(), ESIndex.Op_Log.getValue(), id);
    }

    public SearchSourceBuilder parseCondition(QueryBuilder mwIdQueryBuilder, SearchSourceBuilder searchSourceBuilder, String condition) {
        if (condition != null) {
            JSONObject jsonObject = JSONObject.parseObject(condition);
            Set<String> set = jsonObject.keySet();
            for (String key : set) {
                if (key.equals("time")) {
                    JSONObject timeJsonObject = JSONObject.parseObject(jsonObject.getString(key));
                    String startTime = timeJsonObject.getString("startTime");
                    String endTime = timeJsonObject.getString("endTime");
                    RangeQueryBuilder rangequerybuilder = QueryBuilders
                            .rangeQuery("opTime")
                            .gt(startTime).lt(endTime);
                    QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(rangequerybuilder).must(mwIdQueryBuilder);
                    searchSourceBuilder.query(queryBuilder);
                    break;
                } else {
                    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().filter(QueryBuilders.termQuery(key+".keyword", jsonObject.getString(key))).must(mwIdQueryBuilder);
                    searchSourceBuilder.query(boolQueryBuilder);
                    break;
                }
            }
        } else {
            searchSourceBuilder.query(mwIdQueryBuilder);
        }
        return searchSourceBuilder;
    }

    @Override
    public List getLog(int startNum, int pageSize, String mwId, String condition) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ESIndex.Op_Log.getValue()).types(ESIndex.Op_Log.getValue());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));//搜索超时时间
        searchSourceBuilder.from(startNum);
        searchSourceBuilder.size(pageSize);

        QueryBuilder mwIdQueryBuilder = QueryBuilders.termQuery("mwId", mwId);
        searchSourceBuilder = parseCondition(mwIdQueryBuilder, searchSourceBuilder, condition);


        FieldSortBuilder fieldSortBuilder = new FieldSortBuilder("opTime");
        fieldSortBuilder.order(SortOrder.DESC);
        searchSourceBuilder.sort(fieldSortBuilder);


        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            return esClient.parseResponse(searchResponse, OpLogRecord.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public int getOpLogNumber(String mwId, String condition) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ESIndex.Op_Log.getValue()).types(ESIndex.Op_Log.getValue());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));//搜索超时时间
        QueryBuilder queryBuilder = QueryBuilders.termQuery("mwId", mwId);//匹配含有

        searchSourceBuilder = parseCondition(queryBuilder, searchSourceBuilder, condition);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return (int) searchResponse.getHits().totalHits;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
