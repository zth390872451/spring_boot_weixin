package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableDiscoveryClient 注解:
 * 该注解能激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出。
 * Eureka提供了Application Service 客户端的自行注册功能。
 * Eureka
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProvider01Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceProvider01Application.class).web(true).run(args);
    }
}
