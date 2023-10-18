package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) lprod_id값을 2개 입력 받아서 두 값중 작은 값부터 큰 값사이의 자료들을 출력하시오.
//		(작은값과 큰값은 포함된다.)

public class JdbcTest03 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 Lprod_id값 입력 >> ");
		int num1 = scan.nextInt();
		System.out.print("두번째 Lprod_id값 입력 >> ");
		int num2 = scan.nextInt();
		
		int min = Math.min(num1, num2);	// 두 값 중 작은 값 구하기
		int max = Math.max(num1, num2);	// 두 값 중 큰 값 구하기
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pc12","java");
			
//			String sql = "select * from lprod where lprod_id >= "+min+" and lprod_id <="+max;
			String sql = "select * from lprod where lprod_id between "+min+" and "+max;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println(" == 쿼리문 처리 결과 ==");
			
			while(rs.next()) {
				
				System.out.println("LPROD_ID : "+ rs.getInt("lprod_id"));
				System.out.println("LPROD_GU : "+ rs.getString("lprod_gu"));
				System.out.println("LPROD_NM : "+ rs.getString("lprod_nm"));
				
				System.out.println("---------------------------------------");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// 5. 자원 반납
			if(rs != null) try {rs.close();} catch (SQLException e) {}
			if(stmt != null) try {stmt.close();} catch (SQLException e) {}
			if(conn != null) try {conn.close();} catch (SQLException e) {}
		}
	}
		
}

