Spring源码分析：BeanFactory体系之接口详细分析
http://www.cnblogs.com/zrtqsk/p/4028453.html

BeanFactory：以各种方式获取Bean实例及其属性
拥有如下三个一级子接口
{
    ListableBeanFactory: 最大的特点就是可以列出工厂可以生产的所有实例
    HierarchicalBeanFactory：1、工厂的分层 2、判断本地工厂是否包含这个Bean（忽略其他所有父工厂）。
    AutowireCapableBeanFactory：自动装配的功能，根据类定义BeanDefinition装配Bean、执行前、后处理器等
}


二级子接口
{
    ConfigurableBeanFactory：复杂的配置
        {
            继承了 HierarchicalBeanFactory(层级) 和 SingletonBeanRegistry(注册单例Bean） 两个接口
            并自定义了 37个方法（包含了工厂创建、注册一个Bean的众多细节）。总共54个方法。
        }
}

三级子接口
{
    ConfigurableListableBeanFactory：集大成者
    {
            继承了 ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory 三个接口
            这个工厂接口总共有83个方法，实在是巨大到不行了。
            这个工厂接口的自有方法总体上只是对父类接口功能的补充，包含了BeanFactory体系目前的所有方法，可以说是接口的集大成者
    }
}

扩展接口：
{
        BeanDefinitionRegistry： 用来操作定义在工厂内部的BeanDefinition 
        {
            继承了 AliasRegistry接口（对Bean的别名进操作）
        }
}
