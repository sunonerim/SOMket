package com.sunonerim.framework.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import	java.util.List;

import org.apache.log4j.Logger;

public class DbUtil {
	static final Logger logger = Logger.getLogger (DbUtil.class);
	/**
	 * It returns the number of records after the given sql was executed.
	 *  
	 * @param conn
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	static public	int	countSql( Connection conn , String sql )throws SQLException {
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt.executeQuery( sql );
		int num_record = 0;
		if( rs.next() ){
			num_record = rs.getInt(1);
		} 
		
		return num_record;
	}
	
	
	static public	RecordList	selectSql( Connection conn , String sql )throws SQLException {
		
		RecordList	results = new RecordList();
		// Get a statement from the connection
		Statement stmt = conn.createStatement();

		// Execute the query
		ResultSet rs = stmt.executeQuery( sql );

		// Get the metadata
		ResultSetMetaData md = rs.getMetaData();

		
		// Print the column labels
		for (int i = 1; i <= md.getColumnCount(); i++){
			// logger.info ( String.format( "in DBUtil.selectSql  ;  %s, %s, %d",    md.getColumnLabel(i), md.getColumnTypeName(i), md.getPrecision(i)  )    );
			results.addMetaItem(md.getColumnLabel(i), md.getColumnLabel(i),  md.getColumnType(i),  md.getPrecision(i));
		}

		while( rs.next() ){
			HashMap	record = new HashMap();
			for (int i = 1; i <= md.getColumnCount(); i++){
				record.put(md.getColumnLabel(i), rs.getObject(i));
				logger.info ( String.format("field  %s, %s",  md.getColumnLabel(i), rs.getObject(i)==null?"null": rs.getObject(i).toString() ));
			}
			results.add(record);
		}
		rs.close();
		stmt.close();
		
		return results;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con;
		List	result  = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("No JDBC driver.");
		}

		try {
//			String url = "jdbc:mysql://www.tomeii.com/mudchobo";
			con = DriverManager.getConnection("jdbc:mysql://localhost/finxdb","root", "dlagusanr");
			result = DbUtil.selectSql(con, "select * from CCPInterface");
			con.close();
		} catch (SQLException e) {
			System.err.println("SQL에러.");
		}
		System.out.println( result );
		
	}

}
