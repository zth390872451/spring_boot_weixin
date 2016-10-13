package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 使用Ribbon实现客户端负载均衡的消费者
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonConsumer {
    /**
     * 创建RestTemplate实例，并通过@LoadBalanced注解开启均衡负载能力。
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    private static final Logger log = LoggerFactory.getLogger(RibbonConsumer.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(RibbonConsumer.class);

        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        Environment env = app.run(args).getEnvironment();
        log.info("server.context-path:{} ",env.getProperty("server.context-path"));

        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),"spring.profiles.active========"+source.containsProperty("spring.profiles.active"));

    }
}
