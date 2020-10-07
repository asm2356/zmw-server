package com.iflytek.config.service.app;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author AZhao
 */
public class JDBCUtils {
    private static DruidDataSource ds;
    private static JdbcTemplate jdbcTemplate = createJdbcTemplate();
    private static Properties properties;

    static JdbcTemplate newInstance() {
        return jdbcTemplate;
    }

    private static void loadProperties() {
        properties = new Properties();
        InputStream inputStream = JDBCUtils.class.getResourceAsStream("/system_jdbc.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JdbcTemplate createJdbcTemplate() {
        if (jdbcTemplate == null) {
            loadProperties();
            ds = new DruidDataSource();
            ds.setDriverClassName(properties.getProperty("driverClassName"));
            ds.setUrl(properties.getProperty("url"));
            ds.setUsername(properties.getProperty("username"));
            ds.setPassword(properties.getProperty("password"));
            return new JdbcTemplate(ds);
        }
        return jdbcTemplate;
    }

    public static void close() {
        ds.close();
    }
}
