package main.conf.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/8/3 0003.
 * 切换数据源 Advice
 */
@Aspect
@Order(-10)//保证Aop在@Transtactional 之前执行
@Component
public class DynamicDataSourceAspect {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * @Before("@annotation(ds)") ：
     *  @Before ：在方法之前进行执行
     *  @annotation(targetDataSource)：会拦截注解targetDataSource的方法，否则不拦截
     * @param joinPoint
     * @param targetDataSource
     */
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource){

        //获取当前的指定的数据源
        String dsId = targetDataSource.value();
        //如果不在我们注入的所有数据源范围之内，
        //那么输出警告信息，系统自动使用默认的数据源
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)){
            logger.warn("数据源 > {} 不存在，使用默认数据源 ，切入点的签名 = {}"+targetDataSource.value(),joinPoint.getSignature());
        }else {
            logger.warn("使用数据源:{},切入点的签名signature={}",targetDataSource.value(),joinPoint.getSignature());
            //找到了数据源，为当前线程设置数据源(将其设置到动态数据源的上下文中)。
            DynamicDataSourceContextHolder.setDataSourceType(targetDataSource.value());
        }
    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint joinPoint,TargetDataSource targetDataSource){
        logger.info("Revert 数据源：{}，切入点的签名signature：{}",targetDataSource.value(),joinPoint.getSignature());
        //方法完毕后，销毁当前线程所使用的数据源
        DynamicDataSourceContextHolder.clearDataSourceType();
    }




}
