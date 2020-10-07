package com.iflytek.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 * @author AZhao
 */
@TableName(value = "login_history")
public class LoginHistory implements Serializable {
    private static final long serialVersionUID = -7742286513526098937L;
    @TableId(value = "id")
    private String id;
    @TableField(value = "mw_id")
    private String mwId;
    @TableField(value = "login_equipment")
    private String loginEquipment;
    @TableField(value = "browser")
    private String browser;
    @TableField(value = "login_time")
    private long loginTime;
    @TableField(value = "ip")
    private String ip;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "login_address")
    private String loginAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMwId() {
        return mwId;
    }

    public void setMwId(String mwId) {
        this.mwId = mwId;
    }

    public String getLoginEquipment() {
        return loginEquipment;
    }

    public void setLoginEquipment(String loginEquipment) {
        this.loginEquipment = loginEquipment;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    @Override
    public String toString() {
        return "LoginHistory{" +
                "id='" + id + '\'' +
                ", mwId='" + mwId + '\'' +
                ", loginEquipment='" + loginEquipment + '\'' +
                ", browser='" + browser + '\'' +
                ", loginTime=" + loginTime +
                ", ip='" + ip + '\'' +
                ", phone='" + phone + '\'' +
                ", loginAddress='" + loginAddress + '\'' +
                '}';
    }

}
