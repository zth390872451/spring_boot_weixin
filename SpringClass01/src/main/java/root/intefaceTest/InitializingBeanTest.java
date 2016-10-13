package root.intefaceTest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * InitializingBean接口为bean提供了初始化方法的方式
 *
 */
@Component
public class InitializingBeanTest implements InitializingBean {

    public void afterPropertiesSet() throws Exception {
        System.out.println("在初始化bean的时候会执行该方法 ");
    }

    public void testInit(){
        System.out.println(" 测试执行先后顺序" );
    }

    /*public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("/bean.xml");

    }*/
}
