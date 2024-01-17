package com.team2.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.team2.db.MySQLConnector;
@WebServlet("/uphit")
public class UpHitSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 조회수 증가하는 쿼리 문 가져와서 redirect 시키기
	 *
	 * @author 최호준
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLConnector mysql = new MySQLConnector();
		
		int hit = Integer.parseInt(request.getParameter("upPage"));
		mysql.countHit(hit);
		
		response.sendRedirect("ITList.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
