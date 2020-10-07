package com.iflytek.common.enumeration.sys;
/**
 * @author zgzhao
 * 统计 时间的类型
 */
public enum StatisticsTimeType {
    One_Week(1),
    One_Months(2),//四周(月)
    One_Quarter(3), //十二周(季度)
    One_Year(4);
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    StatisticsTimeType(int value) {
        this.value = value;
    }
}
