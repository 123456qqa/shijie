package org.cloudCorp.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.cloudCorp.dao.FriendDao;
import org.cloudCorp.model.Friend;
import org.cloudCorp.model.UserInfo;
import org.cloudCorp.util.CRUD;
import org.cloudCorp.util.DBUtils;

public class FriendDaoImpl implements FriendDao {

	@Override
	public List<UserInfo> getToAddUserList(int userInfoId) {
		List<UserInfo> toAddUserList = new LinkedList<UserInfo>() ; 
		String sql = " select * from userInfo where userInfoId<>? " + 
				"and userInfoId not in (" + 
				"select userInfoBId from friend where userInfoAId = ? and status='approved'" + 
				") and userInfoId not in (" + 
				"select userInfoAId from friend where userInfoBId = ? and status='approved')" + 
				"and  userInfoId not in(" + 
				"select userInfoBId from friend where userInfoAId = ? and status='applied'" + 
				") and userInfoId not in ( " + 
				"select userInfoAId from friend where userInfoBId = ? and status='applied')" ;
		Object[] values = {userInfoId,userInfoId,userInfoId,userInfoId,userInfoId} ;
		ResultSet rs = CRUD.query(sql, values) ;
		try {
			while (rs.next() ) {
				UserInfo userInfo = new UserInfo() ;
				userInfo.setUserInfoId(rs.getInt("userInfoId")) ;
				userInfo.setMobilePhone(rs.getString("mobilePhone")) ;
				userInfo.setPassword(rs.getString("password")) ;
				userInfo.setUserName(rs.getString("userName"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setTrueName(rs.getString("trueName"));
				userInfo.setGender(rs.getString("gender"));
				userInfo.setHeadImage(rs.getString("headImage"));
				userInfo.setBrief(rs.getString("brief"));
				toAddUserList.add(userInfo) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, rs);
		}
		return toAddUserList;
	}

	@Override
	public boolean confirmFriend(int friendId, String action) {
		  boolean success = false ;
	        if ( action == "agree" || "agree".equals(action) ) {
	        	//同意
	        	String sql = "update friend set startTime=now(),status=? where friendId=?" ;
	        	Object[] values = {"confirmed",friendId} ;
	        	int result =CRUD.cud(sql, values);
	        	success = result > 0 ;
	        } else if ( action == "reject" || "reject".equals(action)  ) {
	        	//拒绝
	        	String sql = "delete from friend where friendId=?" ;
	        	Object[] values = {friendId} ;
	        	int result =CRUD.cud(sql, values);
	        	success = result > 0 ;
	        }
			return success;
	}

	@Override
	public List<UserInfo> getMyFriendList(int userInfoId) {
		List<UserInfo> myFriendList = new LinkedList<UserInfo>() ;
		//主动加的好友
		String sql = " select * from friend where userInfoAId = ?   and status=? " ;
		Object[] values = {userInfoId,"confirmed"} ;
		ResultSet rs = CRUD.query(sql, values) ;
		try {
			while ( rs.next() ) {
				FriendDao friendDao=new FriendDaoImpl();
				int userInfoBId = rs.getInt("userInfoBId") ;
				List<UserInfo> userBObj = friendDao.getMyFriendList(userInfoBId);
				myFriendList.addAll(userBObj) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		//其他用户加我为好友
		sql = "select * from friend where userB = ?   and status=? " ;
		Object[] values1 = {userInfoId,"confirmed"} ;
		rs = CRUD.query(sql, values1) ;
		try {
			while (rs.next()) {
				FriendDao friendDao=new FriendDaoImpl();
				int userInfoAId = rs.getInt("userInfoAId") ;
				List<UserInfo> userAObj =friendDao.getMyFriendList(userInfoAId);  ;
				myFriendList.addAll(userAObj) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, rs);
		}
		
		return myFriendList;
	}

	@Override
	public void applyFriend(Friend friend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Friend> getAppliedList(int userInfoBId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
