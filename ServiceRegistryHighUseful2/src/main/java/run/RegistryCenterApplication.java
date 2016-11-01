package run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务注册中心
 *
 *  @EnableEurekaServer :开启Eureka服务配置，启动一个服务注册中心提供给其他应用进行对话
 * 在默认设置下，该服务注册中心也会将自己作为客户端来尝试注册它自己，
 * 所以我们需要禁用它的客户端注册行为，
 * 只需要在application.properties中问增加 禁用配置。
 * Eureka 缺点：当被注册的服务出现宕机时，
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistryCenterApplication {
    private static final Logger log = LoggerFactory.getLogger(RegistryCenterApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext app = SpringApplication.run(RegistryCenterApplication.class, args);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        Environment env = app.getEnvironment();
        log.info("server.context-path:{} ",env.getProperty("server.context-path"));

        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),"spring.profiles.active========"+source.containsProperty("spring.profiles.active"));
    }
}
