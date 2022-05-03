package com.jgz.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

public interface ReloadService {

    List<RouteDefinition> loadAllEnableRoute();

    void refreshRoute();

}
