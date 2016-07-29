package main.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Administrator on 2016/7/29.
 */
@Configuration
@EnableScheduling
public class ScheduleTask {
    Logger logger = LoggerFactory.getLogger(this.getClass());
//    cron表达式:一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。

//    @Scheduled(cron = "0/20 * * * * ?")//每20秒执行一次
    public void schedule(){
        logger.info("schedule");
    }
}
