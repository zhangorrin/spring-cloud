package com.xxx.sample.spring.boot.dubbo.autoconfig.config;

import com.xxx.sample.spring.boot.dubbo.core.context.ExtensionBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by orrin on 2017/3/5
 */
@Configuration
@ConditionalOnClass({ExtensionBeanPostProcessor.class})
public class ExtensionConfiguration {

    @Bean
    ExtensionBeanPostProcessor extensionBeanPostProcessor() {
        return new ExtensionBeanPostProcessor();
    }
}
