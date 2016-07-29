package main.controller;

import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class TemplateController {

    @RequestMapping("/test")
    public String helloHtml(Map<String,Object> map,HttpServletRequest httpServletRequest){

       /* , HttpServletRequest httpServletRequest ServletContext servletContext = httpServletRequest.getServletContext();
        System.out.println("servletContext.getContextPath() = " + servletContext.getContextPath());*/
        map.put("hello","from TemplateController.helloHtml");
        return "/test";
    }
}