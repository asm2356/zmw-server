package com.iflytek.common.utils;

import org.springframework.util.SerializationUtils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AZhao
 */
public class MyUtils {
    public static List parseRequestList(String json) {
        String[] maps = json.split("&");
        List list = new ArrayList<>();
        for (String temp : maps) {
            list.add(temp.split("=")[1]);
        }
        return list;
    }

    //序列化
    public static String jdkSerialize(Object object) {
        if (object instanceof String) return (String) object;
        byte[] bytes = SerializationUtils.serialize(object);
        if (bytes == null || bytes.length == 0) return null;
        return new String(bytes);
    }

    //反序列化
    public static Object jdkDeserialize(String value) {
        return SerializationUtils.deserialize(value.getBytes());
    }

    /**
     * @param str 格式 127.0.0.1:8081,192.168.1.2:8097
     * @return
     */
    public static InetSocketAddress[] strToSocketAddress(String str) {
        String[] temp = str.split(",");
        InetSocketAddress[] socketAddresses = new InetSocketAddress[temp.length];
        for (int i = 0; i < temp.length; i++) {
            String address = temp[i].split(":")[0];
            String port = temp[i].split(":")[1];
            socketAddresses[i] = new InetSocketAddress(address, Integer.parseInt(port));
        }
        return socketAddresses;
    }

    public static String secondToTime(long time) {
        if (time < 60) {
            return time + "秒";
        }
        if (time < 3600) {
            long minute = time / 60;
            return minute + "分" + (time - minute * 60) + "秒";
        }
        if (time < 86400) {
            long hour = (time / 3600);
            long minute = (time % 3600) / 60;
            long second = time % 3600 - minute * 60;
            return hour + "小时" + minute + "分" + second + "秒";
        } else {
            long day = time / 86400;
            long hour = (time % 86400) / (3600);
            return day + "天" + hour + "小时";
        }
    }
}
