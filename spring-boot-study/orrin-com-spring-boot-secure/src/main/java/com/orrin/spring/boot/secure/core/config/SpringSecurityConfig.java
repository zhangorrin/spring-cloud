package com.orrin.spring.boot.secure.core.config;

import com.orrin.spring.boot.secure.core.secure.CustomUserService;
import com.orrin.spring.boot.secure.core.secure.DefaultAccessDeniedHandler;
import com.orrin.spring.boot.secure.core.secure.MyAccessDecisionManager;
import com.orrin.spring.boot.secure.core.secure.SimpleLoginSuccessHandler;
import com.orrin.spring.boot.secure.core.secure.URLFilterInvocationSecurityMetadataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Orrin on 2017/7/10.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


	private static final Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

	@Bean
	UserDetailsService customUserService() {
		return new CustomUserService();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(customUserService());
		daoAuthenticationProvider.setHideUserNotFoundExceptions(false);

		return daoAuthenticationProvider;
	}

	@Bean
	DefaultAccessDeniedHandler accessDeniedHandler() {
		DefaultAccessDeniedHandler accessDeniedHandler = new DefaultAccessDeniedHandler();
		accessDeniedHandler.setErrorPage("/securityException/accessDenied");
		return accessDeniedHandler;
	}

	@Bean
	AuthenticationSuccessHandler athenticationSuccessHandler() {
		SimpleLoginSuccessHandler simpleLoginSuccessHandler = new SimpleLoginSuccessHandler();
		return simpleLoginSuccessHandler;
	}

	@Bean
	URLFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() {
		URLFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource = new URLFilterInvocationSecurityMetadataSource();
		return urlFilterInvocationSecurityMetadataSource;
	}

	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() {
		AuthenticationManager authenticationManager = null;
		try {
			authenticationManager = super.authenticationManagerBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authenticationManager;
	}

	/*
	 *
     * 这里可以增加自定义的投票器
     */
	@Bean(name = "accessDecisionManager")
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList();
		decisionVoters.add(new RoleVoter());
		decisionVoters.add(new AuthenticatedVoter());
		decisionVoters.add(webExpressionVoter());// 启用表达式投票器
		MyAccessDecisionManager accessDecisionManager = new MyAccessDecisionManager(decisionVoters);
		return accessDecisionManager;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//http.addFilterAfter(MyUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		// 自定义accessDecisionManager访问控制器,并开启表达式语言
		//http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and().authorizeRequests().anyRequest().authenticated().expressionHandler(webSecurityExpressionHandler());

		http.authorizeRequests().antMatchers("/securityException/accessDenied").permitAll();

		// 开启默认登录页面
		http.authorizeRequests().anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
				fsi.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
				fsi.setAccessDecisionManager(accessDecisionManager());
				fsi.setAuthenticationManager(authenticationManagerBean());
				return fsi;
			}
		}).and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))

				.and().formLogin().successHandler(athenticationSuccessHandler()).defaultSuccessUrl("/index").failureUrl("/login?error")



				.and().logout().logoutSuccessUrl("/index").permitAll();

		// 自定义accessDecisionManager访问控制器,并开启表达式语言
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				.and().authorizeRequests().anyRequest().authenticated().expressionHandler(webSecurityExpressionHandler());

		// 自定义登录页面
		http.csrf().disable();

		// session管理
		http.sessionManagement().maximumSessions(1);


	}


	/*
	* 表达式控制器
	*/
	@Bean(name = "expressionHandler")
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		return webSecurityExpressionHandler;
	}

	/*
	 * 表达式投票器
	 */
	@Bean(name = "expressionVoter")
	public WebExpressionVoter webExpressionVoter() {
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
		return webExpressionVoter;
	}
}
