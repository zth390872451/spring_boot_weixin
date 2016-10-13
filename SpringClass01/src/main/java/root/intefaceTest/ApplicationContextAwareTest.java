package root.intefaceTest;

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
    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
        System.out.println("applicationContext = " + applicationContext);
    }

    @Override
    public void destroy() throws Exception {
        appContext = null;
    }

}
