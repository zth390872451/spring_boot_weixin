package main.intefaceTest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 *
 */

public class BeanFactoryAwareTest implements BeanFactoryAware {

    private static  BeanFactory beanFactory;
    public static int count = 0;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("count++ = " + count++);
        this.beanFactory = beanFactory;
    }
}
