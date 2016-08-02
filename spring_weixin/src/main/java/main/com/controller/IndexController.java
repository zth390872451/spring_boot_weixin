package main.com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
@RestController("/")
public class IndexController {

    @RequestMapping("")
    public String index(){
        return "welcome my website!";
    }
}
