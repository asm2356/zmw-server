package com.iflytek.common.model;

import java.io.Serializable;
import java.util.List;
/**
 * @author AZhao
 */
public class Page<T> implements Serializable {
    private int pageSize; //每页显示条数
    private int totalCount; //总条数
    private int startNum; //开始条数
    private int pageNo;//当前页
    private int totalPages; //总页数
    private List<T> pageList;//数据

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getPageNo() {
        return startNum / pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPages() {
        return totalCount % pageSize != 0 ? (totalCount / pageSize) + 1 : totalCount / pageSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
}
