package main.controller;

import main.annotation.PermissionAccessException;
import main.domain.Admin;
import main.service.AdminService;
import main.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2016/7/29.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private AdminService adminService;
//    @Autowired
    private MailUtil mailUtil;

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("sendMail")
    public String mai() throws Exception {
        mailUtil.sendMail(null,null,null,null);
        return "success";
    }

    @ExceptionHandler(value = PermissionAccessException.class)
    @ResponseBody
    public String handleError(HttpServletRequest req, PermissionAccessException exception) {
//        return new ResponseEntity("权限访问禁止", HttpStatus.FORBIDDEN);
        return "权限访问禁止";
    }

    //同步和异步处理与效率无直接关系
    //spring异步的出现目的：解决阻塞竞争问题。请求线程将处理过程放入队列中，然后重新回到请求线程池中
    //这样后面的来的请求就不会阻塞。而所有的对请求的响应将会延迟处理。
    @RequestMapping("/callable")
    public Callable<String> callable(final long id){
        logger.info("*******当前线程id={}!",Thread.currentThread().getId());

        return new Callable<String>(){
            @Override
            public String call() throws Exception {
                Admin admin = adminService.getOne(id);
                Thread.sleep(3 * 1000L);
                logger.info("当前线程id={}!",Thread.currentThread().getId());
                return "success";
            }
        };
    }
}
