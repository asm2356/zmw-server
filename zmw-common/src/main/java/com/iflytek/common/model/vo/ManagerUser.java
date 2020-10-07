package com.iflytek.common.model.vo;

import com.iflytek.common.model.Account;
import com.iflytek.common.model.UserBaseInfo;

import java.io.Serializable;

/**
 * @author zgzhao
 * 管理界面
 */
public class ManagerUser implements Serializable {
    private static final long serialVersionUID = -2418984274873877452L;
    private String mwId;
    private Account account;
    private int loginNumber;//登录次数
    private UserBaseInfo userBaseInfo;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(int loginNumber) {
        this.loginNumber = loginNumber;
    }

    public UserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(UserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    public String getMwId() {
        return mwId;
    }

    public void setMwId(String mwId) {
        this.mwId = mwId;
    }

    @Override
    public String toString() {
        return "ManagerUser{" +
                "mwId='" + mwId + '\'' +
                ", account=" + account +
                ", loginNumber=" + loginNumber +
                ", userBaseInfo=" + userBaseInfo +
                '}';
    }
}
