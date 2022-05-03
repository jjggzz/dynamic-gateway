package com.jgz.gateway.load;

import com.jgz.gateway.service.ReloadService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jgz
 * @version 1.0.0
 * @date 2022/4/29 16:16
 * @description
 */
@DependsOn("routeServiceImpl")
@Component
public class DynamicLoadRouteRepository implements RouteDefinitionRepository {

    @Resource
    private ReloadService reloadService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = reloadService.loadAllEnableRoute();
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
