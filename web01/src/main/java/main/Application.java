
package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;


/**
 * Created by Administrator on 2016/7/23 0023.
 * SpringBootApplication equals( @Configuration ， @EnableAutoConfiguration 和 @ComponentScan )
 *
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    private Environment environment;

    @PostConstruct
    public void initApplication() throws IOException {
        if (environment.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default configuration");
        } else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(environment.getActiveProfiles()));
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        //application.setAdditionalProfiles("dev");
        //app.setShowBanner(false);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
//        addDefaultProfile(app, source);
//
//        ClassPathXmlApplicationContext context=new
//                        ClassPathXmlApplicationContext("/applicationContext.xml");
        Environment env = app.run(args).getEnvironment();
        log.info("server.context-path:{}",env.getProperty("server.context-path"));
        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),"spring.profiles.active========"+source.containsProperty("spring.profiles.active"));
    }
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) throws UnknownHostException {
        if (!source.containsProperty("spring.profiles.active")) {
            if (!InetAddress.getLocalHost().getHostAddress().contains("115.159.184.85")){
                app.setAdditionalProfiles("dev");
                log.warn("加载了dev配置文件");
            }else {
                app.setAdditionalProfiles("prod");
                log.warn("加载了prod配置文件");
            }
//            app.setAdditionalProfiles("dev");
//            log.warn("加载了dev配置文件");
        }else {
            log.warn("已经包含了属性文件 名字列表 source.getPropertyNames()="+source.getPropertyNames());
            source.getPropertyNames();
        }
    }
}

