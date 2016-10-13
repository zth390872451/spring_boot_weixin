package root.intefaceTest;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/10/13.
 */
@Component
public class ApplicationListenerTest2  implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEventTest){
            ApplicationEventTest applicationEventTest = (ApplicationEventTest)event;
            String eventMsg = applicationEventTest.getEventMsg();
            System.out.println("监听到发布的事件 信息 eventMsg = " + eventMsg);
        }
    }
 /*
  测试代码
  @SpringBootApplication
    public class Main01 {
        public static void main(String[] args) {
            SpringApplication.run(Main01.class, args).publishEvent(new ApplicationEventTest("testEvent"));
        }
    }*/


}
