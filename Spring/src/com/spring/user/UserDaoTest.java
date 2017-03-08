package com.spring.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.sql.SQLException;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoTest {
	

	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml",UserDao.class);
		
		UserDao dao = context.getBean("userDao",UserDao.class);
		
		User user =new User();
		user.setId("whiteshep");
		user.setName("조민함");
		user.setPassword("hantaegoon");
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		
		User user2 = dao.get(user.getId());
		
		assertThat(user2.getName(),is(user.getName()));
		assertThat(user2.getPassword(),is(user.getPassword()));
		
	}
//	public static void main(String[] args){
//		JUnitCore.main("com.spring.user.UserDaoTest");
//	}
//	

}
