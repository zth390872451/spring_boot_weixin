BeanFactory：
    定义了 IOC 容器的最基本形式，并提供了 IOC 容器应遵守的的最基本的接口。
FactoryBean：
    一般情况下，Spring 通过反射机制利用 <bean> 的 class 属性指定实现类实例化 Bean ，
在某些情况下，实例化 Bean 过程比较复杂，如果按照传统的方式，则需要在 <bean> 中提供大量的配置信息。
配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。
 Spring 为此提供了一个 org.springframework.bean.factory.FactoryBean 的工厂类接口，
 用户可以通过实现该接口定制实例化 Bean 的逻辑。
 
区别
     BeanFactory是个 Factory ，也就是 IOC 容器或对象工厂， FactoryBean 是个 Bean 。
     在 Spring 中，所有的 Bean 都是由 BeanFactory( 也就是 IOC 容器 ) 来进行管理的。
     但对 FactoryBean 而言，这个 Bean 不是简单的 Bean ，而是一个能生产或者修饰对象生成的工厂 Bean, 
     它的实现与设计模式中的工厂模式和修饰器模式类似。
     
Spring中有两种类型的Bean，一种是普通Bean，另一种是工厂Bean，即FactoryBean，这两种Bean都被容器管理，
但工厂Bean跟普通Bean不同，其返回的对象不是指定类的一个实例，其返回的是该FactoryBean的getObject方法所返回的对象。
在Spring框架内部，有很多地方有FactoryBean的实现类，
它们在很多应用如(Spring的AOP、ORM、事务管理)及与其它第三框架(ehCache)集成时都有体现