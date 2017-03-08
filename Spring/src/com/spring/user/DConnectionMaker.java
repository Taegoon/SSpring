package com.spring.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException{
	
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/testdb?autoReconnect=true&useSSL=false",
				"root" , "hantaijun1225");

		return c;
		
	}
}
