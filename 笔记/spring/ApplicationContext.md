理解ApplicationContext
作用：为应用提供配置信息，运行时只读，如果有必要且应用支持，可重新加载(reloaded).
    1、提供BeanFactory的相关方法，用以访问应用中各个组件。
    2、使用通用的模式加载文件资源的能力。
    3、注册监听器并发布事件。
    4、支持国际化。
    5、继承父级上下文。
    
http://zhangzhenting.iteye.com/blog/1827777

加载机制(ContextLoader)：
    1、ContextLoaderListener
    2、ContextLoaderServlet(已经废弃使用)

3种实现子类：
    1、ClassPathXmlApplicationContext
    2、FileSystemXmlApplicationContext
    3、XmlWebApplicationContext

ServletContext与ApplicationContext
http://www.cnblogs.com/onlywujun/articles/2869302.html

servlet 3.0之后，WebApplicationInitializer 可编程式替换web.xml配置
http://datum.iteye.com/blog/1537632 
    <servlet>
 *   <servlet-name>dispatcher</servlet-name>
 *   <servlet-class>
 *     org.springframework.web.servlet.DispatcherServlet
 *   </servlet-class>
 *   <init-param>
 *     <param-name>contextConfigLocation</param-name>
 *     <param-value>/WEB-INF/spring/dispatcher-config.xml</param-value>
 *   </init-param>
 *   <load-on-startup>1</load-on-startup>
 * </servlet>
 *
 * <servlet-mapping>
 *   <servlet-name>dispatcher</servlet-name>
 *   <url-pattern>/</url-pattern>
 * </servlet-mapping>}
 
 
 
   public class MyWebAppInitializer implements WebApplicationInitializer {
  *
  *    &#064;Override
  *    public void onStartup(ServletContext container) {
  *      XmlWebApplicationContext appContext = new XmlWebApplicationContext();
  *      appContext.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
  *
  *      ServletRegistration.Dynamic dispatcher =
  *        container.addServlet("dispatcher", new DispatcherServlet(appContext));
  *      dispatcher.setLoadOnStartup(1);
  *      dispatcher.addMapping("/");
  *    }
  *
   }




