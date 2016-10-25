package com.didispace.rabbit.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq 的 配置
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue helloQueue(){
        Queue hello = new Queue("hello");
        System.out.println("hello = " + hello);
        return hello;
    }

}
