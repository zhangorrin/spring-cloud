package com.xxx.sample.spring.cloud.csde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 在生产环境中，我们可能会将Config Server 与 Eureka等注册中心联合使用
 * （注意：目前Spring Cloud只支持与Eureka及Consul联合使用，不支持与Zookeeper联合使用）。
 * @author Orrin on 2017/2/4.
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerEurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerEurekaApplication.class, args);
	}
}