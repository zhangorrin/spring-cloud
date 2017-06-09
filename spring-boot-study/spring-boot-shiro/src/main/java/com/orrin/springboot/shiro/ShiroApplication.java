package com.orrin.springboot.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Orrin on 2017/5/27.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.orrin.springboot.shiro")
@EnableAutoConfiguration
public class ShiroApplication {

	public static void main(String[] args) {
		// 程序启动入口
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(ShiroApplication.class,args);
	}
}
