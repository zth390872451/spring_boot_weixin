1.1高可用和域
    Eureka服务器没有后端存储,但注册的服务实例都有发送心跳保持他们的登记日期(这可以在内存中完成)，
    客户也有一个内存中的缓存(所以他们不需要为每一个服务的情况去注册一次)。
    默认情况下eureka服务器也是一个eureka客户端，还需要一个url来定位节点。
1.2单服务模式
    server:
      port: 8761
    
    eureka:
      instance:
        hostname: localhost
      client:
        registerWithEureka: false
        fetchRegistry: false
        serviceUrl:
          defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
          
1.3对等意识
两个对等的eureka服务配置，application.yml配置如下：
    ---
    spring:
      profiles: peer1
    eureka:
      instance:
        hostname: peer1
      client:
        serviceUrl:
          defaultZone: http://peer2/eureka/
    
    ---
    spring:
      profiles: peer2
    eureka:
      instance:
        hostname: peer2
      client:
        serviceUrl:
          defaultZone: http://peer1/eureka/
          
1.4 使用IP

实例名称显示IP配置如下：
    eureka.instance.preferIpAddress=true