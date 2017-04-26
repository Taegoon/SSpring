package com.spring.user;

import java.sql.SQLException;

public interface UserDao {
	void add(User user) throws ClassNotFoundException, SQLException;
	User get(String id) throws ClassNotFoundException, SQLException;
//	List<User> getAll;
	void deleteAll() throws SQLException;
	int getCount() throws SQLException;
}
