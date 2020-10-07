package com.iflytek.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;
/**
 * @author AZhao
 */
@TableName(value="account")
public class Account implements Serializable {
    private static final long serialVersionUID = 1571406198892002025L;
    @TableId(value = "mw_id")
    private String mwId;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "pwd")
    private String pwd;
    @TableField(value = "status")
    private int status;
    @TableField(exist = false)
    private List<Role> roleList;
    @TableField(exist = false)
    private List<Permission> permissionList;

    public String getMwId() {
        return mwId;
    }

    public void setMwId(String mwId) {
        this.mwId = mwId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public Account(String mwId, String pwd) {
        this.mwId = mwId;
        this.pwd = pwd;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                ", mwId='" + mwId + '\'' +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", status=" + status +
                ", roleList=" + roleList +
                ", permissionList=" + permissionList +
                '}';
    }
}
