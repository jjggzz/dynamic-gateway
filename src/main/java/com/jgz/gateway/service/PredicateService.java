package com.jgz.gateway.service;

import com.jgz.gateway.controller.req.PredicateReq;
import com.jgz.gateway.support.ApiResult;

public interface PredicateService {
    ApiResult<Integer> add(PredicateReq predicateReq);

    ApiResult<Void> update(Integer predicateId, PredicateReq predicateReq);

    ApiResult<Void> delete(Integer predicateId);

}
