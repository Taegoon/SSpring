package com.spring.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class JdbcContext {
	private ConnectionMaker connectionMaker;	
	public void setDataSource(ConnectionMaker connectionMaker){
		this.connectionMaker = connectionMaker;
	}
	
//	변하지 않는 부분을 분리시킨 deleteAll() 메소드

	public void executeSql(final String query) throws SQLException{
		workWithStatementStrategy(new DeleteAllStatement(){
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				return c.prepareStatement(query); 
			}
		}); 
	}
	
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ((DConnectionMaker) this.connectionMaker).makeConnection();
			
			ps = stmt.makePreparedStatement(c); 
			
			
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null){try{ps.close();}catch(SQLException e){}}
			if(c!=null){try{c.close();}catch(SQLException e){}}
		}
		
	}
}
