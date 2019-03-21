package org.cloudCorp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	
	//涓嶉渶瑕佹瘡涓搷浣滈兘鍒涘缓杩炴帴
	private static  Connection con ;
	
	//闈欐�佸潡锛屽湪绫诲姞杞芥椂鎵ц
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudCorporation",
					                          "root", "123456") ;
			System.out.println("con==null--1:"+(con==null)) ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		System.out.println("con==null--2:"+(con==null)) ;
		return con ;
	}

	public static void close(Connection con,Statement stmt,ResultSet rs) {
		try {
			if ( rs != null ) {
			  rs.close();
			  rs = null ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if ( stmt != null ) {
					   stmt.close() ;
					   stmt = null ;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					   try {
							if ( con != null ) {
							   con.close();
							   con = null ;
							}
						} catch (SQLException e) {
							e.printStackTrace();
						} 
				}
		}
	
}
	
}
