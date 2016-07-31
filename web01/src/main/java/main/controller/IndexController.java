package main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
@RestController
public class IndexController {

    @RequestMapping({"/","/index"})
    public String index(){
        return "/index";
    }
}
