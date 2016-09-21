package root.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import root.Application;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/8/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)//引入Junit测试框架
@SpringApplicationConfiguration(classes = Application.class)//指定spring boot的启动类
@WebAppConfiguration//web工程，因此需要模拟上下文ServletContext
public class HelloServiceTest {
    @Resource
    private HelloService helloService;

    @Test
    public void testGetName(){
        Assert.assertEquals("hello",helloService.getName());
    }

}
