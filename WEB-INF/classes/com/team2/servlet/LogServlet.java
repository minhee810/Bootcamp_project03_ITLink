package com.team2.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.team2.dao.UsersDAO;
import com.team2.db.MySQLConnector;
@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MySQLConnector mySQLConnector = new MySQLConnector();
	private UsersDAO user = new UsersDAO();
	private final String adminID = "admin";
	private final String adminPW = "1234";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if (request.getParameter("ID").equals("admin") && request.getParameter("PW").equals("1234")) {
			session.setAttribute("admin", adminID);
			session.setAttribute("adminpw", adminPW);
			// session에 관리자 아디 비번 저장
			// 관리자 페이지로 redirect
			response.sendRedirect("listAll");	
		}
		else {
	
			user = mySQLConnector.selectgetUser(request.getParameter("ID"), request.getParameter("PW"));
			if (user.getU_id() == null ) {
//				response.sendRedirect("ITLogin.jsp");	
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>"
						+ "alert('계정이 없습니다.'); window.location.href='ITLogin.jsp'</script>");
				
				writer.close();
				// 아디 비번 잘못씀 -> 다시 로그인창으로
			}
			else {
				session.setAttribute("user", user);
				response.sendRedirect("listAll");	
				// session에 유저 아디 비번 저장
				// 유저 페이지로 redirect				
			}
		}
	
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
