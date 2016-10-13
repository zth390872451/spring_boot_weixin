package com.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/10/13.
 */
@Component
public class ApplicationContextAwareTest implements DisposableBean,ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.setApplicationContext(applicationContext);
    }

    @Override
    public void destroy() throws Exception {
        applicationContext = null;
    }
}
