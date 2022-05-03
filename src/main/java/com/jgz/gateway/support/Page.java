package com.jgz.gateway.support;

public class Page {

    private Integer page = 0;

    private Integer size = 20;

    public Integer getPage() {
        return page;
    }

    public Page setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public Page setSize(Integer size) {
        this.size = size;
        return this;
    }
}
