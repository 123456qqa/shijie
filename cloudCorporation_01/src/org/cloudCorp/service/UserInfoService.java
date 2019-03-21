package org.cloudCorp.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloudCorp.dao.UserInfoDao;
import org.cloudCorp.dao.Impl.UserInfoDaoImpl;
import org.cloudCorp.model.UserInfo;

public class UserInfoService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("GBK");

		String op = request.getParameter("op");
		System.out.println("op="+op);
		if (op != null) {
			try {
				if (op == "register" || "register".equals(op)) {
					// 注册
					register(request, response);
				} else if (op == "login" || "login".equals(op)) {
					// 登录
					login(request, response);
				} else if (op == "changePassword" || "changePassword".equals(op)) {
					// 修改密码
					changePassword(request,response) ;
				} else if ( op == "logout" || "logout".equals(op)  ) {
					//退出
					logout(request,response) ;
				}else if ( op == "isExistsEmail" || "isExistsEmail".equals(op) ) {
					//判断email是否存在
					isExistsEmail(request,response) ;
				}else if(op=="UpdateUserInfo" || "UpdateUserInfo".equals(op)) {
					//修改用户信息
					UpdateUserInfo(request,response);
				}else if ( op == "isExistsEmail" || "isExistsEmail".equals(op) ) {
					//判断email是否存在
					isExistsEmail(request,response) ;
				}
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void UpdateUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String userName= request.getParameter("userName");
	       String trueName=request.getParameter("trueName");
	       String email=request.getParameter("email");
	       String gender=request.getParameter("gender");
	       String brief=request.getParameter("brief");
	      UserInfo userInfo=(UserInfo) request.getSession().getAttribute("userInfo");
	      if(userInfo==null) {
	    	  request.getRequestDispatcher("main.jsp").forward(request, response);
	      
	      }else {
	    	  int userInfoId=userInfo.getUserInfoId();
	    	  UserInfo us=new UserInfo();
	    	  us.setBrief(brief);
	    	  us.setEmail(email);
	    	  us.setGender(gender);
	    	  us.setTrueName(trueName);
	    	  us.setUserName(userName);
	    	  us.setUserInfoId(userInfoId);
	    	  
	    	  UserInfoDao userInfoDao=new UserInfoDaoImpl();
	    	 boolean success= userInfoDao.UpdateUserInfo(us);
	    	  if(success) {
	    		  request.getRequestDispatcher("main.jsp").forward(request, response);
        	  } else {
        		  
        		  response.sendRedirect("userInfo/updateUserInfo.jsp");
        	  
	    		  
	    		  
	    	  }
	    	  
	      }
	       
		
	}

	@SuppressWarnings("unused")
	private void isExistsEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  String email = request.getParameter("email") ;
		  UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo") ;
		  int userId = userInfo.getUserInfoId() ;
		  UserInfoDao userInfoDao=new UserInfoDaoImpl();
		  boolean result = userInfoDao.isExistsEmail(email);
		  if (userInfo != null) {
				//存在
				
				try {
					request.getRequestDispatcher("main.jsp").forward(request, response);
				} catch (ServletException e) {
					
					e.printStackTrace();
				}
			} else {
				// 不存在
				response.sendRedirect("index.jsp");
			}
		 
	}

	//退出
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 request.getSession().invalidate() ; //session失效
		 response.sendRedirect("index.jsp"); //重定向到登录页面
	}

	// 修改密码
	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     UserInfo userInfo =  (UserInfo)request.getSession().getAttribute("userInfo") ;
		int userInfoId = userInfo.getUserInfoId() ;
		String oldPassword = request.getParameter("oldPassword") ;
		String newPassword = request.getParameter("newPassword") ;
		UserInfoDao userInfoDao = new UserInfoDaoImpl() ;
		boolean success = userInfoDao.changepassword(userInfoId, oldPassword, newPassword) ;
		if ( success ) {
			//修改密码成功，返回主页面
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			//修改密码失败，返回到修改密码页面
			response.sendRedirect("userInfo/changePassword.jsp");
		}
	}

	// 登录
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountNo = request.getParameter("accountNo");
		String password = request.getParameter("password");
		UserInfoDao userInfoDao = new UserInfoDaoImpl();
		UserInfo userInfo = userInfoDao.login(accountNo, password);
		if (userInfo != null) {
			// 登录成功
			request.getSession().setAttribute("userInfo", userInfo); // 将登录成功的用户对象保存到session中
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			// 登录失败
			response.sendRedirect("index.jsp");
		}

	}

	// 注册
	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mobilePhone = request.getParameter("mobilePhone");
		String password = request.getParameter("password");
		UserInfo userInfo = new UserInfo();
		userInfo.setMobilePhone(mobilePhone);
		userInfo.setPassword(password);
		UserInfoDao userInfoDao = new UserInfoDaoImpl();
		boolean success = userInfoDao.register(userInfo);
		if (success) {
			// 注册成功
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			// 注册失败
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
