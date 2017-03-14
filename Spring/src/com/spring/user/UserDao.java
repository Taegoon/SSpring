package com.spring.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.ejb.access.EjbAccessException;

public abstract class UserDao {
	
	private ConnectionMaker connectionMaker;
	
	public ConnectionMaker getConnectionMaker() {
		return connectionMaker;
	}


	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}


	public void add(User user) throws ClassNotFoundException, SQLException{
		Connection c = ((DConnectionMaker) connectionMaker).makeConnection();
		
		PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");

		ps.setString(1 , user.getId()); 
		ps.setString(2, user.getName()); 
		ps.setString(3, user.getPassword()); 
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		
		Connection c = ((DConnectionMaker) connectionMaker).makeConnection();
		
		PreparedStatement ps = c.prepareStatement( "select * from users where id = ?" ); 
				
		ps.setString(1 , id); 
		
		ResultSet rs = ps.executeQuery();
		
		User user = null;
		if(rs.next()){ 
			user = new User();
			user.setId(rs.getString("id")); 
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password")); 
		}
		rs.close(); 
		ps.close(); 
		c.close(); 
		if(user == null) throw new EjbAccessException(id, null);
		
		return user; 
				
	}
	
	public void deleteAll() throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ((DConnectionMaker) connectionMaker).makeConnection();
			
			ps = makeStatement(c); 
			
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null){try{ps.close();}catch(SQLException e){}}
			if(c!=null){try{c.close();}catch(SQLException e){}}
		}
		
	}
//	deleteAll에서 변하는 부분을 별도로 메소드 추출
	abstract protected PreparedStatement makeStatement(Connection c)throws SQLException ;


	public int getCount() throws SQLException{
		Connection c = null;;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = ((DConnectionMaker) connectionMaker).makeConnection();
			ps = c.prepareStatement( "select count(*) from users" ); 
			
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			if(rs!=null){try{rs.close();}catch(SQLException e){}}
			if(ps!=null){try{ps.close();}catch(SQLException e){}}
			if(c!=null){try{c.close();}catch(SQLException e){}}
		}
		return 0;
	}
}





