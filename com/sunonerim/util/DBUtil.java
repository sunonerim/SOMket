package com.sunonerim.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public	static Connection 	openConnect(){
		String Driver 		= "com.mysql.jdbc.Driver";
		String Url 			= "jdbc:mysql://localhost/FinXDB";
		String ID 			= "root";
		String Password 	= "dlagusanr";
		Connection	mConn 	= null;
		
			System.out.println("CommnadBase  openConnection");
			try {
				Class.forName(Driver);
				try {
					mConn = DriverManager.getConnection(Url, ID, Password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}		
			return mConn;
	}
	
	
	public	static	void	closeConnect(Connection		conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public	static	int	execute(Connection		conn, String sql ) throws SQLException{		
		Statement stmt = conn.createStatement(); 
		return stmt.executeUpdate(sql);
	}
}
