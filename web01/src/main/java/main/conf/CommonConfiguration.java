package main.conf;

import jxl.write.biff.StyleXFRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Bean初始化的配置测试
 */
@Component
public class CommonConfiguration {
    @Value("${common.testName}")
    private String testName;
    @Value("${common.testPassword}")
    private String testPassword;

    public CommonConfiguration() {
        System.out.println("construct init! testName = " + testName);
    }


    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestPassword() {
        return testPassword;
    }

    public void setTestPassword(String testPassword) {
        this.testPassword = testPassword;
    }
}
