package com.controller;

import com.service.ComputeClient;
import com.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2016/9/26.
 */
@RestController
public class ConsumerController {
    @Autowired
    private ComputeClient computeClient;

    @Autowired
    private HystrixService hystrixService;

    @RequestMapping(value = "/computeClient",method = RequestMethod.GET)
    public String add(){
        return computeClient.add(1,2);
    }

    //添加断路器
    @RequestMapping(value = "/hystrixService",method = RequestMethod.GET)
    public String addHystrix(){
        return hystrixService.addHystrix();
    }

}
