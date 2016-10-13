package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Feign是一个声明式的Web Service客户端，它使得编写Web Serivce客户端变得更加简单。
 * 我们只需要使用Feign来创建一个接口并用注解来配置它既可完成。它具备可插拔的注解支持，
 * 包括Feign注解和JAX-RS注解。Feign也支持可插拔的编码器和解码器。
 * Spring Cloud为Feign增加了对Spring MVC注解的支持，
 * 还整合了Ribbon和Eureka来提供均衡负载的HTTP客户端实现。
 * @EnableCircuitBreaker 与 @EnableHystrix 等价
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker //开启断路器
@EnableHystrixDashboard //开启断路器的监控板
public class FeignConsumerApplication {
    /**
     * 创建RestTemplate实例，并通过@LoadBalanced注解开启均衡负载能力。
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    private static final Logger log = LoggerFactory.getLogger(FeignConsumerApplication.class);

        public static void main(String[] args) {
            SpringApplication.run(FeignConsumerApplication.class, args);
        }
}
