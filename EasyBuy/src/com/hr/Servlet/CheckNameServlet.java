package com.hr.Servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hr.dao.EASYBUY_USERDao;
import com.hr.util.EncodeUtil;
//检查姓名是否已经注册过
public class CheckNameServlet extends HttpServlet {
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	EncodeUtil.encode(req);
	resp.setContentType("text/html;charset=utf-8");
	String name = req.getParameter("name");
	int  count=EASYBUY_USERDao.selectByName(name);
	PrintWriter out = resp.getWriter();
	 if(count>0){
		out.print("false");
	}else{
		out.print("true");
	}
	out.close();
}
}
