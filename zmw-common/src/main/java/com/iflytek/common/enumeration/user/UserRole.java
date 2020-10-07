package com.iflytek.common.enumeration.user;
/**
 * @author AZhao
 */
public enum  UserRole {
    Admin(1){
        @Override
        public int getValue() {
            return super.getValue();
        }
    },
    Ordinary(2){
        @Override
        public int getValue() {
            return super.getValue();
        }
    },
    SuperAdmin(3){
        @Override
        public int getValue() {
            return super.getValue();
        }
    },
    VIPUser(4){
        @Override
        public int getValue() {
            return super.getValue();
        }
    };
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    UserRole(int value) {
        this.value = value;
    }
}
