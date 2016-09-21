#一级？
##二级？
###三级？


> 

server.port=8090
server.session-timeout=30
server.context-path=
server.tomcat.max-threads=0
server.tomcat.uri-encoding=UTF-8

spring.datasource.url = jdbc:mysql://localhost:3306/newbirds
spring.datasource.username = root
spring.datasource.password = mymysql
spring.datasource.driverClassName = com.mysql.jdbc.Driver
spring.jpa.database = MYSQL
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

----
>server:
   port: 8090
   session-timeout: 30
   tomcat.max-threads: 0
   tomcat.uri-encoding: UTF-8
 spring:
   datasource:
     url : jdbc:mysql://localhost:3306/newbirds
     username : root
     password : mymysql
     driverClassName : com.mysql.jdbc.Driver
   jpa:
     database : MYSQL
     show-sql : true
     hibernate:
       ddl-auto : update
       naming-strategy : org.hibernate.cfg.ImprovedNamingStrategy
     properties:
       hibernate:
         dialect : org.hibernate.dialect.MySQL5Dialect
         
----
注意点：
1，原有的key，例如spring.jpa.properties.hibernate.dialect，按“.”分割，都变成树状的配置

2，key后面的冒号，后面一定要跟一个空格

3，把原有的application.properties删掉。然后一定要执行一下  maven -X clean install



零散笔记：
1、字节长度
用ISO8859-1编码方式时，一个中/英文都只占一个字节；
采用GB2312或GBK编码方式时，一个中文占两个字节；
而采用UTF-8编码方式时，一个中文占三个字节。

2、aop的作用
配合注解实现权限控制
比如添加一个自定义注解：
@AdminPermission{
value=(super|high|common)
}
管理员权限：定义为超级、高级、一般
实现一个Aop切面，
当发现在控制器或者方法上添加了该注解时，
读取该注解中的值，配合Http参数等信息，
进行相关业务判断，进行权限的控制。



