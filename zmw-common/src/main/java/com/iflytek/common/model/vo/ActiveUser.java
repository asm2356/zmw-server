package com.iflytek.common.model.vo;

import com.iflytek.common.model.UserBaseInfo;

import java.io.Serializable;
/**
 * @author AZhao
 */
public class ActiveUser implements Serializable {
    private static final long serialVersionUID = 4335585979116039978L;
    private String mwId;
    private UserBaseInfo userBaseInfo;
    private int count;

    public String getMwId() {
        return mwId;
    }

    public void setMwId(String mwId) {
        this.mwId = mwId;
    }

    public UserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ActiveUser{" +
                "mwId='" + mwId + '\'' +
                ", userBaseInfo=" + userBaseInfo +
                ", count=" + count +
                '}';
    }
}
