package com.iflytek.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * @author AZhao
 */
public class RandomUtils {
    /**
     * @param n 随机数的位数
     * @return 返回字母和数字随机数组合
     * @author zgzhao
     *  生成大小写字母和数字组合
     */
    public static String getItemID(int n) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (nextInt + random.nextInt(26)));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                val.append(String.valueOf(random.nextInt(10)));
            }
        }
        return val.toString();
    }

    /**
     * @return
     * 生成uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
