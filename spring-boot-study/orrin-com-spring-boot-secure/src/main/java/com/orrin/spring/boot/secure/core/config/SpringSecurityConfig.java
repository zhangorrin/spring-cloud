package com.orrin.spring.boot.secure.core.config;

import com.orrin.spring.boot.secure.core.secure.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
	AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(customUserService());
		daoAuthenticationProvider.setHideUserNotFoundExceptions(false);

		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login")
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
}
