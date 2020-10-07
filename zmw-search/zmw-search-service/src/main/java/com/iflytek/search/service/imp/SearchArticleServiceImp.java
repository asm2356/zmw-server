package com.iflytek.search.service.imp;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.common.enumeration.sys.ESIndex;
import com.iflytek.common.model.*;
import com.iflytek.search.api.SearchArticleService;
import com.iflytek.search.service.config.ESClient;
import com.iflytek.search.service.config.ESConfig;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author AZhao
 */
@Service
public class SearchArticleServiceImp implements SearchArticleService {
    @Autowired
    private ESClient esClient;

    private RestHighLevelClient restHighLevelClient = ESConfig.createClient();

    @Override
    public int insertArticle(Article article) {
        return esClient.insertDocument(ESIndex.Article.getValue(), ESIndex.Article.getValue(), article.getArticleId(), JSON.toJSONString(article));
    }

    @Override
    public int deleteArticle(String articleId) {
        return esClient.deleteDocument(ESIndex.Article.getValue(), ESIndex.Article.getValue(), articleId);
    }

    @Override
    public Article getArticle(String articleId) {
        String json = esClient.getDocument(ESIndex.Article.getValue(), ESIndex.Article.getValue(), articleId);
        if (json != null) {
            return JSON.parseObject(json, Article.class);
        }
        return null;
    }

    @Override
    public void updateArticle(String id, Map<String, Object> map) {
        esClient.updateDocument(ESIndex.Article.getValue(), ESIndex.Article.getValue(), id, map);
    }

    @Override
    public List<Article> getArticleListByTitle(String title) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ESIndex.Article.getValue()).types(ESIndex.Article.getValue());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder titleQuery = QueryBuilders.wildcardQuery("title.keyword", "*" + title + "*");
        QueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("title.keyword", title);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(titleQuery).should(fuzzyQuery);
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);
        searchRequest.source(searchSourceBuilder);
        //HighlightBuilder highlightBuilder = new HighlightBuilder();
        //highlightBuilder.field("title")
        //        .preTags("<strong>").postTags("</strong>");
        //searchSourceBuilder.highlighter(highlightBuilder);
        SearchResponse searchResponse;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits searchHits = searchResponse.getHits();
            SearchHit[] hits = searchHits.getHits();
            return parseArticle(hits);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getArticleNumberByTitle(String title) {
        return 0;
    }

    private List<Article> parseArticle(SearchHit[] hits) {
        List<Article> list = new LinkedList<>();
        for (SearchHit hit : hits) {
            Article article = JSONObject.parseObject(hit.getSourceAsString(), Article.class);
            list.add(article);
        }
        return list;
    }
}
