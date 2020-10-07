package com.iflytek.common.enumeration.user;
/**
 * @author AZhao
 */
public enum Openness {
    Open(1),
    Not_Open(2),//不公开
    Encrypt_Open(3),//加密公开
    ALL(4)//所有的
    ;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    Openness(int value) {
        this.value = value;
    }
}
