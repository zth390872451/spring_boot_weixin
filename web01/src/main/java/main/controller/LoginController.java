package main.controller;

import main.conf.ShiroConfiguration;
import main.util.MailUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/7/25 0025.
 */
@Controller
public class LoginController {

    @Autowired
    private MailUtil mailUtil;

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @RequestMapping("/login")
    public String login(String username, String password, ModelMap modelMap){
        mailUtil.sendMail();
        //获取当前线程中的subject，判断是否认证成功
        boolean isAuth = SecurityUtils.getSubject().isAuthenticated();
        String msg ="";
        if (!isAuth){//未认证或者失败、失效
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
                modelMap.addAttribute("msg","参数错误！");
                return "login";
            }else {
                try {
                    UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
                    SecurityUtils.getSubject().login(usernamePasswordToken);
                }catch (Exception exception){
                    if (UnknownAccountException.class.getName().equals(exception)) {
                        logger.info("UnknownAccountException -- > 账号不存在：");
                        msg = "UnknownAccountException -- > 账号不存在：";
                    } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                        logger.info("IncorrectCredentialsException -- > 密码不正确：");
                        msg = "IncorrectCredentialsException -- > 密码不正确：";
                    } else if ("kaptchaValidateFailed".equals(exception)) {
                        logger.info("kaptchaValidateFailed -- > 验证码错误");
                        msg = "kaptchaValidateFailed -- > 验证码错误";
                    } else {
                        msg = "else >> "+exception;
                        logger.info("else -- >" + exception);
                    }
                    logger.info("登录失败，e={}",exception.getMessage());
                    modelMap.addAttribute("msg",msg);
                    return "login";
                }
                return "home";
            }
        }else{//认证成功
            return "home";
        }
    }
}
