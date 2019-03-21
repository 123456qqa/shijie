package org.cloudCorp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloudCorp.dao.FriendDao;
import org.cloudCorp.dao.Impl.FriendDaoImpl;
import org.cloudCorp.model.Friend;
import org.cloudCorp.model.UserInfo;

public class FriendService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String op = request.getParameter("op");
		if (op != null) {
			try {
				if (op == "toAddList" || "toAddList".equals(op)) {
					// 鏄剧ず鍙互鍔犱负濂藉弸鐨勭敤鎴峰垪琛�
					toAddList(request, response);
				} else if ( op == "applyFriend" || "applyFriend".equals(op) ) {
					//鐢宠鍔犱负濂藉弸
					applyFriend(request,response) ;
				} else if ( op == "showAppliedList" || "showAppliedList".equals(op) ) {
					//鏄剧ず宸茬敵璇峰姞濂藉弸鐨勫垪琛�
					showAppliedList(request,response) ;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
	}
	
	//鏄剧ず宸茬敵璇峰姞濂藉弸鐨勫垪琛�
	private void showAppliedList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		if (userInfo != null) {
			//宸茬櫥褰�
			int userInfoBId = userInfo.getUserInfoId();
			FriendDao friendDao = new FriendDaoImpl() ;
			List<Friend> appliedList = friendDao.getAppliedList(userInfoBId) ;
			request.setAttribute("appliedList", appliedList);
			request.getRequestDispatcher("friend/showAppliedList.jsp").forward(request, response);
		}else {
			//娌℃湁鐧诲綍
			response.sendRedirect("index.jsp");
		}
		
	}

	//鐢宠鍔犱负濂藉弸
	private void applyFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		if (userInfo != null) {
			int userInfoAId = userInfo.getUserInfoId();
			String strUserInfoBId = request.getParameter("userInfoBId") ;
			int userInfoBId = Integer.parseInt(strUserInfoBId) ;
			Friend friend = new Friend() ;
			friend.setUserInfoAId(userInfoAId);
			friend.setUserInfoBId(userInfoBId);
			friend.setStatus("applied");
			FriendDao friendDao = new FriendDaoImpl() ;
			friendDao.applyFriend(friend) ;
			response.sendRedirect("friendService?op=toAddList");
			//鎴�  toAddList(request,response) ;
		}else {
			// 娌℃湁鐧诲綍
			response.sendRedirect("index.jsp");
		}
		
	}

	// 鏄剧ず鍙互鍔犱负濂藉弸鐨勭敤鎴峰垪琛�
	private void toAddList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		if (userInfo != null) {
			int userInfoId = userInfo.getUserInfoId();
			FriendDao friendDao = new FriendDaoImpl();
			List<UserInfo> toAddUserList = friendDao.getToAddUserList(userInfoId);
			request.setAttribute("toAddUserList", toAddUserList);
			request.getRequestDispatcher("friend/showAddList.jsp").forward(request, response);
		} else {
			// 娌℃湁鐧诲綍
			response.sendRedirect("index.jsp");
		}

	}

}
