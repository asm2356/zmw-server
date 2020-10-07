package com.iflytek.common.enumeration.sys;
/**
 * @author zgzhao
 * 统计的类型
 */
public enum StatisticsType {
    Active_Rate(1),//用户总体活跃率
    Add_User(2),//新增用户
    User_Sum(3);//用户总量
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    StatisticsType(int value) {
        this.value = value;
    }
}
