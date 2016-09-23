package main.conf;

import main.filter.ShiroDatabaseRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.util.LinkedHashMap;
import java.util.Map;

//@Configuration
public class ShiroConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

	//LifecycleBeanPostProcessor用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调
	// 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调。
	// 如UserRealm就实现了Initializable，而DefaultSecurityManager实现了Destroyable。
	@Bean
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor(){
		logger.info("init Bean LifecycleBeanPostProcessor");
		return new LifecycleBeanPostProcessor();
	}


	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager){
		logger.info("init Bean AuthorizationAttributeSourceAdvisor");
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return new AuthorizationAttributeSourceAdvisor();
	}


	@Bean
	public AuthorizingRealm getShiroRealm(){
		logger.info("init Bean ShiroDatabaseRealm");
		return  new ShiroDatabaseRealm();
	}

	@Bean
	public EhCacheManager getEhCacheManager(){
		logger.info("init Bean EhCacheManager");
		EhCacheManager ehCacheManager = new EhCacheManager();
		//  <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");//classpath:org/apache/shiro/cache/ehcache/ehcache.xml
		return ehCacheManager;
	}

	@Bean
	public SecurityManager SecurityManager(Realm realm, CacheManager cacheManager){
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(realm);
		dwsm.setCacheManager(cacheManager);
		return dwsm;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		logger.info("init Bean ShiroFilterFactoryBean");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		//定义一系列关于URL的规则和访问权限
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		//配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/web/*", "anon");
		filterChainDefinitionMap.put("/*", "authc");
		//全部不进行认证
//		filterChainDefinitionMap.put("/web/*", "anon");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/main");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/login");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}



}
