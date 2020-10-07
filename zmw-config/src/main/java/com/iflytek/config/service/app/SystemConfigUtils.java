package com.iflytek.config.service.app;

import com.iflytek.common.model.Config;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author AZhao
 */
public class SystemConfigUtils {
    private static JdbcTemplate jdbcTemplate = JDBCUtils.newInstance();


    public static int addItem(final Config config) {
        String sql = "insert into config(c_key,c_value,c_describe) value(?,?,?);";
        return jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, config.getKey());
            preparedStatement.setString(2, config.getValue());
            preparedStatement.setString(3, config.getDescribe());
        });
    }


    public static int deleteItem(final String key) {
        String sql = "delete from config where c_key=?";
        return jdbcTemplate.update(sql, preparedStatement -> preparedStatement.setString(1, key));
    }


    public static int updateItem(final Config config) {
        final String sql = "update config set c_value =? ,c_describe=? where c_key = ? ";
        return jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, config.getValue());
            preparedStatement.setString(2, config.getDescribe());
            preparedStatement.setString(3, config.getKey());
        });
    }


    public static Config getItem(String key) {
        final Config config = new Config();
        final String sql = "select * from config where c_key = ?";
        jdbcTemplate.query(sql, new Object[]{key}, resultSet -> {
            config.setKey(resultSet.getString("c_key"));
            config.setValue(resultSet.getString("c_value"));
            config.setDescribe(resultSet.getString("c_describe"));
        });
        return config;
    }

    public static String getValue(String key) {
        return getItem(key).getValue();
    }

    public static String getDescribe(String key) {
        return getItem(key).getDescribe();
    }

    public static List<Config> getAllItem() {
        final String sql = "select * from config";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Config config = new Config();
            config.setKey(resultSet.getString("c_key"));
            config.setValue(resultSet.getString("c_value"));
            config.setDescribe(resultSet.getString("c_describe"));
            return config;
        });
    }

    public static void main(String[] args) {
        String value = SystemConfigUtils.getValue("xxl.job.accessToken");
        System.out.println(value);
    }
}
