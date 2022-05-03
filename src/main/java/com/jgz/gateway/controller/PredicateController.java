package com.jgz.gateway.controller;

import com.jgz.gateway.controller.req.PredicateReq;
import com.jgz.gateway.service.PredicateService;
import com.jgz.gateway.support.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/manage")
@RestController
public class PredicateController {

    @Resource
    private PredicateService predicateService;


    @PutMapping("/predicate")
    public ApiResult<Integer> add(
            @RequestBody PredicateReq predicateReq
    ) {
        return predicateService.add(predicateReq);
    }

    @PostMapping("/predicate/{predicateId}")
    public ApiResult<Void> update(
            @PathVariable("predicateId") Integer predicateId,
            @RequestBody PredicateReq predicateReq
    ) {
        return predicateService.update(predicateId,predicateReq);
    }

    @DeleteMapping("/predicate/{predicateId}")
    public ApiResult<Void> delete(
            @PathVariable("predicateId") Integer predicateId
    ) {
        return predicateService.delete(predicateId);
    }

}
