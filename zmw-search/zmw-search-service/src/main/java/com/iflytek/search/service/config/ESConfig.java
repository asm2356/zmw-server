package com.iflytek.search.service.config;

import com.iflytek.common.utils.MyUtils;
import com.iflytek.config.service.app.SystemConfigUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author AZhao
 */
public class ESConfig {
    private static RestHighLevelClient client;

    static {
        String addressList = SystemConfigUtils.getValue("elasticsearch.address");
        InetSocketAddress[] inetSocketAddresses = MyUtils.strToSocketAddress(addressList);
        HttpHost[] httpHosts = new HttpHost[inetSocketAddresses.length];
        for (int i = 0; i < inetSocketAddresses.length; i++) {
            httpHosts[i] = new HttpHost(
                    inetSocketAddresses[i].getAddress(),
                    inetSocketAddresses[i].getPort(),
                    "http");
        }
        client = new RestHighLevelClient(RestClient.builder(httpHosts));
    }

    private ESConfig() {

    }

    public static RestHighLevelClient createClient() {
        return client;
    }


    public void close() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
