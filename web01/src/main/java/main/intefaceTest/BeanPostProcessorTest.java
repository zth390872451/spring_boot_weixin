package main.intefaceTest;

import main.conf.CommonConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Bean的处理器
 */
@Component
public class BeanPostProcessorTest implements BeanPostProcessor{
    /**
     *
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CommonConfiguration){
            System.out.println(" Common Service has been initialized " );
            CommonConfiguration cs = (CommonConfiguration)bean;
            System.out.println("postProcessBeforeInitialization  cs.TestName = " + cs.getTestName());
            cs.setTestName("postProcessBeforeInitialization");
            System.out.println("cs.getTestName() = " + cs.getTestName());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CommonConfiguration){
            System.out.println(" Common Service has been initialized " );
            CommonConfiguration cs = (CommonConfiguration)bean;
            System.out.println("postProcessAfterInitialization  cs.TestName = " + cs.getTestName());
            cs.setTestName("postProcessAfterInitialization");
            System.out.println("cs.getTestName() = " + cs.getTestName());
        }
        return bean;
    }
}
