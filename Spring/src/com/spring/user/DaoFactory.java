package com.spring.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
	
	@Bean
	public UserDaoJdbc userDao(){
		UserDaoJdbc userDao = new UserDaoJdbc(); 
		userDao.setConnectionMaker(connectionMaker()); 
		return userDao; 
	}
	
	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}
	
//	@Bean 
//	public DataSource dataSource() { 
//		SimpleDriverDataSource dataSource = new SimpleDriverDataSource(); 
//
//		dataSource.setDriverClass(Driver.class); 
//		dataSource.setUrl("jdbc:mysql://localhost/testdb?autoReconnect=true&useSSL=false"); 
//		dataSource.setUsername("root"); 
//		dataSource.setPassword("hantaijun1225"); 
//	return dataSource; 
//	}
	
}
