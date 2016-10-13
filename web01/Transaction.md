spring transaction源码分析--事务架构

http://www.cnblogs.com/davidwang456/p/4309038.html

事务特性：
	ACID
事务类型：
	JDBC事务、{控制、事务模式、缺点}
	JTA事务、{}
	容器事务。
	三种Java事务差异。
	
	
spring事务架构
	事务管理PlatformTransactionManager的架构
	事务定义TransactionDefinition的架构
		事务传播行为类型
		事务隔离级别
	
	
spring事务实现机制
 1 高层

     比较好的方式有：1.基于持久层api的模板方法；2.使用具有事务工厂bean的本地ORM api；3使用代理管理本地资源工厂。

 2 底层

      DataSourceUtils (用作JDBC事务), EntityManagerFactoryUtils (用作JPA事务), SessionFactoryUtils (用作Hibernate事务),PersistenceManagerFactoryUtils (用作JDO事务)等等，
	  
  3 最低层
   TransactionAwareDataSourceProxy是事务的最底层，它代理了DataSource，并增加了spring管理事务功能。	  