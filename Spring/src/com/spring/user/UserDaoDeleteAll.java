package com.spring.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDeleteAll {
	 protected PreparedStatement makeStatement(Connection c)throws SQLException {
			// TODO Auto-generated method stub
			PreparedStatement ps;
			ps = c.prepareStatement( "delete from users" ); 
			return ps;
		}

}
