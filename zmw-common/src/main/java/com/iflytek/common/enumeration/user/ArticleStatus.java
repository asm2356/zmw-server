package com.iflytek.common.enumeration.user;
/**
 * @author AZhao
 */
public enum ArticleStatus {
    HAS_RELEASE(1),//已经发布
    SCHEDULED_RELEASE(2),//定时发布
    DRATFT(3);//草稿
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    ArticleStatus(int value) {
        this.value = value;
    }
}
