package main.conf.datasource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/4 0004.
 * 动态数据源注册
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar,
        EnvironmentAware{
    //如若哦诶之文件中未指定数据源类型，则使用该默认值
    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";

    private ConversionService conversionService = new DefaultConversionService();

    private PropertyValues propertyValues;
    //默认数据源
    private DataSource defaultDataSource;
    //存储 自定义的数据源
    private Map<String,DataSource> customDataSources = new HashMap<String, DataSource>();



    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initCustomDataSources(environment);
    }

    /**
     * 初始化 自定义的数据源
     * @param environment
     */
    private void initCustomDataSources(Environment environment) {
        //读取配置文件获取更多的数据源，也可以读取数据库获取更多的数据源
        //从上下文环境environment的前缀为spring.datasource.获取属性解析器
        RelaxedPropertyResolver relaxedPropertyResolver = new RelaxedPropertyResolver(environment,"custom.datasource.");

        String dsPrefixs = relaxedPropertyResolver.getProperty("names");
        for (String dsPrefix : dsPrefixs.split(",")) {//多个数据源
            Map<String,Object> dsMap = relaxedPropertyResolver.getSubProperties(dsPrefix+".");//根据前缀获取键值对
            DataSource ds = buildDataSource(dsMap);//创建数据源
            customDataSources.put(dsPrefix,ds);//将创建好的数据源存储入内存中
            dataBinder(ds,environment);//为数据源 绑定更多的数据
        }
    }

    /**
     * 初始化 默认的数据源(主数据源)
     * @param environment
     */
    private void initDefaultDataSource(Environment environment) {
        RelaxedPropertyResolver relaxedPropertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
        Map<String,Object> dsMap = new HashMap<String ,Object>();
        dsMap.put("type",relaxedPropertyResolver.getProperty("type"));
        dsMap.put("driverClassName",relaxedPropertyResolver.getProperty("driverClassName"));
        dsMap.put("url",relaxedPropertyResolver.getProperty("url"));
        dsMap.put("username",relaxedPropertyResolver.getProperty("username"));
        dsMap.put("password",relaxedPropertyResolver.getProperty("password"));
        //创建数据源
        defaultDataSource = buildDataSource(dsMap);
        //为数据源绑定更多的数据
        dataBinder(defaultDataSource,environment);
    }

    /**
     *  为DataSource绑定更多数据
     * @param defaultDataSource
     * @param environment
     */
    private void dataBinder(DataSource defaultDataSource, Environment environment) {
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(defaultDataSource);
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);
        dataBinder.setIgnoreInvalidFields(false);
        dataBinder.setIgnoreUnknownFields(true);

        if (propertyValues==null){
            Map<String,Object> rpr = new RelaxedPropertyResolver(environment,
                    "spring.datasource").getSubProperties(".");
            Map<String,Object> values = new HashMap<>(rpr);
            //排除已经设置好的属性
            values.remove("type");
            values.remove("driverClassName");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            //易变的属性容器
            propertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(propertyValues);//
    }

    /**
     * 根据 参数创建数据源
     * @param dsMap
     * @return
     */
    private DataSource buildDataSource(Map<String, Object> dsMap) {
        Object type = dsMap.get("type");
        if (type==null){
            type = DATASOURCE_TYPE_DEFAULT;//默认数据源
        }
        Class<? extends DataSource> dataSourceType;
        try {
            dataSourceType = (Class<? extends DataSource>)Class.forName((String) type);
            String driverClassName = dsMap.get("driverClassName").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();
            DataSourceBuilder factory = DataSourceBuilder.create()
                    .url(url).driverClassName(driverClassName)
                    .password(password).type(dataSourceType)
                    .username(username);
            return factory.build();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("创建数据源失败 e.getMessage() = " + e.getMessage());
        }
        return null;
    }

    /**
     *
     * 注册数据源 到动态数据源上下文中
     * @param annotationMetadata
     * @param beanDefinitionRegistry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //存储所有的数据源
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put("dataSource",defaultDataSource);
        //将 主数据源 加入管理
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        targetDataSources.putAll(customDataSources);
        //将自定义的数据源 加入管理
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        //创建动态数据源DynamicDataSource
        GenericBeanDefinition beanDefinition =  new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        //获取可变属性值集合
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();

        //添加属性：AbstractRoutingDataSource.defaultTargetDataSource
        mpv.addPropertyValue("defaultTargetDataSource",defaultDataSource);
        mpv.addPropertyValue("targetDataSources",targetDataSources);

        beanDefinitionRegistry.registerBeanDefinition("dataSource",beanDefinition);
    }
}
