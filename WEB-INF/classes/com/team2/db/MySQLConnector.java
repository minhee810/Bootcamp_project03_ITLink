package com.team2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.team2.dao.AdminDAO;
import com.team2.dao.BoardDAO;
import com.team2.dao.UsersDAO;

public class MySQLConnector extends Querys {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String db_name = "mini02"; // db 이름 넣기
	private String url = "jdbc:mysql://localhost:3306/" + db_name
			+ "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private String id_mysql = "root";
	private String pw_mysql = "7284";

	public Connection conn = null; // MySQL 접속 결과(상태) 저장

	public MySQLConnector() {
		connectMySQL();
	}

	public void connectMySQL() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id_mysql, pw_mysql);

		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
	}

	/**
	 * @author 임제정
	 */
	@Override // 사용자 회원가입
	public void insertUser(String u_id, String u_pw, String u_name, String uWriter) {
		PreparedStatement pstmt = null;
		// "insert into board (u_id, u_pw, u_name, uWriter) values (?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(queryInsertUser);
			pstmt.setString(1, u_id);
			pstmt.setString(2, u_pw);
			pstmt.setString(3, u_name);
			pstmt.setString(4, uWriter);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("insert()  ERR : " + e.getMessage());
		} finally {
			this.close(pstmt);
		}

	} // insertUser() END

	/**
	 * @author 임제정 로그인 할때 사용자의 정보 가져오기
	 */
	public UsersDAO selectgetUser(String u_id, String u_pw) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UsersDAO user = new UsersDAO();
		System.out.println(u_id + " " + u_pw);
		try {
			pstmt = conn.prepareStatement(selectUIdPw);
			pstmt.setString(1, u_id);
			pstmt.setString(2, u_pw);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user.setU_id(rs.getString("u_id"));
				user.setU_pw(rs.getString("u_pw"));
				user.setU_name(rs.getString("u_name"));
				user.setuWriter(rs.getString("uWriter"));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("getUser ERR : " + e.getMessage());
		} finally {

		}
		return user;

	} // selectgetUser end

	public void queryAdminWrite(String b_Title, String b_Link, String b_Category, int b_hit, int b_like) {
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			pstmt = conn.prepareStatement(queryAdminWrite);

			pstmt.setString(1, b_Title);
			pstmt.setString(2, b_Link);
			pstmt.setString(3, b_Category);
			pstmt.setInt(4, b_hit);
			pstmt.setInt(5, b_like);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("queryAdminWrite ERR : " + e.getMessage());
		}

	}

	/**
	 * 글 정보 가져와서 ITList.jsp에 출력하기
	 * 
	 * @author 최호준
	 *
	 */
	@Override
	public ArrayList<BoardDAO> selectBoardAll() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BoardDAO> boardlist = new ArrayList<BoardDAO>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectBoardAll);

			while (rs.next()) {
				int b_no = rs.getInt("b_no");
				String b_title = rs.getString("b_title");
				String reg_date = rs.getString("reg_date");
				int b_hit = rs.getInt("b_hit");
				int b_like = rs.getInt("b_like");

				boardlist.add(new BoardDAO(b_no, b_title, reg_date, b_hit, b_like));
			}
			System.out.println(boardlist);
		} catch (SQLException e) {
			System.out.println("selectBoardAll Err" + e.getMessage());
		}
		return boardlist;
	} // selectBoardAll End

	/**
	 * 글 정보 수정
	 * 
	 * @author 김효경 => 호준(해결): 쿼리문 오류
	 */

	@Override
	// final String queryUPDATE = "update board set b_Title = ?, b_Link = ?";
	public void update(String b_Title, String b_Link, int b_num) { // 테이블 데이터 수정

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(queryUPDATE);
			pstmt.setString(1, b_Title);
			pstmt.setString(2, b_Link);
			pstmt.setInt(3, b_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("update: " + e.getMessage());
		}
//		finally {
//			close(pstmt);
//		}
	}

	/**
	 * 글 정보 삭제
	 * 
	 * @author 김효경 => 호준(해결) : 매게변수 Title,Link 없애줌
	 */
	@Override
	public void delete(int b_No) {

		PreparedStatement pstmt = null;
		try {

			pstmt = conn.prepareStatement(queryDELETE);
			pstmt.setInt(1, b_No);

			int n = pstmt.executeUpdate();
			if (n > 0)
				System.out.println("----- delete -----");
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt);
		}
	}

	// final String querySELECTONE = "select * from board where b_No = ?";
	public BoardDAO selectOne(int b_No) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDAO boards = new BoardDAO();
		try {
			pstmt = conn.prepareStatement(querySELECTONE);
			pstmt.setInt(1, b_No);
			rs = pstmt.executeQuery();

			System.out.println("----- selectOne -----");
			while (rs.next()) {
				// System.out.println(rs.getInt(1) + " : " + rs.getString(2));
				boards.setB_Title(rs.getString("b_Title"));
				boards.setB_Link(rs.getString("b_Link"));
				boards.setB_Category(rs.getString("b_Category"));
				boards.setB_hit(rs.getInt("b_hit"));
				boards.setB_like(rs.getInt("b_like"));

				System.out.println(rs.getLong("b_No"));
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} finally {
			close(pstmt, rs);
		}
		return boards;
	}

	@Override
	public ArrayList<BoardDAO> sortBoardLike() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BoardDAO> boardlist = new ArrayList<BoardDAO>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sortBoardLike);

			while (rs.next()) {
				int b_no = rs.getInt("b_no");
				String b_title = rs.getString("b_title");
				String reg_date = rs.getString("reg_date");
				int b_hit = rs.getInt("b_hit");
				int b_like = rs.getInt("b_like");

				boardlist.add(new BoardDAO(b_no, b_title,reg_date, b_hit, b_like));
			}
		} catch (SQLException e) {
			System.out.println("sortBoardLike Err" + e.getMessage());
		}
		return boardlist;
	}

	/**
	 * 글 정보 가져와서 ITList.jsp에 출력하기 (최근순 정렬)
	 * 
	 * @author 최호준
	 *
	 */
	@Override
	public ArrayList<BoardDAO> sortBoardRecent() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BoardDAO> boardlist = new ArrayList<BoardDAO>();
		String word = " desc"; // 내림차순 단어
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sortBoard + word);

			while (rs.next()) {
				int b_no = rs.getInt("b_no");
				String b_title = rs.getString("b_title");
				String reg_date = rs.getString("reg_date");
				int b_hit = rs.getInt("b_hit");
				int b_like = rs.getInt("b_like");

				boardlist.add(new BoardDAO(b_no, b_title, reg_date, b_hit, b_like));
			}
		} catch (SQLException e) {
			System.out.println("sortBoardRecent Err" + e.getMessage());
		}
		return boardlist;
	}

	/**
	 * 글 정보 가져와서 ITList.jsp에 출력하기 (오래된 순 정렬)
	 * 
	 * @author 최호준
	 *
	 */
	@Override
	public ArrayList<BoardDAO> sortBoardOlder() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BoardDAO> boardlist = new ArrayList<BoardDAO>();
		String word = " desc"; // 내림차순 단어
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sortBoard);

			while (rs.next()) {
				int b_no = rs.getInt("b_no");
				String b_title = rs.getString("b_title");
				String reg_date = rs.getString("reg_date");
				int b_hit = rs.getInt("b_hit");
				int b_like = rs.getInt("b_like");

				boardlist.add(new BoardDAO(b_no, b_title, reg_date, b_hit, b_like));
			}
		} catch (SQLException e) {
			System.out.println("sortBoardOlder Err" + e.getMessage());
		}
		return boardlist;
	}

	/**
	 * 글 정보 가져와서 ITList.jsp에 출력하기 (조회수 순 정렬)
	 * 
	 * @author 최호준
	 *
	 */
	@Override
	public ArrayList<BoardDAO> sortBoardHit() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BoardDAO> boardlist = new ArrayList<BoardDAO>();
		String word = " desc"; // 내림차순 단어
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sortBoardHit);

			while (rs.next()) {
				int b_no = rs.getInt("b_no");
				String b_title = rs.getString("b_title");
				String reg_date = rs.getString("reg_date");
				int b_hit = rs.getInt("b_hit");
				int b_like = rs.getInt("b_like");

				boardlist.add(new BoardDAO(b_no, b_title, reg_date, b_hit, b_like));
			}
		} catch (SQLException e) {
			System.out.println("sortBoardHit Err" + e.getMessage());
		}
		return boardlist;
	}

	/**
	 * index값 가져와서 글 정보 출력
	 * 
	 * @author 최호준
	 *
	 */
	@Override
	public BoardDAO selectNum(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDAO result = null;
		try {
			pstmt = conn.prepareStatement(selectNum);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int No = rs.getInt("b_No");
				String Title = rs.getString("b_Title");
				String Link = rs.getString("b_Link");
				String Category = rs.getString("b_Category");
				String reg_date = rs.getString("reg_date");
				int hit = rs.getInt("b_hit");
				int like = rs.getInt("b_like");

				result = new BoardDAO(No, Title, Link, Category, reg_date, hit, like);
			}
			System.out.println("++" + result);
		} catch (SQLException e) {
			System.out.println("selectNum Err" + e.getMessage());
		}
		return result;

	} // selectNum End

	/**
	 * 글 눌렀을 때 조회수 증가 오류- 메서드가 안 들어옴
	 * 
	 * @author 강민희
	 */
	@Override
	public void countHit(int b_No) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(countHit);
			pstmt.setInt(1, b_No);
			System.out.println(b_No);
			pstmt.executeUpdate();
			System.out.println("1줄이 업데이트 되었습니다");
		} catch (SQLException e) {
			System.out.println("countHit ERR : " + e.getMessage());
		} finally {
		}
	}

	/**
	 * @author 임제정
	 */
	// 좋아요 버튼 누르면 좋아요 수가 올라감
	public void updatelike(int b_No) {

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(updatelike);
			pstmt.setInt(1, b_No);

			pstmt.executeUpdate();
			return;

		} catch (SQLException e) {

			System.out.println("pushlike ERR :" + e.getMessage());
		}

	}



	@Override
	public ArrayList<UsersDAO> querySelectUserInfoList() {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<UsersDAO> users = new ArrayList<UsersDAO>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(querySelectUserInfoList);
			while (rs.next()) {
				int uNo = rs.getInt("u_No");
				String uId = rs.getString("u_id");
				String uPw = rs.getString("u_pw");
				String uName = rs.getString("u_name");
				String uWriter = rs.getString("uWriter");
				

				users.add(new UsersDAO(uNo, uId, uPw, uName, uWriter));
			}

		} catch (SQLException e) {
			System.out.println("querySelectUserInfoList Err" + e.getMessage());
		}
		return users;
	} // querySelectUserInfoList End

	
	/**
	 * @author 임제정 중복이아디 체크 메서드
	 */
	@Override
	public String checkId(String u_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user = null;
		try {
			pstmt = conn.prepareStatement(checkUID);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = rs.getString("u_id");
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("getUser ERR : " + e.getMessage());
		} finally {

		}
		return user;

	} // checkId end

	/** 자원 해제 메서드들 : Overloading 기법 **/
	// 3. close를 위한 공통 메서드들을 추상 클래스 내부로 이동
	public void close(Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Statement, ResultSet CLOSE  ERR : " + e.getMessage());
		}
	}

	public void close(PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("PreparedStatement, ResultSet  CLOSE  ERR : " + e.getMessage());
		}
	}

	public void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("PreparedStatement  CLOSE  ERR : " + e.getMessage());
		}
	}

	public void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Connection  CLOSE  ERR : " + e.getMessage());
		}
	}

}
