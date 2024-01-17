package com.team2.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.team2.db.MySQLConnector;
@WebServlet("/uplike")
public class UpLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uplike = Integer.parseInt(request.getParameter("uppage"));
		
		MySQLConnector mysql = new MySQLConnector();
		
		mysql.updatelike(uplike);
		
		response.sendRedirect("ITListInfo.jsp?b_No="+uplike);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
