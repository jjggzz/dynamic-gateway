package com.jgz.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.jgz.gateway.mapper"})
@SpringBootApplication
public class DynamicGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicGatewayApplication.class, args);
    }

}
