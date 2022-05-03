package com.jgz.gateway.controller.req;

public class FilterReq {

    private Integer routeId;

    private String name;

    private String value;

    public Integer getRouteId() {
        return routeId;
    }

    public FilterReq setRouteId(Integer routeId) {
        this.routeId = routeId;
        return this;
    }

    public String getName() {
        return name;
    }

    public FilterReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public FilterReq setValue(String value) {
        this.value = value;
        return this;
    }
}
