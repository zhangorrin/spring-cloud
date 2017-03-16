package com.xxx.sample.spring.boot.dubbo.demo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xxx.sample.spring.boot.dubbo.demo.api.DemoApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Orrin on 2017/3/16.
 */
@Component
public class ConsumerAction {
	private final static Logger LOGGER = LoggerFactory.getLogger(ConsumerAction.class);

	@Reference
	private DemoApi demoApi;

	public void add(int a, int b) {
		int ret = demoApi.add(a, b);
		LOGGER.info("ret = {}", ret);
		System.out.println("ret = " + ret);
	}

}