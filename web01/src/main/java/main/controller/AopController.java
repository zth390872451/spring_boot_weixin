package main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
@RestController
@RequestMapping("aop")
public class AopController {
    @RequestMapping("/aopTest")
    public String aopTest(){
        return "aopTest";
    }
}
