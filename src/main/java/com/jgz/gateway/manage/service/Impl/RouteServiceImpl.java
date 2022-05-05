package com.jgz.gateway.manage.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jgz.gateway.manage.controller.req.RouteIndexReq;
import com.jgz.gateway.manage.controller.req.RouteReq;
import com.jgz.gateway.manage.controller.res.RouteRes;
import com.jgz.gateway.manage.mapper.FilterMapper;
import com.jgz.gateway.manage.mapper.PredicateMapper;
import com.jgz.gateway.manage.mapper.RouteMapper;
import com.jgz.gateway.manage.model.*;
import com.jgz.gateway.manage.service.ReloadService;
import com.jgz.gateway.manage.service.RouteService;
import com.jgz.gateway.support.ApiResult;
import com.jgz.gateway.support.PageRet;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService, ReloadService {

    @Resource
    private RouteMapper routeMapper;

    @Resource
    private PredicateMapper predicateMapper;

    @Resource
    private FilterMapper filterMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public List<RouteDefinition> loadAllEnableRoute() {
        // 获取所有启用的路由配置
        RouteExample routeExample = new RouteExample();
        routeExample.createCriteria()
                .andStateEqualTo(Boolean.TRUE);
        List<Route> allEnableRoute = routeMapper.selectByExample(routeExample);
        if (CollectionUtils.isEmpty(allEnableRoute)) {
            return Collections.emptyList();
        }

        List<Integer> routeIdList = allEnableRoute.stream().map(Route::getId).collect(Collectors.toList());

        // 获取断言信息并分组
        PredicateExample predicateExample = new PredicateExample();
        predicateExample.createCriteria()
                .andRouteIdIn(routeIdList);
        Map<Integer, List<Predicate>> predicateMap = predicateMapper.selectByExample(predicateExample).stream()
                .collect(Collectors.groupingBy(Predicate::getRouteId));

        // 获取过滤器并分组
        FilterExample filterExample = new FilterExample();
        filterExample.createCriteria()
                .andRouteIdIn(routeIdList);
        Map<Integer, List<Filter>> filterMap = filterMapper.selectByExample(filterExample).stream()
                .collect(Collectors.groupingBy(Filter::getRouteId));

        return allEnableRoute.stream()
                .map(item -> {
                    // 映射路由配置
                    RouteDefinition routeDefinition = new RouteDefinition();
                    routeDefinition.setId(item.getUkey());
                    if (Objects.nonNull(item.getOrd())) {
                        routeDefinition.setOrder(item.getOrd());
                    }
                    routeDefinition.setUri(URI.create(item.getUri()));

                    // 映射断言配置
                    List<Predicate> predicates = predicateMap.get(item.getId());
                    List<PredicateDefinition> predicateDefinitionList = Optional.ofNullable(predicates).orElseGet(ArrayList::new).stream()
                            .map(predicate -> new PredicateDefinition(predicate.getUkey() + "=" + predicate.getValue())).collect(Collectors.toList());
                    routeDefinition.setPredicates(predicateDefinitionList);

                    // 映射过滤器配置
                    List<Filter> filters = filterMap.get(item.getId());
                    List<FilterDefinition> filterDefinitionList = Optional.ofNullable(filters).orElseGet(ArrayList::new).stream()
                            .map(filter -> {
                                String text;
                                if (StringUtils.hasText(filter.getValue())) {
                                    text = filter.getName() + "=" + filter.getValue();
                                } else {
                                    text = filter.getName();
                                }
                                return new FilterDefinition(text);
                            }).collect(Collectors.toList());
                    routeDefinition.setFilters(filterDefinitionList);

                    return routeDefinition;

                }).collect(Collectors.toList());
    }

    @Override
    public void refreshRoute() {
        redisTemplate.convertAndSend("redis.refreshRoute", "refresh");
    }

    @Override
    public ApiResult<Integer> add(RouteReq routeReq) {
        RouteExample routeExample = new RouteExample();
        routeExample.createCriteria()
                .andUkeyEqualTo(routeReq.getUkey());
        int count = routeMapper.countByExample(routeExample);
        if (count > 0) {
            return ApiResult.fail(HttpStatus.BAD_REQUEST.value(),"路由已存在");
        }

        Route route = new Route();
        BeanUtils.copyProperties(routeReq, route);
        routeMapper.insertSelective(route);
        return ApiResult.success(route.getId());
    }

    @Override
    public ApiResult<Void> update(Integer routeId, RouteReq routeReq) {
        Route route = new Route();
        BeanUtils.copyProperties(routeReq, route);
        route.setId(routeId);
        routeMapper.updateByPrimaryKeySelective(route);
        return ApiResult.success();
    }

    @Override
    public ApiResult<Void> delete(Integer routeId) {
        routeMapper.deleteByPrimaryKey(routeId);
        return ApiResult.success();
    }

    @Override
    public ApiResult<PageRet<RouteRes>> index(RouteIndexReq routeIndexReq) {
        RouteExample routeExample = new RouteExample();
        RouteExample.Criteria criteria = routeExample.createCriteria();
        if (StringUtils.hasText(routeIndexReq.getUkey())) {
            criteria.andUkeyLike("%" + routeIndexReq.getUkey() + "%");
        }
        if (StringUtils.hasText(routeIndexReq.getUri())) {
            criteria.andUriLike("%" + routeIndexReq.getUri() + "%");
        }

        Page<Route> page = PageHelper.startPage(routeIndexReq.getPage(), routeIndexReq.getSize());
        routeMapper.selectByExample(routeExample);

        List<RouteRes> list = page.stream()
                .map(item -> {
                    RouteRes routeRes = new RouteRes();
                    BeanUtils.copyProperties(item, routeRes);
                    return routeRes;
                }).collect(Collectors.toList());

        PageRet<RouteRes> res = new PageRet<RouteRes>()
                .setPageNum(page.getPageNum())
                .setPageSize(page.getPageSize())
                .setTotal((int) page.getTotal())
                .setData(list);

        return ApiResult.success(res);
    }

    @Override
    public ApiResult<Void> enable(Integer routeId) {
        PredicateExample predicateExample = new PredicateExample();
        predicateExample.createCriteria()
                .andRouteIdEqualTo(routeId);
        int count = predicateMapper.countByExample(predicateExample);
        if (count == 0) {
            return ApiResult.fail(HttpStatus.NOT_FOUND.value(),"路由未配置断言规则");
        }


        Route route = new Route();
        route.setId(routeId);
        route.setState(Boolean.TRUE);
        routeMapper.updateByPrimaryKeySelective(route);
        return ApiResult.success();
    }

    @Override
    public ApiResult<Void> disable(Integer routeId) {
        Route route = new Route();
        route.setId(routeId);
        route.setState(Boolean.FALSE);
        routeMapper.updateByPrimaryKeySelective(route);
        return ApiResult.success();
    }

    @Override
    public ApiResult<RouteRes> get(Integer routeId) {
        Route route = routeMapper.selectByPrimaryKey(routeId);
        if (Objects.isNull(route)) {
           return ApiResult.fail(HttpStatus.NOT_FOUND.value(),"未查询到路由信息");
        }

        RouteRes routeRes = new RouteRes();
        BeanUtils.copyProperties(route, routeRes);

        FilterExample filterExample = new FilterExample();
        filterExample.createCriteria()
                .andRouteIdEqualTo(routeId);
        List<Filter> filters = filterMapper.selectByExample(filterExample);
        List<RouteRes.FilterRes> filterResList = filters.stream()
                .map(item -> {
                    RouteRes.FilterRes filterRes = new RouteRes.FilterRes();
                    BeanUtils.copyProperties(item, filterRes);
                    return filterRes;
                })
                .collect(Collectors.toList());
        routeRes.setFilterResList(filterResList);

        PredicateExample predicateExample = new PredicateExample();
        predicateExample.createCriteria()
                .andRouteIdEqualTo(routeId);
        List<Predicate> predicates = predicateMapper.selectByExample(predicateExample);
        List<RouteRes.PredicateRes> predicateResList = predicates.stream()
                .map(item -> {
                    RouteRes.PredicateRes predicateRes = new RouteRes.PredicateRes();
                    BeanUtils.copyProperties(item, predicateRes);
                    return predicateRes;
                })
                .collect(Collectors.toList());
        routeRes.setPredicateResList(predicateResList);

        return ApiResult.success(routeRes);
    }
}
