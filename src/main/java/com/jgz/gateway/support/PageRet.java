package com.jgz.gateway.support;

import java.util.List;

public class PageRet<T> {

    private Integer total;
    private Integer pageSize;
    private Integer pageNum;
    private List<T> data;

    public Integer getTotal() {
        return total;
    }

    public PageRet<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public PageRet<T> setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public PageRet<T> setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public PageRet<T> setData(List<T> data) {
        this.data = data;
        return this;
    }
}
