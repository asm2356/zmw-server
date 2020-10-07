//package com.iflytek.search.service.config;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.entity.ContentType;
//import org.apache.http.nio.entity.NStringEntity;
//import org.apache.http.util.EntityUtils;
//import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
//import org.elasticsearch.client.Response;
//import org.elasticsearch.client.RestClient;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Map;
//
//@Configuration
//public class ESConfigRestful {
//    private static RestClient restClient;
//    private static String address;//es地址
//    private static Response response;
//
//    ESConfigRestful() {
//        address = "127.0.0.1";
//    }
//
//    public static void main(String[] args) {
//        ESConfigRestful elasticsearchUtil = new ESConfigRestful();
//        elasticsearchUtil.createClient();
//        String responseBody = elasticsearchUtil.getData("zmw/user_info/Iid9C2cBIYLFijdu1iv2", "[]", "GET");
//        System.out.println(responseBody);
//        elasticsearchUtil.close();
//    }
//
//    public static RestClient createClient() {
//        restClient = RestClient.builder(
//                new HttpHost(address, 9200, "http"),
//                new HttpHost(address, 9201, "http"),
//                new HttpHost(address, 9202, "http")).build();
//        return restClient;
//    }
//
//    public String getData(String url, String argsBody, String method) {
//        Map<String, String> params = Collections.emptyMap();
//        HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory consumerFactory =
//                new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024);
//        HttpEntity httpEntity = new NStringEntity(argsBody, ContentType.APPLICATION_JSON);
//        String responseBody = "";
//        try {
//            //
//            response = restClient.performRequest(method, url, params, httpEntity, consumerFactory);
//            responseBody = EntityUtils.toString(response.getEntity());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return responseBody;
//    }
//
//    public void close() {
//        try {
//            restClient.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
