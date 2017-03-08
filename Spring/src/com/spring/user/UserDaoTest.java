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
		
		dao.deleteAll();	// DB에 있는 정보 모두 삭제
		assertThat(dao.getCount(),is(0));	// DB에 있는 정보 삭제 확인
		
		User user =new User();
		user.setId("whiteshep");
		user.setName("조민함");
		user.setPassword("hantaegoon");
		
		dao.add(user);
		assertThat(dao.getCount(),is(1));	//DB에 정보 입력 확인
		
		User user2 = dao.get(user.getId());
		
		assertThat(user2.getName(),is(user.getName()));
		assertThat(user2.getPassword(),is(user.getPassword()));
		
	}

}
