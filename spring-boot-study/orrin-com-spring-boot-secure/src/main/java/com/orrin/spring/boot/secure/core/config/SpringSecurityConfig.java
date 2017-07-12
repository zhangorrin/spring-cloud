package com.orrin.spring.boot.secure.core.config;

import com.orrin.spring.boot.secure.core.secure.CustomUserService;
import com.orrin.spring.boot.secure.core.secure.DefaultAccessDeniedHandler;
import com.orrin.spring.boot.secure.core.secure.SimpleLoginSuccessHandler;
import com.orrin.spring.boot.secure.core.secure.URLFilterInvocationSecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Orrin on 2017/7/10.
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
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
	AccessDeniedHandler accessDeniedHandler() {
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


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//http.addFilterAfter(MyUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		// 自定义accessDecisionManager访问控制器,并开启表达式语言
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				.and().authorizeRequests().anyRequest().authenticated().expressionHandler(webSecurityExpressionHandler());


		http.authorizeRequests()
				.anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
						fsi.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
						//fsi.setAccessDecisionManager(accessDecisionManager());
						fsi.setAuthenticationManager(authenticationManagerBean());
						return fsi;
					}
				})
				.and().formLogin().loginPage("/login").successHandler(athenticationSuccessHandler())
				//设置默认登录成功跳转页面
				.defaultSuccessUrl("/index").failureUrl("/login?error").permitAll()
				.and()
				//开启cookie保存用户数据
				.rememberMe()
				//设置cookie有效期
				.tokenValiditySeconds(60 * 60 * 24 * 7)
				//设置cookie的私钥
				.key("abc")
				.and()
				.logout()
				//默认注销行为为logout，可以通过下面的方式来修改
				.logoutUrl("/logout")
				//设置注销成功后跳转页面，默认是跳转到登录页面
				.logoutSuccessUrl("/login")
				.permitAll();
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
	/*@Bean(name = "expressionVoter")
	public WebExpressionVoter webExpressionVoter() {
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
		return webExpressionVoter;
	}*/
}
