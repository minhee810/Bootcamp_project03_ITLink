package com.team2.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.team2.dao.BoardDAO;
import com.team2.dao.UsersDAO;

/**
 *
 * 쿼리 문 작성(개인별로 작업하는 곳 추가하기!!)
 */
public abstract class Querys {

	final String selectBoardAll = "select b_no,b_title,reg_date,b_hit,b_like from board"; // 호준 - 리스트 보여줄 쿼리문

	final String queryInsertUser = "insert into Users(u_id, u_pw, u_name, uWriter) values (?, ?, ?, ?)"; // 제정 - 회원가입 한
																											// user의
	final String updatelike = "update board set b_like=b_like +1 where b_No=?";	 // 제정- 좋아요 수 카운트 쿼리																								// 데이터를 추가하는
																											// 쿼리

	// 민희 - 사용자 정보를 보여주는 리스트
	final String querySelectUserInfoList = "SELECT u_No, u_id, u_pw, u_name,uWriter FROM USERS";
	// 민희 - 사용자가 게시글을 작성 
	final String queryAdminWrite = "INSERT INTO BOARD (b_Title, b_Link, b_Category, reg_date, b_hit, b_like) VALUES (?,?,?,now(), ?, ?)";

	
	final String selectUIdPw = "select * from Users where u_id= ? and u_pw = ? ";
	// 제정 - 로그인 한 user의 데이터를 가져오는 쿼리
	final String queryDELETE = "delete from board where b_No=?";
	
	// 효경 - (오류)게시글 수정 쿼리 => 호준 - (해결) where 조건 걸어줌
	final String queryUPDATE = "update board set b_Title = ?, b_Link =? where b_No=?" ;
	
	final String selectNum = "select * from board where b_no=?";

	// 효경 - 게시글 상세 보기 쿼리
	final String querySELECTONE = "select * from board where b_No = ?"; 

	// 호준 - 좋아요 순 리스트 쿼리문
	final String sortBoardLike = "select b_no,b_title,reg_date,b_hit,b_like from board order by b_like desc";
	// 호준 - 최신순/오래된순 리스트 쿼리문
	final String sortBoard = "select b_no,b_title,reg_date,b_hit,b_like from board order by reg_date";
	// 호준 - 조회수 순 리스트 쿼리문
	final String sortBoardHit = "select b_no,b_title,reg_date,b_hit,b_like from board order by b_hit desc";

	// 민희 - 조회수 증가
	final String countHit = "UPDATE board SET b_hit = b_hit + 1 WHERE b_No = ?";
	
	
	
	// 제정 - 회원가입 중복 아이디 확인
	final String checkUID = "select * from users where u_id=?";
	
	public Querys() {

	}

	public abstract ArrayList<BoardDAO> selectBoardAll(); // 호준 - 리스트 보여줄 메서드
	
	// 민희- 사용자들의 개인 정보를 한번에 조회할 수 있는 쿼리 (완료)
	public abstract ArrayList<UsersDAO> querySelectUserInfoList();

	// 민희- 사용자가 게시글을 작성했을 때 db(board)에 insert 해주는 쿼리
	public abstract void queryAdminWrite(String b_Title, String b_Link, String b_Category, int b_hit, int b_like);

	public abstract void insertUser(String u_id, String u_pw, String u_name, String uWriter); // 제정- 사용자 회원가입 메서드

	public abstract UsersDAO selectgetUser(String u_id, String u_pw);// 제정_사용자 로그인 메서드

	public abstract void update(String b_Title, String b_Link,int b_num);
	
	public abstract void updatelike(int b_No);// 제정- 좋아요 수 업데이트 메서드

	public abstract void delete(int b_No); // 효경 - 게시글 삭제 메서드

	public abstract BoardDAO selectOne(int b_No); // 효경 - 게시글 상세 보기 메서드

	public abstract ArrayList<BoardDAO> sortBoardLike(); // 호준 - 좋아요 리스트 정렬 메서드

	public abstract ArrayList<BoardDAO> sortBoardRecent(); // 호준 - 최신순 리스트 정렬 메서드

	public abstract ArrayList<BoardDAO> sortBoardOlder(); // 호준 - 오래된순 리스트 정렬 메서드

	public abstract ArrayList<BoardDAO> sortBoardHit(); // 호준 - 조회수 리스트 정렬 메서드
	 
	public abstract void countHit(int b_No); // 민희- 조회수 증가

	public abstract BoardDAO selectNum(int num); 
	
	public abstract String checkId(String u_id);
	

}
