package main.conf.datasource;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/8/3 0003.
 * 在方法上使用，用于指定使用哪个数据源
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface  TargetDataSource {
    String value();
}
