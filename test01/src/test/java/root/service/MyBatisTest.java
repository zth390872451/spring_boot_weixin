package root.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import root.Application;
import root.domain.User;
import root.mapper.UserMapper;

/**
 * Created by Administrator on 2016/8/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MyBatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void findByName()throws Exception{
        userMapper.insert("zth",20);
        User user = userMapper.findByName("zth");
        Assert.assertEquals(20,user.getAge().intValue());
    }

}
