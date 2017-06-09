package com.orrin.springboot.shiro.config;

import com.orrin.springboot.shiro.core.shiro.RetryLimitHashedCredentialsMatcher;
import com.orrin.springboot.shiro.core.shiro.ServerFormAuthenticationFilter;
import com.orrin.springboot.shiro.core.shiro.UserRealm;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Orrin on 2017/6/8.
 */
@Configuration
@EnableAutoConfiguration
public class ShiroConfiguration implements EnvironmentAware {
	private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

	@Autowired
	private Environment env;

	/*@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private String redisPort;

	@Value("${spring.redis.timeout}")
	private String redisTimeout;

	@Value("${spring.redis.password}")
	private String redisPassword;


	@Value("${application.id}")
	private String applicationId;*/


	/**
	 * 注册DelegatingFilterProxy（Shiro）
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
		filterRegistration.setAsyncSupported(true);
		filterRegistration.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico");
		return filterRegistration;
	}

	@Bean(name = "redisManager")
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(env.getProperty("spring.redis.host"));
		redisManager.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
		redisManager.setTimeout(Integer.parseInt(env.getProperty("spring.redis.timeout")));
		redisManager.setExpire(1800);
		//redisManager.setPassword(env.getProperty("spring.redis.password"));
		return redisManager;
	}

	@Bean(name = "redisCacheManager")
	public RedisCacheManager redisCacheManager(@Qualifier("redisManager") RedisManager redisManager) {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager);
		return redisCacheManager;
	}

	@Bean(name = "retryLimitHashedCredentialsMatcher")
	public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher(@Qualifier("redisCacheManager") RedisCacheManager redisCacheManager) {
		RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(redisCacheManager);
		retryLimitHashedCredentialsMatcher.setHashAlgorithmName("md5");
		retryLimitHashedCredentialsMatcher.setHashIterations(2);
		retryLimitHashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);

		return retryLimitHashedCredentialsMatcher;
	}

	@Bean(name = "userRealm")
	public UserRealm userRealm(@Qualifier("retryLimitHashedCredentialsMatcher") RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher) {
		UserRealm userRealm = new UserRealm();
		userRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher);
		userRealm.setCachingEnabled(false);
		return userRealm;
	}

	@Bean(name = "sessionIdGenerator")
	public SessionIdGenerator sessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}

	@Bean(name = "sessionIdCookie")
	public SimpleCookie sessionIdCookie() {
		SimpleCookie sessionIdCookie = new SimpleCookie("test_sid");
		sessionIdCookie.setHttpOnly(true);
		sessionIdCookie.setMaxAge(-1);
		sessionIdCookie.setPath("/");
		return sessionIdCookie;
	}

	@Bean(name = "rememberMeCookie")
	public SimpleCookie rememberMeCookie() {
		SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
		rememberMeCookie.setHttpOnly(true);
		rememberMeCookie.setMaxAge(2592000);
		rememberMeCookie.setPath("/");
		return rememberMeCookie;
	}

	@Bean(name = "rememberMeManager")
	public CookieRememberMeManager rememberMeManager(@Qualifier("rememberMeCookie") SimpleCookie rememberMeCookie) {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCipherKey(org.apache.shiro.codec.Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		cookieRememberMeManager.setCookie(rememberMeCookie);
		return cookieRememberMeManager;
	}

	@Bean(name = "sessionDAO")
	public RedisSessionDAO sessionDAO(@Qualifier("redisManager") RedisManager redisManager, @Qualifier("sessionIdGenerator") SessionIdGenerator sessionIdGenerator) {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager);
		redisSessionDAO.setSessionIdGenerator(sessionIdGenerator);
		return redisSessionDAO;
	}

	@Bean(name = "sessionManager")
	public DefaultWebSessionManager sessionManager(@Qualifier("sessionDAO") RedisSessionDAO sessionDAO, @Qualifier("sessionIdCookie") SimpleCookie sessionIdCookie) {
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		defaultWebSessionManager.setGlobalSessionTimeout(1800000);
		defaultWebSessionManager.setDeleteInvalidSessions(true);
		defaultWebSessionManager.setSessionDAO(sessionDAO);
		defaultWebSessionManager.setSessionIdCookieEnabled(true);
		defaultWebSessionManager.setSessionIdCookie(sessionIdCookie);
		return defaultWebSessionManager;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm,
													 @Qualifier("sessionManager") DefaultWebSessionManager sessionManager,
													 @Qualifier("rememberMeManager") CookieRememberMeManager rememberMeManager,
													 @Qualifier("redisCacheManager") RedisCacheManager redisCacheManager) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(userRealm);
		defaultWebSecurityManager.setSessionManager(sessionManager);
		defaultWebSecurityManager.setRememberMeManager(rememberMeManager);
		defaultWebSecurityManager.setCacheManager(redisCacheManager);
		return defaultWebSecurityManager;
	}

	@Bean(name = "methodInvokingFactoryBean")
	public MethodInvokingFactoryBean methodInvokingFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		methodInvokingFactoryBean.setArguments(new Object[]{securityManager});
		return methodInvokingFactoryBean;
	}

	@Bean(name = "formAuthenticationFilter")
	public ServerFormAuthenticationFilter formAuthenticationFilter() {
		ServerFormAuthenticationFilter serverFormAuthenticationFilter = new ServerFormAuthenticationFilter();
		serverFormAuthenticationFilter.setUsernameParam("username");
		serverFormAuthenticationFilter.setPasswordParam("password");
		serverFormAuthenticationFilter.setRememberMeParam("rememberMe");
		return serverFormAuthenticationFilter;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("formAuthenticationFilter") ServerFormAuthenticationFilter serverFormAuthenticationFilter,
											  @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		//配置登录的url和登录成功的url
		bean.setLoginUrl("/pages/login");
		bean.setSuccessUrl("/pages/index.html");
		bean.setUnauthorizedUrl("/unauth.html");

		Map<String, Filter> filters = new LinkedHashMap<>();
		filters.put("form_captcha_filter",serverFormAuthenticationFilter);
		bean.setFilters(filters);

		//配置访问权限
		filterChainDefinitionMap.put("/pages/login", "form_captcha_filter");
		filterChainDefinitionMap.put("/logout*", "anon");
		filterChainDefinitionMap.put("/captcha/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/pages/**", "user,authc");
		filterChainDefinitionMap.put("/**", "user,authc");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return bean;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
}
