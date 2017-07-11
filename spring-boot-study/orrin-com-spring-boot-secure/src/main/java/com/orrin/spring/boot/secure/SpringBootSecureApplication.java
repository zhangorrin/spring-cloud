package com.orrin.spring.boot.secure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Orrin on 2017/7/8.
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.orrin.spring.boot.secure"})
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringBootSecureApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootSecureApplication.class, args);
	}
}
