package com.iflytek.common.enumeration.user;
/**
 * @author AZhao
 */
public enum LetterStatus {
    Unread(1),
    Already_Read(2),
    All(3);
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    LetterStatus(int value) {
        this.value = value;
    }
}
