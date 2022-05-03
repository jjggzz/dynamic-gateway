package com.jgz.gateway.service;

import com.jgz.gateway.controller.req.FilterReq;
import com.jgz.gateway.controller.req.PredicateReq;
import com.jgz.gateway.support.ApiResult;

public interface FilterService {

    ApiResult<Integer> add(FilterReq filterReq);

    ApiResult<Void> update(Integer filterId, FilterReq filterReq);

    ApiResult<Void> delete(Integer filterId);
}
