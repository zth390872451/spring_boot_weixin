package main.intefaceTest;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * InitializingBean接口为bean提供了初始化方法的方式
 *
 */
//@Component
public class InitializingBeanTest implements InitializingBean  {


    public void initMethod(){
        System.out.println("InitializingBeanTest.initMethod调用");
    }

    public InitializingBeanTest(){
        System.out.println(" Bean Construct 调用!");
    }


    public void afterPropertiesSet() throws Exception {
//        ClassPathXmlApplicationContext
        System.out.println("InitializingBean 接口方法调用");
    }

    @PostConstruct
    public void testPostConstruct(){
        System.out.println(" PostConstruct 注解方法调用" );
    }
}
