package com.jgz.gateway.controller.req;

import com.jgz.gateway.support.Page;

public class RouteIndexReq extends Page {

    private String ukey;

    private String uri;

    public String getUkey() {
        return ukey;
    }

    public RouteIndexReq setUkey(String ukey) {
        this.ukey = ukey;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public RouteIndexReq setUri(String uri) {
        this.uri = uri;
        return this;
    }
}
