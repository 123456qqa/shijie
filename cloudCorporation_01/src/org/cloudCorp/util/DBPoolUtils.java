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
	
	private List<Connection> dbPool = null ; //���ӳ�
	
	private  int dbPoolMaxSize = 5 ; //���ӳ����������
	
    private DBPoolUtils() {
    	//��ʼ��
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
    
    //�����ӳػ�ȡ���ӣ����ӳش�С��1��������ӳش�СΪ0������null
    public Connection getConnection() {
    	if ( dbPool.size() > 0 ) {
    		return dbPool.remove(0) ;
    	}else {
    		return null ;
    	}
    }
    
    //�����ӻ��յ����ӳ���
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

	
	
	
	

