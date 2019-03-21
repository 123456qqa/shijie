package org.cloudCorp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD_Pool {
	
	public static int  cud(String sql,Object[] values) {
		 int result = 0 ;
		 DBPoolUtils dbPoolUtil = DBPoolUtils.getInstance() ;
		 Connection con = dbPoolUtil.getConnection() ;
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
			dbPoolUtil.close(null, pstmt);  //关闭结果集和声明
			dbPoolUtil.releaseConnection(con); //释放连接
		}
		 return result ;
	}
	
	public static ResultSet query(String sql,Object[] values) {
		 ResultSet rs = null ;
		 DBPoolUtils dbPoolUtil = DBPoolUtils.getInstance() ;
		 Connection con = dbPoolUtil.getConnection() ;
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
			//dbPoolUtil.close(rs, pstmt);  //不能关闭结果集和声明，因为结果集作为返回值
			dbPoolUtil.releaseConnection(con); //释放连接
		}
		 return rs ;
	}

}
