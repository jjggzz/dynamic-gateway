package com.jgz.gateway.manage.service;

import com.jgz.gateway.manage.controller.req.RouteIndexReq;
import com.jgz.gateway.manage.controller.req.RouteReq;
import com.jgz.gateway.manage.controller.res.RouteRes;
import com.jgz.gateway.support.ApiResult;
import com.jgz.gateway.support.PageRet;

public interface RouteService {

    ApiResult<Integer> add(RouteReq routeReq);

    ApiResult<Void> update(Integer routeId, RouteReq routeReq);

    ApiResult<Void> delete(Integer routeId);

    ApiResult<PageRet<RouteRes>> index(RouteIndexReq routeIndexReq);

    ApiResult<Void> enable(Integer routeId);

    ApiResult<Void> disable(Integer routeId);

    ApiResult<RouteRes> get(Integer routeId);
}
