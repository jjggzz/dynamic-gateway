package com.jgz.gateway.manage.controller.req;

public class RouteReq {

    private String ukey;

    private String uri;

    private Integer ord;

    public String getUkey() {
        return ukey;
    }

    public RouteReq setUkey(String ukey) {
        this.ukey = ukey;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public RouteReq setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public Integer getOrd() {
        return ord;
    }

    public RouteReq setOrd(Integer ord) {
        this.ord = ord;
        return this;
    }
}
