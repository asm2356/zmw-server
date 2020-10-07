package com.iflytek.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 * @author AZhao
 */
@TableName(value="user_base_info")
public class UserBaseInfo implements Serializable {
    private static final long serialVersionUID = -7743628881312449896L;
    @TableId(value = "mw_id")
    private String mwId;
    @TableField(value = "sex")
    private String sex;
    @TableField(value = "birthday")
    private String birthday;
    @TableField(value = "wechat")
    private String wechat;
    @TableField(value = "introduction")
    private String introduction;
    @TableField(value = "header")
    private String header;
    @TableField(value = "alias")
    private String alias;
    @TableField(value = "cover")
    private String cover;


    public String getMwId() {
        return mwId;
    }

    public void setMwId(String mwId) {
        this.mwId = mwId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }




    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public UserBaseInfo(String mwId) {
        this.mwId = mwId;
    }

    public UserBaseInfo() {
    }

    @Override
    public String toString() {
        return "UserBaseInfo{" +
                "mwId='" + mwId + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", wechat='" + wechat + '\'' +
                ", introduction='" + introduction + '\'' +
                ", header='" + header + '\'' +
                ", alias='" + alias + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
