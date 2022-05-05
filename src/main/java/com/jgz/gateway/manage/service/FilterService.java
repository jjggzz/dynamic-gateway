package com.jgz.gateway.manage.service;

import com.jgz.gateway.manage.controller.req.FilterReq;
import com.jgz.gateway.support.ApiResult;

public interface FilterService {

    ApiResult<Integer> add(FilterReq filterReq);

    ApiResult<Void> update(Integer filterId, FilterReq filterReq);

    ApiResult<Void> delete(Integer filterId);
}
