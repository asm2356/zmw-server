package com.iflytek.common.enumeration.sys;

/**
 * @author AZhao
 */
public enum ESIndex {
    User_Base_Info("user_base_info"),
    Op_Log("op_log"), Article("article");
    private String value;

    ESIndex(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
