package com.iflytek.search.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.iflytek.common.enumeration.sys.ESIndex;
import com.iflytek.common.model.UserBaseInfo;
import com.iflytek.search.api.SearchUserBaseInfoService;
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
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author AZhao
 */
@Service
public class SearchUserBaseInfoServiceImp implements SearchUserBaseInfoService {
    @Autowired
    private ESClient esClient;

    private RestHighLevelClient restHighLevelClient = ESConfig.createClient();

    @Override
    public int insertUserBaseInfo(UserBaseInfo userBaseInfo) {
        if (userBaseInfo == null || userBaseInfo.getMwId() == null) {
            return -1;
        }
        return esClient.insertDocument(ESIndex.User_Base_Info.getValue(), ESIndex.User_Base_Info.getValue(), userBaseInfo.getMwId(), JSON.toJSONString(userBaseInfo));
    }

    @Override
    public int updateUserBaseInfo(UserBaseInfo userBaseInfo) {
        return insertUserBaseInfo(userBaseInfo);
    }

    @Override
    public int deleteUserBaseInfo(String mwId) {
        if (mwId == null) {
            return -1;
        }
        return esClient.deleteDocument(ESIndex.User_Base_Info.getValue(), ESIndex.User_Base_Info.getValue(), mwId);
    }

    @Override
    public UserBaseInfo getUserBaseInfo(String mwId) {
        String json = esClient.getDocument(ESIndex.User_Base_Info.getValue(), ESIndex.User_Base_Info.getValue(), mwId);
        if (json != null) {
            return JSON.parseObject(json, UserBaseInfo.class);
        }
        return null;
    }

    @Override
    public List getUserBaseInfoListByAttribute(String item, long startNum, int pageSize) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ESIndex.User_Base_Info.getValue()).types(ESIndex.User_Base_Info.getValue());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder alias = QueryBuilders.wildcardQuery("alias.keyword", "*" + item + "*"); //multiMatchQuery 匹配多个字段
        QueryBuilder mwId = QueryBuilders.wildcardQuery("mwId.keyword", "*" + item + "*");
        boolQueryBuilder.should(alias);
        boolQueryBuilder.should(mwId);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));//对评分进行排序
        //sourceBuilder.sort(new FieldSortBuilder("_uid").order(SortOrder.ASC));
        searchSourceBuilder.from((int) startNum);
        searchSourceBuilder.size(pageSize);
        searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));//搜索超时时间
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return esClient.parseResponse(searchResponse, UserBaseInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
