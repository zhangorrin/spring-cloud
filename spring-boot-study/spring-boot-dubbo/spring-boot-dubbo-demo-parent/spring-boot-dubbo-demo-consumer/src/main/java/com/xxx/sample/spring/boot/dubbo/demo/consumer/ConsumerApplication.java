package com.xxx.sample.spring.boot.dubbo.demo.consumer;

import com.xxx.sample.spring.boot.dubbo.autoconfig.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

/**
 * @author Orrin on 2017/3/16.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xxx.sample.spring.boot.dubbo.demo.consumer")
@EnableDubbo(basePackages = "com.xxx.sample.spring.boot.dubbo.demo.consumer")
public class ConsumerApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new SpringApplicationBuilder()
				.sources(ConsumerApplication.class)
				.web(false)
				.run(args);
		ctx.getBean(ConsumerAction.class).add(21, 25);
		new CountDownLatch(1).await();
	}

}
