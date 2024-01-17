package com.team2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team2.db.MySQLConnector;

/**
 * @author 임제정  회원가입 정보 추가 페이지
 * */
@WebServlet("/InsertUServlet")
public class InsertUServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MySQLConnector mySQLConnector = new MySQLConnector();



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
				
		String uid = request.getParameter("u_id");
		String upw = request.getParameter("u_pw");
		String uname = request.getParameter("u_name");	
		String usWriter = request.getParameter("uWriter");	
		
		if (mySQLConnector.checkId(uid) != null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>"
					+ "alert('중복된 회원입니다.'); window.location.href='ITJoin.jsp'</script>");
			
			writer.close();
		}
		else {
			mySQLConnector.insertUser(uid, upw, uname, usWriter);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>"
					+ "alert('환영합니다. 회원가입 되었습니다.'); window.location.href='ITLogin.jsp'</script>");
			
			writer.close();
		
		}
	}

	                
	
                                                 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
