package com.didispace.rabbit.controller;

import com.didispace.rabbit.service.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/10/25.
 */
@RestController
@RequestMapping("/send")
public class SendController {
    @Autowired
    private Sender sender;
    @RequestMapping("/hello")
    public String send(){
        sender.send();
        return "success";
    }
}
