package com.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2016/9/27.
 */
@Service
public class HystrixService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "failExecute")
    public String addHystrix(){
        return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=10&b=20",String.class).getBody();
    }


    public String failExecute(){
        return "404 error";
    }

}
