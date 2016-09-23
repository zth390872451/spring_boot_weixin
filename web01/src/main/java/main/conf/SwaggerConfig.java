package main.conf;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.rowset.Predicate;

/**
 * Created by Administrator on 2016/9/22.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        Docket docket= new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
//                .paths(Predicates.and(PathSelectors.regex("/web/user/")))
                .build();
        ApiSelectorBuilder select = docket.select();
        System.out.println("select = " + select);
        System.out.println("docket = " + docket);
        return docket;
    }

//    @Bean
    public Docket testApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(Predicates.or(PathSelectors.regex("/api/.*")))//过滤的接口
                .build()
                .apiInfo(testApiInfo());
        ;
        return docket;
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("标题")
                .description("描述")
                .contact("联系人")
                .version("版本号")
                .termsOfServiceUrl("API地址Url")
                .build();
    }

    private ApiInfo testApiInfo() {
        ApiInfo apiInfo = new ApiInfo("Test相关接口",//大标题
                "Test相关接口，主要用于测试.",//小标题
                "1.0",//版本
                "http://412887952-qq-com.iteye.com/",
                "Angel",//作者
                "北京知远信息科技有限公司",//链接显示文字
                "http://www.kfit.com.cn/"//网站链接
        );

        return apiInfo;
    }

    private ApiInfo demoApiInfo() {
        ApiInfo apiInfo = new ApiInfo("Demo相关接口",//大标题
                "Demo相关接口，获取个数，获取列表，注意：",//小标题
                "1.0",//版本
                "http://412887952-qq-com.iteye.com/",
                "Angel",//作者
                "北京知远信息科技有限公司",//链接显示文字
                "http://www.kfit.com.cn/"//网站链接
        );
        return apiInfo;
    }
}
