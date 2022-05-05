package com.jgz.gateway.manage.service.Impl;

import com.jgz.gateway.manage.controller.req.FilterReq;
import com.jgz.gateway.manage.mapper.FilterMapper;
import com.jgz.gateway.manage.model.Filter;
import com.jgz.gateway.manage.service.FilterService;
import com.jgz.gateway.support.ApiResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FilterServiceImpl implements FilterService {

    @Resource
    private FilterMapper filterMapper;

    @Override
    public ApiResult<Integer> add(FilterReq filterReq) {
        Filter filter = new Filter();
        BeanUtils.copyProperties(filterReq,filter);
        filterMapper.insertSelective(filter);
        return ApiResult.success(filter.getId());
    }

    @Override
    public ApiResult<Void> update(Integer filterId, FilterReq filterReq) {
        Filter filter = new Filter();
        BeanUtils.copyProperties(filterReq,filter);
        filter.setId(filterId);
        filterMapper.updateByPrimaryKeySelective(filter);
        return ApiResult.success();
    }

    @Override
    public ApiResult<Void> delete(Integer filterId) {
        filterMapper.deleteByPrimaryKey(filterId);
        return ApiResult.success();
    }
}
