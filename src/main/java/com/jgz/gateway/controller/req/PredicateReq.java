package com.jgz.gateway.controller.req;

public class PredicateReq {

    private Integer routeId;

    private String ukey;

    private String value;

    public Integer getRouteId() {
        return routeId;
    }

    public PredicateReq setRouteId(Integer routeId) {
        this.routeId = routeId;
        return this;
    }

    public String getUkey() {
        return ukey;
    }

    public PredicateReq setUkey(String ukey) {
        this.ukey = ukey;
        return this;
    }

    public String getValue() {
        return value;
    }

    public PredicateReq setValue(String value) {
        this.value = value;
        return this;
    }
}
