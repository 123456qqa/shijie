package org.cloudCorp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD {
	
	public static int  cud(String sql,Object[] values) {
		 int result = 0 ;
		 Connection con = DBUtils.getConnection() ;
		 PreparedStatement pstmt = null ;
		 try {
			 pstmt = con.prepareStatement(sql) ;
			 if ( values != null ) {
				 for(int i=0;i<values.length;i++) {
					 pstmt.setObject(i+1, values[i]);
				 }
			 }
			 result = pstmt.executeUpdate() ;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, pstmt, null); //连接不能关闭
		}
		 return result ;
	}
	
	public static ResultSet query(String sql,Object[] values) {
		 ResultSet rs = null ;
		 Connection con = DBUtils.getConnection() ;
		 PreparedStatement pstmt = null ;
		 try {
			pstmt = con.prepareStatement(sql) ;
			if ( values != null  ) {
				for(int i=0;i<values.length;i++) {
					pstmt.setObject(i+1, values[i]);
				}
			}
			System.out.println(pstmt.toString()) ; //打印已经对?赋值的SQL语句
			rs = pstmt.executeQuery() ;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//结果集需要返回，不能关闭
			//声明不能关闭，因为声明关闭，结果集也会关闭
			//连接不能关闭，因为连接关闭，声明关闭，从而结果集也会关闭
			//DBUtils.close(con, pstmt, null);
		}
		 return rs ;
	}

}
