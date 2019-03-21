package org.cloudCorp.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cloudCorp.dao.UserInfoDao;
import org.cloudCorp.dao.Impl.UserInfoDaoImpl;
import org.cloudCorp.model.UserInfo;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class FileUploadService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("op:" + request.getParameter("op"));
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		response.setCharacterEncoding("GBK");
		try {
			changeheadImage(request, response);
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SmartUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			request.getRequestDispatcher("userInfo/UpdateUserInfo").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void changeheadImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SmartUploadException {
		SmartUpload smartUpload = new SmartUpload();
		// SmartUpload接管请求和响应

		smartUpload.initialize(this.getServletConfig(), request, response);
		// 设置文件大小
		smartUpload.setMaxFileSize(10000000);

		// 文件上传
		smartUpload.upload();
		// 将文件保存到指定位置
		Files files = smartUpload.getFiles();
		File file = files.getFile(0);
		String orgName = file.getFileName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String strDate = sdf.format(new Date());
		String newFileName = strDate + orgName; // 保存后的文件名
		String path = "E:\\cloudCorp\\";
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		file.saveAs(path + newFileName); // 将上传后的文件保存到指定路径下
		// 将文件路径保存到UserInfo表中
		UserInfoDao userInfoDao = new UserInfoDaoImpl();
		userInfo.setHeadImage(newFileName);
		userInfoDao.changeheadImage(userInfo);

	}
}
