package com.didispace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/10/25.
 */
@SpringBootApplication
public class RabbitMqApplication {

    public static void main(String[] args) throws UnknownHostException {
//        SpringApplication.run(RabbitMqApplication.class,args);
        SpringApplication app = new SpringApplication(RabbitMqApplication.class);
        Environment env = app.run(args).getEnvironment();
        System.out.println("env server.context-path=" + env.getProperty("server.context-path"));
        System.out.println("Access URLs: http://127.0.0.1:{}\n\t" + env.getProperty("server.port"));
    }
}
