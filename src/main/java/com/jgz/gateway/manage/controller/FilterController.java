package com.jgz.gateway.manage.controller;

import com.jgz.gateway.manage.controller.req.FilterReq;
import com.jgz.gateway.manage.service.FilterService;
import com.jgz.gateway.support.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/manage")
@RestController
public class FilterController {

    @Resource
    private FilterService filterService;

    @PutMapping("/filter")
    public ApiResult<Integer> add(
            @RequestBody FilterReq filterReq
    ) {
        return filterService.add(filterReq);
    }

    @PostMapping("/filter/{filterId}")
    public ApiResult<Void> update(
            @PathVariable("filterId") Integer filterId,
            @RequestBody FilterReq filterReq
    ) {
        return filterService.update(filterId,filterReq);
    }

    @DeleteMapping("/filter/{filterId}")
    public ApiResult<Void> delete(
            @PathVariable("filterId") Integer filterId
    ) {
        return filterService.delete(filterId);
    }

}
