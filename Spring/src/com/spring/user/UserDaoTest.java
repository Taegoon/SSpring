package com.spring.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ejb.access.EjbAccessException;

public class UserDaoTest {
	private UserDao dao;
	
	@Before
	public void setUp(){

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml",UserDao.class);
		this.dao = context.getBean("userDao",UserDao.class);
	}
	

	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		
		User user1 = new User( "gyumee" , "박성절 ","springnol"); 
		User user2 = new User("leegw700", "이길원 ","springno2");

		
		dao.deleteAll();	// DB에 있는 정보 모두 삭제
		assertThat(dao.getCount(),is(0));	// DB에 있는 정보 삭제 확인
		
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(),is(2));	//DB에 정보 입력 확인
		
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(),is(user1.getName()));
		assertThat(userget1.getPassword(),is(user1.getPassword()));

		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(),is(user2.getName()));
		assertThat(userget2.getPassword(),is(user2.getPassword()));
		
	}
	
	@Test
	public void count() throws SQLException, ClassNotFoundException{
		
		User user1 = new User( "gyumee" , "박성절 ","springnol"); 
		User user2 = new User("leegw700", "이길원 ","springno2");
		User user3 = new User("bumjin" , "박범진","springno3") ; 
		
		
		dao.deleteAll(); 
		assertThat(dao.getCount(),is(0)); 
		dao.add(user1); 
		assertThat(dao.getCount(),is(1)); 
		dao.add(user2); 
		assertThat(dao.getCount(),is(2)); 
		dao.add(user3); 
		assertThat(dao.getCount(),is(3)); 
	}
	
	@Test(expected=EjbAccessException.class) 
	public void getUserFailure() throws SQLException, ClassNotFoundException{
		
		dao.deleteAll();
		assertThat(dao.getCount(),is(0)); 
		dao.get("unknown_id");
	}
	
}
