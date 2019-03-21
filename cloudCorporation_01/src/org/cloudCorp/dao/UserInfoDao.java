package org.cloudCorp.dao;

import org.cloudCorp.model.UserInfo;

public interface UserInfoDao {
	
	//用户注册
	public  boolean register(UserInfo userInfo) ;
	//用户登录
    public UserInfo login(String accountNo, String password);
    //判断手机号
    public boolean isExistsMobilePhone(String mobilePhone); 
    //判断密码
    public boolean isExistsPassword(String password);
    //修改密码
    public boolean changepassword(int userInfoId,String oldpassword,String newpassword);
    public boolean isExistsEmail(String email);
    public boolean UpdateUserInfo(UserInfo userInfo);
    public boolean changeheadImage(UserInfo userInfo);

	/**
	 * 判断不是本人的email是否已经存在
	 * @param email  邮箱名称
	 * @param userId  用户标识
	 * @return 如果不是本人的email已存在返回true，否则返回false
	 */
	public boolean isExistsEmail(String email,int userId) ;

}
