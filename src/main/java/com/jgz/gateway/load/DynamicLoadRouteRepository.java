package com.jgz.gateway.load;

import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jgz
 * @version 1.0.0
 * @date 2022/4/29 16:16
 * @description
 */
@Component
public class DynamicLoadRouteRepository implements RouteDefinitionRepository {


    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();

        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId("jtt808-manage");
        routeDefinition.setOrder(1000);
        try {
            routeDefinition.setUri(new URI("http://192.168.1.129:8125"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // 断言
        List<PredicateDefinition> predicateDefinitions = new ArrayList<>();
        predicateDefinitions.add(new PredicateDefinition("Path=/admin/**"));
        routeDefinition.setPredicates(predicateDefinitions);

        // 过滤器
        List<FilterDefinition> filterDefinitions = new ArrayList<>();
        filterDefinitions.add(new FilterDefinition("PreserveHostHeader"));
        routeDefinition.setFilters(filterDefinitions);


        routeDefinitions.add(routeDefinition);

        System.out.println("refresh route..............................");

        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
