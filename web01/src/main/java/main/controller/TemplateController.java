package main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class TemplateController {

    @RequestMapping("/test")
    public String helloHtml(Map<String,Object> map,HttpServletRequest httpServletRequest){

       /* , HttpServletRequest httpServletRequest ServletContext servletContext = httpServletRequest.getServletContext();
        System.out.println("servletContext.getContextPath() = " + servletContext.getContextPath());*/
        map.put("hello","from TemplateController.helloHtml");
        return "/test";
    }
}