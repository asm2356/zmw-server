package com.iflytek.common.model;

import java.io.Serializable;
/**
 * @author AZhao
 */
public class OpLogRecord implements Serializable {
    private static final long serialVersionUID = -59125417130975855L;
    private String id;
    private String mwId;
    private long opTime;
    private String describe;
    private String method;
    private String remoteIP;
    private String inputParams;

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

    public long getOpTime() {
        return opTime;
    }

    public void setOpTime(long opTime) {
        this.opTime = opTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public String getInputParams() {
        return inputParams;
    }

    public void setInputParams(String inputParams) {
        this.inputParams = inputParams;
    }

    @Override
    public String toString() {
        return "OpLogRecord{" +
                "id='" + id + '\'' +
                ", mwId='" + mwId + '\'' +
                ", opTime=" + opTime +
                ", describe='" + describe + '\'' +
                ", method='" + method + '\'' +
                ", remoteIP='" + remoteIP + '\'' +
                ", inputParams='" + inputParams + '\'' +
                '}';
    }
}
