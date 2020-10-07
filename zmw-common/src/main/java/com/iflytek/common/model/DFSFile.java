package com.iflytek.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;
/**
 * @author AZhao
 */
@TableName(value = "file")
public class DFSFile implements Serializable {
    private static final long serialVersionUID = -130101837273541039L;
    @TableId(value = "id")
    private String id;
    @TableField(value = "host")
    private String host;
    @TableField(value = "group_name")
    private String groupName;
    @TableField(value = "path")
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DFSFile(String host, String groupName, String path) {
        this.host = host;
        this.groupName = groupName;
        this.path = path;
    }

    public DFSFile(String id, String host, String groupName, String path) {
        this.id = id;
        this.host = host;
        this.groupName = groupName;
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DFSFile dfsFile = (DFSFile) o;
        return
                Objects.equals(host, dfsFile.host) &&
                        Objects.equals(groupName, dfsFile.groupName) &&
                        Objects.equals(path, dfsFile.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, groupName, path);
    }

    public DFSFile() {
    }

    @Override
    public String toString() {
        return "DFSFile{" +
                "id=" + id +
                ", host='" + host + '\'' +
                ", groupName='" + groupName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
