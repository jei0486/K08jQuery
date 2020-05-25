package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OracleDAO {
	
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	public OracleDAO() {

		try {
			Context initCtx = new InitialContext();

			DataSource source = (DataSource) initCtx.lookup("java:comp/env/jdbc/myoracle");
			con = source.getConnection();
			System.out.println("DBCP연결성공");
			
		} catch (Exception e) {
			System.out.println("DB연결실패");
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println("자원반납시 예외발생");
			e.printStackTrace();
		}
	}
	
	
	
	
public boolean isMember(String id, String pass) {
		
		String sql = "SELECT COUNT(*) FROM member "
					+ " WHERE id=? AND pass=?";
		//count(*) = 0혹은 결과의 수
		int isMember = 0;
		
		try {
			//prepare 객체로 쿼리문 전송
			psmt = con.prepareStatement(sql);
			//인파라미터 설정
			psmt.setString(1, id);
			psmt.setString(2, pass);
			//쿼리실행
			rs = psmt.executeQuery();
			//실행결과를 가져오기 위해서 next()호출
			rs.next(); //반환값은 boolean (true or false)
			
			isMember = rs.getInt(1);
			System.out.println("affected:"+isMember);
			if(isMember==0) {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
