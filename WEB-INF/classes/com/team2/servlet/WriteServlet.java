package com.team2.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.dao.BoardDAO;
import com.team2.db.MySQLConnector;


@WebServlet("/WriteServlet")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 제목, 링크, 작성자, 카테고리, 비밀번호
    // dao 담고 파라미터로 하나만 전송
	
	private final BoardDAO board = new BoardDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청의 문자 인코딩을 UTF-8로 설정 
		request.setCharacterEncoding("UTF-8");
		// Database 객체 생성 , 연결 담당하는 커스텀 클래스  
		MySQLConnector mysql = new MySQLConnector();
		// 객체 사용하여 db에 연결, db연결 설정하는 메서드 
		mysql.connectMySQL();
				
		String title = request.getParameter("TITLE");
		String link = request.getParameter("LINK");
		String category = request.getParameter("CATEGORY");
		
		// DB로 보내기 완료! 
		mysql.queryAdminWrite(title, link, category,0,0);
	
		// 다음 페이지로 이동 
		response.sendRedirect("ITList.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
