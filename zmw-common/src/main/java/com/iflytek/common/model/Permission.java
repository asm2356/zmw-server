package com.iflytek.common.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author AZhao
 */
@TableName(value = "permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 5700449886528450905L;
    @TableId(value = "id")
    private int id;
    @TableId(value = "permission_name")
    private String permissionName;
    @TableId(value = "describe")
    private String describe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
