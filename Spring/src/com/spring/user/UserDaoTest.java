package com.spring.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

//		UserDao dao = new UserDao();
//		NUserDao dao =new NUserDao();
//		UserDao dao = new DaoFactory().userDao();

//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml",UserDao.class);
		
		UserDao dao = context.getBean("userDao",UserDao.class);
		
		User user =new User();
		user.setId("whiteship");
		user.setName("조민함");
		user.setPassword("chominham");
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		
		User user2 = dao.get(user.getId());
		
//		System.out.println(user2.getName());
//		System.out.println(user2.getPassword());
//		System.out.println(user2.getId() + "조회 성공");
		
		if (!user.getName().equals(user2.getName())) {
			System.out.println("테스트 실패(name)");
		}
		else if(!user.getPassword().equals(user2.getPassword())){
			System.out.println("테스트 실패(password");
		}
		else{
			System.out.println("조회 테스트 성공");
		}
		
	}

}
