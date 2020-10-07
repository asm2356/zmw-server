package com.iflytek.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 * @author AZhao
 */
@TableName(value = "config")
public class Config implements Serializable {
    private static final long serialVersionUID = -1064272139751635449L;
    @TableId(value = "c_key")
    private String key;
    @TableField(value = "c_value")
    private String value;
    @TableField(value = "c_describe")
    private String describe;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Config(String key, String value, String describe) {
        this.key = key;
        this.value = value;
        this.describe = describe;
    }

    public Config() {
    }

    @Override
    public String toString() {
        return "Config{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
