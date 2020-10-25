package com.spj.authorization.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
 * @author Yogesh Sharma
 *
 */
@Component
@Data
public class ServiceConfig {

	@Value("${spring.datasource.url}")
	private String databaseUrl;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spj.security.scope}")
	private String clientScope;

	@Value("${spj.security.accessTokenValidity}")
	private int accessTokenValidity;

	@Value("${spj.security.refreshTokenValidity}")
	private int refreshTokenValidity;

	@Value("${spj.security.resourceIds}")
	private String resourceIds;
}