package com.orrin.spring.cloud.security.oath2.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Enumeration;

/**
 * @author Orrin on 2017/7/11.
 */
@EnableAutoConfiguration
@Configuration
@EnableOAuth2Sso
@RestController
public class ClientServerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ClientServerApplication.class);

	@RequestMapping("/client")
	public String home(Principal user, HttpServletRequest request) {
		Enumeration<String>  headerNames = request.getHeaderNames();
		Enumeration<String>  parameterNames = request.getParameterNames();
		String tokenValue = ((OAuth2AuthenticationDetails)((OAuth2Authentication) user).getDetails()).getTokenValue();
		String tokenType = ((OAuth2AuthenticationDetails)((OAuth2Authentication) user).getDetails()).getTokenType();
		Object decodedDetails = ((OAuth2AuthenticationDetails)((OAuth2Authentication) user).getDetails()).getDecodedDetails();

		logger.info("tokenValue = {}", tokenValue);
		logger.info("tokenType = {}", tokenType);
		return "Hello " + user.getName();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(ClientServerApplication.class).run(args);
	}
}
