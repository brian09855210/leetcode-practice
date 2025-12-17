package com.practice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@Configuration
@PropertySource(value="config.properties")
public class Config {
	
	@Value("${DB_URL}")
	private String dbUrl;
	
	@Value("${DB_USER}")
	private String dbUser;
	
	@Value("${DB_PWD}")
	private String dbPwd;
	
	@Value("${DB_DRIVER}")
	private String dbDriver;
	
	@Value("${TABLE_NAME}")
	private String tableName;
	
	@Value("${PACKAGE_NAME}")
	private String packageName;
	
}
