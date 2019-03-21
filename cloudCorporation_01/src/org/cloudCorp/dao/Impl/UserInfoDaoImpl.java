package org.cloudCorp.dao.Impl;





import java.sql.ResultSet;
import java.sql.SQLException;

import org.cloudCorp.dao.UserInfoDao;
import org.cloudCorp.model.UserInfo;
import org.cloudCorp.util.CRUD;
import org.cloudCorp.util.DBUtils;

public class UserInfoDaoImpl implements UserInfoDao {
	@Override
	public boolean register(UserInfo userInfo) {
		String mobilePhone = userInfo.getMobilePhone();
		if (isExistsMobilePhone(mobilePhone)) {
			// 手机号存在
			return false;
		}
		String sql = "insert into UserInfo (mobilePhone,password) values(?,?)";
		Object[] values = { userInfo.getMobilePhone(), userInfo.getPassword() };
		int rs = CRUD.cud(sql, values);
		return rs > 0;
	}

	@Override
	public UserInfo login(String accountNo, String password) {
		UserInfo userInfo = null;
		String sql = "select * from UserInfo where (mobilePhone=? or email=? or userName=?)and password=?";
		Object[] values = { accountNo, accountNo, accountNo, password };
		ResultSet rs = CRUD.query(sql, values);
		try {
			if (rs.next()) {
				int userInfoId = rs.getInt("userInfoId");
				String mobilePhone = rs.getString("mobilePhone");
				String up = rs.getString("userPass");
				String userName = rs.getString("userName");
				String email = rs.getString("email");
				String trueName = rs.getString("trueName");
				String gender = rs.getString("gender");
				String headImage = rs.getString("headImage");
				String brief = rs.getString("brief");
				userInfo = new UserInfo();
				userInfo.setBrief(brief);
				userInfo.setEmail(email);
				userInfo.setGender(gender);
				userInfo.setHeadImage(headImage);
				userInfo.setMobilePhone(mobilePhone);
				userInfo.setPassword(password);
				userInfo.setTrueName(trueName);
				userInfo.setUserInfoId(userInfoId);
				userInfo.setUserName(userName);
			}
			;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, rs);
		}

		return null;
	}

	@Override
	public boolean isExistsMobilePhone(String mobilePhone) {
		boolean isExists = false;
		String sql = "select * from UserInfo where mobilePhone=？";
		Object[] values = { mobilePhone };
		ResultSet rs = CRUD.query(sql, values);
		try {
			if (rs.next()) {
				// 手机号存在
				isExists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, rs);

		}
		return isExists;
	}
	@Override
	public boolean isExistsPassword(String password) {
		boolean isExists = false;
		String sql = "select * from UserInfo where mobilPhone=?";
		Object[] values = { password };
		ResultSet rs = CRUD.query(sql, values);
		try {    			
			if (rs.next()) {
				// 密码正确
				isExists = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, rs);
		}
		return isExists;
	}

	@Override
	public boolean changepassword(int userInfoId, String oldpassword, String newpassword) {
	     String sql="update UserInfo set password=? where userInfoId=? and password=?";
	     Object[] values = {newpassword,userInfoId,oldpassword};
	     int result=CRUD.cud(sql, values);
	     
		return result>0;
	}

	@Override
	public boolean isExistsEmail(String email) {
		boolean isExists = false;
		String sql = "select * from UserInfo where email=?";
		Object[] values = { email };
		ResultSet rs = CRUD.query(sql, values);
		try {    			
			if (rs.next()) {
				// 密码正确
				isExists = true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtils.close(null, null, rs);
		}
		return isExists;
	}

	@Override
	public boolean UpdateUserInfo(UserInfo userInfo) {
		String sql="update userInfo set userName=?,trueName=?,gender=?,brief=?email=? where userInfoId=?";
		Object[] values= {userInfo.getBrief(),userInfo.getEmail(),userInfo.getGender(),userInfo.getTrueName(),
				userInfo.getUserName(),userInfo.getUserInfoId()};
		 int result=CRUD.cud(sql, values);
		return result>0;
	}

	@Override
	public boolean changeheadImage(UserInfo userInfo) {
		String sql="update userInfo set headImage=? where userInfoId=?";
		Object[] values= {userInfo.getHeadImage(),userInfo.getUserInfoId()};
		int result=CRUD.cud(sql, values);
	    return result>0;
	}

	@Override
	public boolean isExistsEmail(String email, int userId) {
		  boolean isExists = false ;
	        String sql = "select * from userInfo where email=? and userId<>?" ;
	        Object[] values = {email,userId} ;
	        ResultSet rs =CRUD.query(sql, values) ;
	        try {
				if ( rs.next() ) {
					isExists = true ;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtils.close(null, null, rs);
			}
			return isExists;
	}
}
