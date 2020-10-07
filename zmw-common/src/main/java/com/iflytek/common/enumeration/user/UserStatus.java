package com.iflytek.common.enumeration.user;

/**
 * @author AZhao
 */
public enum UserStatus {
    NOEMAL(1) {
        @Override
        public int getValue() {
            return super.getValue();
        }
    }, Forbidden(2);
    private int value;

    public int getValue() {
        return value;
    }

    UserStatus(int value) {
        this.value = value;
    }
}
