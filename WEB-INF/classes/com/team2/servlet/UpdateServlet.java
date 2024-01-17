package com.team2.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.team2.db.MySQLConnector;
/**
 * 	글 정보 업데이트 - 디비 쿼리 가져와서 수정
 * @author 효경
 *
 */
@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MySQLConnector mysql = new MySQLConnector();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
		
			int b_No = Integer.parseInt(request.getParameter("b_No"));
			String b_Title = request.getParameter("b_Title");
			String b_Link = request.getParameter("b_Link");
			mysql.update(b_Title, b_Link, b_No);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>"
					+ "alert('글이 수정되었습니다'); window.location.href='ITListInfo.jsp?b_No="+ b_No + "'</script>");
			
			writer.close();
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
