package main.filter.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 实现web层的日志切面
 *Aspectj内置的5中通知类型的四种：
 * before（连接点执行前通知）
 * after returning(连接点正常完成后通知)，
 * after throwing(连接点执行中发生异常后通知)，
 * after(连接点完成后通知，无论正常还是异常)。
 * 另外一种是around(环绕通知，在连接点执行前，执行完成后都通知)。
 */
@Aspect // 定义切面类
@Component
@Order(-5) /*标识切面的优先级，值越高，优先级越高
（在切入点前的操作，按order的值由小到大执行;
在切入点后的操作，按order的值由大到小执行）*/
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    //定义切点：公开 任意返回值 main.controller 包下的所有类 的以aop开头的任意参数的方法
//    @Pointcut("execution(public * main.controller.*.*aop(..) )")
    @Pointcut("execution(public * main.controller..*.*(..))")
    public void webLog(){

    }
    //@Before在切入点开始处切入内容
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        logger.info("WebLogAspect.doBefore()");
        //接受请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        //记录请求内容
        logger.info("URL:**"+httpServletRequest.getRequestURI().toString());
        logger.info("HTTP_METHOD:**"+httpServletRequest.getMethod());
        logger.info("RemoteAddr:**"+httpServletRequest.getRemoteAddr());
        logger.info("Class_Method:**"+joinPoint.getSignature().getDeclaringTypeName());
        logger.info("UserPrincipal:**"+httpServletRequest.getUserPrincipal());
        logger.info("QueryString:**"+httpServletRequest.getQueryString());
        logger.info("Args:**"+ Arrays.toString(joinPoint.getArgs()));

        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String paramName = parameterNames.nextElement();
            System.out.println("paramName = " + httpServletRequest.getParameter(paramName));
        }

    }

    //@After在切入点结尾处切入内容
    @After("webLog()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("WebLogApsect.doAfter");
    }

    @AfterReturning("webLog()")
    public void AfterReturning(JoinPoint joinPoint){
        logger.info("WebLogApsect.AfterReturning");
    }
//    切入点前后切入内容，并自己控制何时执行切入点自身的内容
    @Around("webLog()")
    public void Around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("WebLogApsect.Around before");
         joinPoint.proceed();
        logger.info("WebLogApsect.Around after");

    }

}
