package com.jgz.gateway.controller.res;

import java.util.List;

public class RouteRes {

    private Integer id;

    private String ukey;

    private String uri;

    private Integer ord;

    private Boolean state;

    private List<PredicateRes> predicateResList;

    private List<FilterRes> filterResList;

    public static class PredicateRes {

    }

    public static class FilterRes {

        private Integer id;

        private Integer routeId;

        private String name;

        private String value;

        public Integer getId() {
            return id;
        }

        public FilterRes setId(Integer id) {
            this.id = id;
            return this;
        }

        public Integer getRouteId() {
            return routeId;
        }

        public FilterRes setRouteId(Integer routeId) {
            this.routeId = routeId;
            return this;
        }

        public String getName() {
            return name;
        }

        public FilterRes setName(String name) {
            this.name = name;
            return this;
        }

        public String getValue() {
            return value;
        }

        public FilterRes setValue(String value) {
            this.value = value;
            return this;
        }
    }


    public Integer getId() {
        return id;
    }

    public RouteRes setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUkey() {
        return ukey;
    }

    public RouteRes setUkey(String ukey) {
        this.ukey = ukey;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public RouteRes setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public Integer getOrd() {
        return ord;
    }

    public RouteRes setOrd(Integer ord) {
        this.ord = ord;
        return this;
    }

    public Boolean getState() {
        return state;
    }

    public RouteRes setState(Boolean state) {
        this.state = state;
        return this;
    }

    public List<PredicateRes> getPredicateResList() {
        return predicateResList;
    }

    public RouteRes setPredicateResList(List<PredicateRes> predicateResList) {
        this.predicateResList = predicateResList;
        return this;
    }

    public List<FilterRes> getFilterResList() {
        return filterResList;
    }

    public RouteRes setFilterResList(List<FilterRes> filterResList) {
        this.filterResList = filterResList;
        return this;
    }
}
