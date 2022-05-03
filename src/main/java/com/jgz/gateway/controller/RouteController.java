package com.jgz.gateway.controller;

import com.jgz.gateway.controller.req.RouteIndexReq;
import com.jgz.gateway.controller.req.RouteReq;
import com.jgz.gateway.controller.res.RouteRes;
import com.jgz.gateway.service.ReloadService;
import com.jgz.gateway.service.RouteService;
import com.jgz.gateway.support.ApiResult;
import com.jgz.gateway.support.PageRet;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/manage")
@RestController
public class RouteController {

    @Resource
    private RouteService routeService;

    @Resource
    private ReloadService reloadService;


    @PutMapping("/route")
    public ApiResult<Integer> add(
            @RequestBody RouteReq routeReq
    ) {
        return routeService.add(routeReq);
    }

    @PostMapping("/route/{routeId}")
    public ApiResult<Void> update(
            @PathVariable("routeId") Integer routeId,
            @RequestBody RouteReq routeReq
    ) {
        return routeService.update(routeId,routeReq);
    }

    @DeleteMapping("/route/{routeId}")
    public ApiResult<Void> delete(
            @PathVariable("routeId") Integer routeId
    ) {
        return routeService.delete(routeId);
    }

    @PostMapping("/route/index")
    public ApiResult<PageRet<RouteRes>> index(
            @RequestBody RouteIndexReq routeIndexReq
    ) {
        return routeService.index(routeIndexReq);
    }

    @GetMapping("/route/{routeId}")
    public ApiResult<RouteRes> get(
            @PathVariable("routeId") Integer routeId
    ) {
        return routeService.get(routeId);
    }

    @PutMapping("/route/enable/{routeId}")
    public ApiResult<Void> enable(
            @PathVariable("routeId") Integer routeId
    ) {
        return routeService.enable(routeId);
    }

    @PutMapping("/route/disable/{routeId}")
    public ApiResult<Void> disable(
            @PathVariable("routeId") Integer routeId
    ) {
        return routeService.disable(routeId);
    }

    @GetMapping("/route/reload")
    public ApiResult<Void> reload() {
        reloadService.refreshRoute();
        return ApiResult.success();
    }

}
