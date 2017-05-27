package com.xxx.sample.spring.boot.muti.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Orrin on 2017/5/27.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xxx.sample.spring.boot.muti.ds")
public class Application {

	public static void main(String[] args) {
		// 程序启动入口
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(Application.class,args);
	}
}
