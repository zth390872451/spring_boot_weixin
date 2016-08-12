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