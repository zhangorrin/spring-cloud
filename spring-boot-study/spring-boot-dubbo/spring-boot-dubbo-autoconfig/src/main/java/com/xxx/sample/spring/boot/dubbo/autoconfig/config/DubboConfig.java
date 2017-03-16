package com.xxx.sample.spring.boot.dubbo.autoconfig.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by orrin on 2017/3/5/005.
 */
@Configuration
public class DubboConfig {
    @Bean
    @ConfigurationProperties(prefix = "dubbo.application")
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "dubbo.registry")
    public RegistryConfig registryConfig() {
        return new RegistryConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "dubbo.protocol")
    public ProtocolConfig protocolConfig() {
        return new ProtocolConfig();
    }
}
