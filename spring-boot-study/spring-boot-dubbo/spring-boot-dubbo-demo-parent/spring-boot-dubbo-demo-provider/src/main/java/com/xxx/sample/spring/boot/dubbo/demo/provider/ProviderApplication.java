package com.xxx.sample.spring.boot.dubbo.demo.provider;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.xxx.sample.spring.boot.dubbo.autoconfig.annotation.EnableDubbo;
import com.xxx.sample.spring.boot.dubbo.core.filter.AbstractDubboFilterSupport;
import com.xxx.sample.spring.boot.dubbo.core.filter.AbstractDubboProviderFilterSupport;
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
@ComponentScan(basePackages = "com.xxx.sample.spring.boot.dubbo.demo.provider")
@EnableDubbo(basePackages = "com.xxx.sample.spring.boot.dubbo.demo.provider")
public class ProviderApplication {
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new SpringApplicationBuilder()
				.sources(ProviderApplication.class)
				.web(false)
				.run(args);
		new CountDownLatch(1).await();
	}

	@Bean
	ProviderFilter providerFilter() {
		return new ProviderFilter();
	}

	static class ProviderFilter extends AbstractDubboProviderFilterSupport {
		public Result invoke(Invoker<?> invoker, Invocation invocation) {
			System.out.println("ProviderFilter");
			return invoker.invoke(invocation);
		}
	}


	@Bean
	CustomFilter customFilter() {
		return new CustomFilter();
	}

	@Activate(group = Constants.PROVIDER)
	static class CustomFilter extends AbstractDubboFilterSupport {
		public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
			System.out.println("CustomFilter");
			return invoker.invoke(invocation);
		}

		public Filter getDefaultExtension() {
			return this;
		}
	}
}
