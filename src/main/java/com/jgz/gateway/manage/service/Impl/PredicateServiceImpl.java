package com.jgz.gateway.manage.service.Impl;

import com.jgz.gateway.manage.controller.req.PredicateReq;
import com.jgz.gateway.manage.mapper.PredicateMapper;
import com.jgz.gateway.manage.model.Predicate;
import com.jgz.gateway.manage.service.PredicateService;
import com.jgz.gateway.support.ApiResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PredicateServiceImpl implements PredicateService {

    @Resource
    private PredicateMapper predicateMapper;

    @Override
    public ApiResult<Integer> add(PredicateReq predicateReq) {
        Predicate predicate = new Predicate();
        BeanUtils.copyProperties(predicateReq, predicate);
        predicateMapper.insertSelective(predicate);
        return ApiResult.success(predicate.getId());
    }

    @Override
    public ApiResult<Void> update(Integer predicateId, PredicateReq predicateReq) {
        Predicate predicate = new Predicate();
        BeanUtils.copyProperties(predicateReq, predicate);
        predicate.setId(predicateId);
        predicateMapper.updateByPrimaryKeySelective(predicate);
        return ApiResult.success();
    }

    @Override
    public ApiResult<Void> delete(Integer predicateId) {
        predicateMapper.deleteByPrimaryKey(predicateId);
        return ApiResult.success();
    }
}
