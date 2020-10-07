package com.iflytek.config.service.other;

import com.iflytek.config.service.app.SystemConfigUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author AZhao
 */
public class FastDFSConfig {
    private static TrackerClient trackerClient = null;
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static StorageClient storageClient = null;

    private static void initConfigFile() throws IOException, MyException {
        String conf_file = FastDFSConfig.class.getResource("/conf/fdfs_client.conf").getPath();
        ClientGlobal.init(conf_file);
    }

    private static void init() throws MyException {
        String connect_timeout = SystemConfigUtils.getValue("fastdfs.connect_timeout");
        String network_timeout = SystemConfigUtils.getValue("fastdfs.network_timeout");
        String charset = SystemConfigUtils.getValue("fastdfs.charset");
        String anti_steal_token = SystemConfigUtils.getValue("fastdfs.anti_steal_token");
        String secret_key = SystemConfigUtils.getValue("fastdfs.secret_key");
        String tracker_http_port = SystemConfigUtils.getValue("fastdfs.tracker_http_port");
        if (connect_timeout != null) {
            ClientGlobal.g_connect_timeout = Integer.parseInt(connect_timeout);
        }
        if (network_timeout != null) {
            ClientGlobal.g_network_timeout = Integer.parseInt(network_timeout);
        }
        if (charset != null) {
            ClientGlobal.g_charset = charset;
        }
        if (anti_steal_token != null) {
            ClientGlobal.g_anti_steal_token = Boolean.parseBoolean(anti_steal_token);
        }
        if (secret_key != null) {
            ClientGlobal.g_secret_key = secret_key;
        }
        if (tracker_http_port != null) {
            ClientGlobal.g_tracker_http_port = Integer.parseInt(tracker_http_port);
        }
        String trackerServer = SystemConfigUtils.getValue("fastdfs.tracker_server");
        String trackerServerPort = SystemConfigUtils.getValue("fastdfs.tracker_server_port");

        InetSocketAddress[] trackerServersSocket = new InetSocketAddress[1];
        trackerServersSocket[0] = new InetSocketAddress(trackerServer, Integer.parseInt(trackerServerPort));
        ClientGlobal.g_tracker_group = new TrackerGroup(trackerServersSocket);
    }

    public static StorageClient getStorageClient() {
        try {
            init();
            trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return storageClient;
    }

    public static void close() {
        try {
            if (null != storageServer) {
                storageServer.close();
            }
            if (null != trackerServer) {
                trackerServer.close();
            }
        } catch (IOException ignored) {
        }
    }
}
