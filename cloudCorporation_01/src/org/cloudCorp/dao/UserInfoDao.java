package org.cloudCorp.dao;

import org.cloudCorp.model.UserInfo;

public interface UserInfoDao {
	
	//�û�ע��
	public  boolean register(UserInfo userInfo) ;
	//�û���¼
    public UserInfo login(String accountNo, String password);
    //�ж��ֻ���
    public boolean isExistsMobilePhone(String mobilePhone); 
    //�ж�����
    public boolean isExistsPassword(String password);
    //�޸�����
    public boolean changepassword(int userInfoId,String oldpassword,String newpassword);
    public boolean isExistsEmail(String email);
    public boolean UpdateUserInfo(UserInfo userInfo);
    public boolean changeheadImage(UserInfo userInfo);

	/**
	 * �жϲ��Ǳ��˵�email�Ƿ��Ѿ�����
	 * @param email  ��������
	 * @param userId  �û���ʶ
	 * @return ������Ǳ��˵�email�Ѵ��ڷ���true�����򷵻�false
	 */
	public boolean isExistsEmail(String email,int userId) ;

}
