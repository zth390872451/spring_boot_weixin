package root.intefaceTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/10/13.
 */
@Component
public class ApplicationListenerTest implements ApplicationListener<ContextRefreshedEvent>
{
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        System.out.println("applicationContext = " + applicationContext);
        System.out.println("applicationContext.getParent() = " + applicationContext.getParent());
    }
}
