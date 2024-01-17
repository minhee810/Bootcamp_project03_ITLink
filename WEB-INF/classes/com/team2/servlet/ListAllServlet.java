 package com.team2.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team2.dao.BoardDAO;
import com.team2.db.MySQLConnector;


@WebServlet("/listAll")
public class ListAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLConnector mysql = new MySQLConnector();
		System.out.println("12312321312");                                
		ArrayList<BoardDAO> list = new ArrayList<BoardDAO>();
		list = mysql.selectBoardAll();
		System.out.println(list);
		request.setAttribute("DBList", list);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("ITList.jsp");
	    requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

