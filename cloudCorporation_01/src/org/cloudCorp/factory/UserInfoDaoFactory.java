package org.cloudCorp.factory;

import org.cloudCorp.dao.UserInfoDao;
import org.cloudCorp.dao.Impl.UserInfoDaoImpl;


public class UserInfoDaoFactory {

	private static UserInfoDao  userInfoDao ;
	
	public static UserInfoDao  getUserInfoDao() {
		if ( userInfoDao == null ) {
			userInfoDao = new UserInfoDaoImpl() ;
		}
		return userInfoDao ;
	}
	
	
	
	
}
