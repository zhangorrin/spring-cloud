package com.xxx.sample.spring.boot.dubbo.demo.consumer;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.xxx.sample.spring.boot.dubbo.autoconfig.annotation.EnableDubbo;
import com.xxx.sample.spring.boot.dubbo.core.filter.AbstractDubboConsumerFilterSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

/**
 * @author Orrin on 2017/3/16.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xxx.sample.spring.boot.dubbo.demo.consumer")
@EnableDubbo(basePackages = "com.xxx.sample.spring.boot.dubbo.demo.consumer")
public class ConsumerApplication {

	@Bean
	ConsumerFilter consumerFilter() {
		return new ConsumerFilter();
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new SpringApplicationBuilder()
				.sources(ConsumerApplication.class)
				.web(false)
				.run(args);
		ctx.getBean(ConsumerAction.class).add(21, 25);
		new CountDownLatch(1).await();
	}

	static class ConsumerFilter extends AbstractDubboConsumerFilterSupport {
		public Result invoke(Invoker<?> invoker, Invocation invocation) {
			System.out.println("ConsumerFilter");
			return invoker.invoke(invocation);
		}
	}

}
