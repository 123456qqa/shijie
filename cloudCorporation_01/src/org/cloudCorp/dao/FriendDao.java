package org.cloudCorp.dao;

import java.util.List;

import org.cloudCorp.model.Friend;
import org.cloudCorp.model.UserInfo;

public interface FriendDao {
	
	/**
	 * 显示某个用户可以加为好友的用户列表
	 * @param userInfoId 用户标识
	 * @return 可以加为好友的用户列表
	 */
	public List<UserInfo> getToAddUserList(int userInfoId) ;

	
	
	/**
	 * 确认加好友的申请
	 * @param friendId 好友标识
	 * @param  action   动作名称，可以为同意（agree）或拒绝（reject）
	 * @return 确认成功返回true，否则返回false
	 */
	public  boolean  confirmFriend(int friendId,String action) ;
	
	/**
	 * 获取某个用户的好友列表
	 * @param userId 用户标识
	 * @return  某个用户的好友列表
	 */
	public List<UserInfo> getMyFriendList(int userinfoId ) ;






	public void applyFriend(Friend friend);



	public List<Friend> getAppliedList(int userInfoBId);
	


}
