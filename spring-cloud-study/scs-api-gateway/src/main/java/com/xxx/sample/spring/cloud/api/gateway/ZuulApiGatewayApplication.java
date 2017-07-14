package com.xxx.sample.spring.cloud.api.gateway;

import com.xxx.sample.spring.cloud.api.gateway.core.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 使用@EnableZuulProxy注解激活zuul。
 * 跟进该注解可以看到该注解整合了@EnableCircuitBreaker、@EnableDiscoveryClient，是个组合注解，目的是简化配置。
 * @author Orrin on 2017/2/4.
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGatewayApplication.class, args);
	}


	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}