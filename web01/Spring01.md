InitializingBean接口为bean提供了初始化方法的方式.
只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候会执行该方法.
在这个Bean的所有属性值被设置之后，被BeanFactory调用afterPropertiesSet()

关于初始化Bean方法的执行顺序：
Constructor >    使用@PostConstruct注解的方法   > InitializingBean接口方法(afterPropertiesSet)     > init-method指定的方法(使用bean定义的配置属性init-method值)


AbstractRefreshableConfigApplicationContext extends AbstractRefreshableApplicationContext
  implements BeanNameAware, InitializingBean
{
    1、加载指定的配置文件(xml文件资源)
    2、在属性设置完成之后调用 方法refresh(创建加载Spring容器配置).                                          		 		 
}
参考链接 完整介绍Spring 接口信息
http://www.cnblogs.com/forerver-elf/p/4724199.html 

http://www.cnblogs.com/forerver-elf/p/4724218.html

http://blog.csdn.net/bubaxiu/article/details/41380683

Spring定义一套资源加载的接口，并提供了实现类。ResourceLoader接口仅有一个getResource(String location)的方法，可以根据一个资源地址加载文件资源。
不过这个文件资源仅支持带资源类型前缀的表达式，不支持Ant风格的资源路径表达式。ResourcePatternResolver扩展了ResourceLoader接口，定义了一个新的接口方法：getResources(String locationPattern)，该方法支持带资源类型前缀及Ant风格的资源路径的表达式。PathMatchingResourcePatternResolver是Spring提供了标准实现类。 

Spring为BeanFactory提供了多种实现，最常用的XmlBeanFactory。 
BeanFactory最主要的方法就是getBean(String beanName),该方法从容器中返回特定该名称的Bean。BeanFactory的其他接口： 
ListableBeanFactory：该接口定义了访问容器中Bean基本信息的若干方法 
HierarchicalBeanFactory：父子级联IoC容器的接口，子容器可以通过接口方法访问父容器。 
ConfigurableBeanFactory：增强IoC容器的可定制性，它定义了设置类装载其、属性编辑器、容器初始化后置处理器等方法 
AutowireCapableBeanFactory：定义了将容器中的Bean按某种规则进行自动装配的方法 
SingletonBeanRegistry：定义了允许在运行期间向容器注册单实例Bean的方法 
BeanDefinitionRegistry：Spring配置文件中每一个<bean>节点元素在Spring容器里都通过一个BeanDefinition对象表示，他描述了Bean的配置信息。而BeanDefinition Registry接口提供了向容器手工注册BeanDefinition对象的方法。 

ApplicationContext的主要实现类是ClassPathXmlApplicationContext和FileSystemXmlApplicationContext，前者默认从类路径加载配置文件，后者默认从文件系统中装载配置文件。ApplicationContext继承了HierarchicalBeanFactory和ListableBeanFactory接口。 
ApplicationEventPublisher：让容器拥有发布应用上下文事件的功能，包括容器启动事件、关闭事件等。实现了ApplicationListener事件监听接口的Bean可以接受到容器事件，并对事件进行响应处理。在ApplicationContext抽象实现类AbstractApplicationContext中，我们可以发现存在一个ApplicationEventMulticaster，它负责保存所有监听器，以便在容器产生上下文事件时通知这些事件监听者。 
MessageSource：为应用提供il18n国际化消息访问功能。 
ResourcePatternResolver：所有ApplicationContext实现类都实现了类似于PathMatchingResourcePatternResolver功能，可以通过带前缀的Ant风格的资源文件路径装载Spring的配置文件。 

LifeCycle
{
    该接口提供了start()和stop()两个方法，主要用于控制异步处理过程。在具体使用时，ApplicationContext及具体的Bean都必须同时实现该接口，
    ApplicationContext会将start/stop的信息传递给容器中所有实现了该接口的Bean，已达到管理和控制JMX、任务调度等目的。 
}


ConfigurableApplicationContext extends ApplicationContext, Lifecycle, Closeable 
{
    它新增了refresh()和close()，让ApplicationContext具有启动、刷新和关闭应用上下文的能力。
    在应用上下文关闭的情况下调用refresh()即可启动应用上下文，在已经启动的状态下，调用refresh()则清理缓存并重新装载配置信息，
    而调用close()则可关闭应用上下文。 
}

ApplicationContext和BeanFactory的重大区别：BeanFactory在初始化容器时，并未实例化Bean，直到第一次访问某个Bean时才实例目标Bean；而ApplicationContext则在初始化应用上下文时就实例化所有单实例的Bean。
因此，ApplicationContext的初始化事件会比BeanFactory稍长，但之后的调用没有第一次惩罚的问题。 