package org.cloudCorp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DBPoolUtils {

	private static final DBPoolUtils instance = new DBPoolUtils() ;
	
	private List<Connection> dbPool = null ; //连接池
	
	private  int dbPoolMaxSize = 5 ; //连接池最大连接数
	
    private DBPoolUtils() {
    	//初始化
    	dbPool = new LinkedList<Connection>() ;
    	for(int i=0;i<dbPoolMaxSize;i++) {
    		try {
				Class.forName("com.mysql.jdbc.Driver") ;
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudCorporation",
	    				"root", "123456") ;
				dbPool.add(con) ;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		
    	}
    }
    
    public static DBPoolUtils getInstance() {
    	return instance ;
    }
    
    //从连接池获取连接，连接池大小减1，如果连接池大小为0，返回null
    public Connection getConnection() {
    	if ( dbPool.size() > 0 ) {
    		return dbPool.remove(0) ;
    	}else {
    		return null ;
    	}
    }
    
    //将连接回收到连接池中
    public void releaseConnection(Connection con ) {
    	if ( dbPool.size() < dbPoolMaxSize ) {
    		dbPool.add(con) ;
    	}
    	
    }
    
    public void close(ResultSet rs,Statement stmt) {
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
					stmt.close(); 
					stmt = null ;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }

}

	
	
	
	

