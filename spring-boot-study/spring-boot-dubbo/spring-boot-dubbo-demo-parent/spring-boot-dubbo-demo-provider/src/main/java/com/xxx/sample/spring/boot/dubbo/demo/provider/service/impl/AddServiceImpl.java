package com.xxx.sample.spring.boot.dubbo.demo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xxx.sample.spring.boot.dubbo.demo.api.DemoApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * Created by lizhou on 2017/2/28/028.
 */
@Service
public class AddServiceImpl implements DemoApi {
    private final static Logger LOGGER = LoggerFactory.getLogger(AddServiceImpl.class);

    @PostConstruct
    public void init() {
        LOGGER.info("{} 初始化", "AddServiceImpl");
    }


    public int add(int a, int b) {
        LOGGER.info("{} + {} = {}",a,b,a+b);
        return a + b;
    }

}
