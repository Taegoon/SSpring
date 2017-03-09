package com.spring.user;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	
	@Bean
	public UserDao userDao(){
		UserDao userDao = new UserDao(); 
		userDao.setConnectionMaker(connectionMaker()); 
		return userDao; 
	}
	
	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}
	
	@Bean 
	public DataSource dataSource() { 
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource(); 

		dataSource.setDriverClass(Driver.class); 
		dataSource.setUrl("jdbc:mysql://localhost/testdb?autoReconnect=true&useSSL=false"); 
		dataSource.setUsername("root"); 
		dataSource.setPassword("hantaijun1225"); 
	return dataSource; 
	}
}
