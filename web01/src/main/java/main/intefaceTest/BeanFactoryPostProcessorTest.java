package main.intefaceTest;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Bean工厂的处理器
 */
@Component
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {
    /**
     * 后置处理方法：在Bean类的构造函数调用之前调用
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition bd = beanFactory.getBeanDefinition("commonConfiguration");
        MutablePropertyValues propertyValues = bd.getPropertyValues();
        propertyValues.addPropertyValue("testName","test01");
        System.out.println("postProcessBeanFactory = " + propertyValues);

    }
}
