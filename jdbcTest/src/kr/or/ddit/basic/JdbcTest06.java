package kr.or.ddit.basic;

import java.nio.file.spi.FileSystemProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

/*
	회원을 관리하는 프로그램 작성하시오...(MYMEMBER 테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오. ( CRUD 기능 구현하기 )
	
	메뉴예시)
	----------------------
	1. 자료추가			-----> insert (C)
	2. 자료 삭제			-----> delete (D)
	3. 자료 수정			-----> update (U)	
	4. 전체 자료 출력		-----> select (R)
	0. 작업 끝.
	----------------------
	
	조건)
	1) '자료추가' 메뉴에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력받는다.)
	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	3) 자료 수정에서 '회워ID'는 변경되지 않는다.
 */

public class JdbcTest06 {
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		JdbcTest06 jd = new JdbcTest06();

		jd.memberStrart();
	}

	// 시작메서드
	public void memberStrart() {
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 추가
				insertMember();
				break;
			case 2: // 삭제
				deleteMember();
				break;
			case 3: // 수정
				udateMember();
				break;
			case 4: // 전체 출력
				displayAll();
				break;
			case 5: // 수정 ==> 원하는 항목만 수정하기
				updateMember2();
				break;
			case 0: // 종료
				System.out.println();
				System.out.println("감사합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 선택했습니다. 다시 선택하세요...");

			}
		}
	}
/*
	private int displayUpdateMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("      1. 비밀번호 변경   	");
		System.out.println("      2. 이름 변경	   	");
		System.out.println("      3. 전화번호 변경   	");
		System.out.println("      4. 주소 변경   	");
		System.out.println("      0. 작업 끝.   	");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >>");
		return sc.nextInt();

	}
	
	private void udateMember2() {
		String newUpdate ="";
		String newCol = "";
		
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.println("회원ID >>");
		String memId= sc.next();
		
		int count  = getMemberCount(memId);
		
		if(count==0) {	//회원이 없을  때
			System.out.println(memId +"은(는) 없는 회원 ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		
		try {
			conn = DBUtil.getConnection();
		while (true) {
		int choice = displayUpdateMenu();
		switch (choice) {
		
		case 1:
			System.out.println("새로운 비밀번호 >>");
			 newUpdate = sc.next();
			 newCol = "mem_pass";
			
			break;
		case 2:
			System.out.println("새로운 회원이름 >>");
			newUpdate = sc.next();
			newCol = "mem_name";
			break;
		case 3:
			System.out.println("새로운 전화번호 >>");
			newUpdate = sc.next();
			newCol = "mem_tel";
			break;
		case 4:
			sc.nextLine(); // 입력 버퍼 비우기
			System.out.println("새로운 회원 주소 >>");
			newUpdate = sc.nextLine();
			newCol = "mem_addr";
			break;

		default:
			System.out.println("잘못입력 했습니다.");
			break;
		}
		}
		
		String sql = "update mymember set " + newCol +" =? where mem_id = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, memId);
		
		}catch (SQLException e) {
			e.getStackTrace();
		} finally {
			if (conn != null)try {conn.close();} catch (SQLException e2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e2) {}
		}
*/
	
		

	// 메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("      1. 자료추가   	");
		System.out.println("      2. 자료 삭제   	");
		System.out.println("      3. 자료 수정	   	");
		System.out.println("      4. 전체 자료 출력   	");
		System.out.println("      5. 자료 수정2   	"); // 원하는 항목 1개만 추가하기
		System.out.println("      0. 작업 끝.   	");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >>");
		return sc.nextInt();

	}

	// 회원 정보를 추가하는 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");

		String memId = null; // 회원ID가 저장될 변수
		int count = 0;
		do {
			sc.nextLine();
			System.out.print("id 입력 >>");
			memId = sc.nextLine();

			count = getMemberCount(memId);

			if (count > 0) {
				System.out.println("입력하신" + memId + "는(은) 이미 등록된 회원ID입니다.");
				System.out.println("다시 입력하세요");
			}

		} while (count > 0);

		System.out.println("pass 입력 >>");
		String memPass = sc.next();
		System.out.println("이름 입력 >>");
		String memName = sc.next();
		System.out.println("전화번호 입력 >>");
		String memTel = sc.next();

		sc.nextLine(); // 입력 버퍼 비우기...
		System.out.println("주소 입력 >>");
		String memAddr = sc.nextLine();

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();

			String sql = "insert into mymember ( mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ "values(?, ?, ? ,? , ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(memId + "회원 정보 등록 성공");
			} else {
				System.out.println(memId + "회원 정보 등록 실패");
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			if (conn != null)try {conn.close();} catch (SQLException e2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e2) {}
		}

	}

	// 회원ID를 매개변수로 받아서 해당 회원이 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		int count = 0; // 반환값이 저장될 변수

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();

			String sql = "select count(*) cnt from mymember where mem_id =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("cnt");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException e2) {}
			if (conn != null)try {conn.close();} catch (SQLException e2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e2) {}
		}

		return count;
	}
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요");
		System.out.println("id 입력 >>");
		String memId = sc.next();

		PreparedStatement pstmt = null;
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			int count = 0;

			String sql = "delete from mymember where mem_id=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(memId + "삭제성공");
			} else {
				System.out.println(memId + "회원은 없거나 삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)try {conn.close();} catch (SQLException e2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e2) {}
		}
	}
	//원하는 회원 정보를 수정하는 메서드(선생님)
	   private void updateMember2() {
	      System.out.println();
	      
	      System.out.println("수정할 회원 정보를 입력하세요...");
	      System.out.print("수정할 ID입력>> ");
	      String memId = sc.next();
	      int count = getMemberCount(memId);
	      if(count==0) {
	         System.out.println(memId + " 는 없는 회원입니다.");
	         System.out.println("수정 작업을 종료합니다.");
	         return;
	      }
	      
	      int num;   //수정을 원하는 항목의 선택 번호가 저장될 변수
	      String updateField = null;   //수정할 컬럼명이 저장될 변수
	      String updateTitle = null;   //입력할 때 보여줄 제목
	      
	      do {
	         System.out.println();
	         System.out.println("수정할 항목을 선택하세요....");
	         System.out.println("1.비밀번호   2.회원이름   3.전화번호   4.회원주소");
	         System.out.println("-----------------------------------------");
	         System.out.print("수정할 항목 선택 >> ");
	         num = sc.nextInt();
	         
	         switch(num) {
	         case 1:
	            updateField = "mem_pass";
	            updateTitle = "비밀번호";
	            break;
	         case 2:
	            updateField = "mem_name";
	            updateTitle = "회원이름";
	            break;
	         case 3:
	            updateField = "mem_tel";
	            updateTitle = "전화번호";
	            break;
	         case 4:
	            updateField = "mem_addr";
	            updateTitle = "회원주소";
	            break;
	         default :
	            System.out.println("다시 입력하세요.");
	         }
	      }while(num<1||num>4);
	      sc.nextLine();	// 입력버퍼 비우기
	      System.out.println();
	      System.out.println("수정할"+ updateTitle + ">>");
	      String updateData = sc.nextLine();  // 수정할 데이터 이름
	      
	      Connection conn =null;
	      PreparedStatement pstmt = null;
	      try {
			conn = DBUtil.getConnection();
			
			String sql ="update mymember set " + updateField +" =? where mem_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1,updateField); // 컬럼명 자리에 작은 따옴표를 자동으로 붙여기때문에 사용x
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,updateData);
			pstmt.setString(2,memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("수정 작업 성공");
			}else {
				System.out.println("수정 작업 실패");
			}
			
			
	      } catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(pstmt!= null)try {pstmt.close();} catch (SQLException e) {}
			if(conn!= null)try {conn.close();} catch (SQLException e) {}
		}
	   }


	// 회원 정보를 수정하는 메서드 ==> 전체 항목 수정
	private void udateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.println("회원ID >>");
		String memId = sc.next();

		int count = getMemberCount(memId);

		if (count == 0) {// 회원이 없을 때
			System.out.println(memId + "은(는) 없는 회원 ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		System.out.println("새로운 비밀번호 >>");
		String newMemPass = sc.next();

		System.out.println("새로운 회원이름 >>");
		String newMemName = sc.next();

		System.out.println("새로운 전화번호 >>");
		String newMemTel = sc.next();

		sc.nextLine(); // 입력 버퍼 비우기
		System.out.println("새로운 회원 주소 >>");
		String newMemAddr = sc.nextLine();

		PreparedStatement pstmt = null;
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();

			String sql = "update mymember set mem_pass = ?,mem_name =?," + "mem_tel =?, mem_addr = ?"
					+ "where mem_id=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, newMemPass);
			pstmt.setString(2, newMemName);
			pstmt.setString(3, newMemTel);
			pstmt.setString(4, newMemAddr);
			pstmt.setString(5, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(memId + "회원 정보 수정 왼료");
			} else {
				System.out.println(memId + "회원 정보 수정 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)try {conn.close();} catch (SQLException e2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e2) {}
		}
	}

	private void displayAll() {
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println(" ID    비밀번호    이름    전화번호    주소 ");
		System.out.println("--------------------------------------");

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
//			conn = DBUtil.getConnection();
//			conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String memId = rs.getString("mem_id");
				String mem_pass = rs.getString("mem_pass");
				String mem_name = rs.getString("mem_name");
				String mem_tel = rs.getString("mem_tel");
				String mem_addr = rs.getString("mem_addr");

				System.out.println(memId + "   " + mem_pass + "   " + mem_name + "    " + mem_tel + "   " + mem_addr);

//				System.out.println("MEM_ID : "+ rs.getString("mem_id"));
//				System.out.println("MEM_PASS : "+rs.getString("mem_pass"));
//				System.out.println("MEM_NAME : "+ rs.getString("mem_name"));
//				System.out.println("MEM_TEL : "+ rs.getString("mem_tel"));
//				System.out.println("MRM_ADDR : "+ rs.getString("mem_addr"));
//				
			}
			System.out.println("---------------------------------------");
			System.out.println("전체 자료 출력 끝 ");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)try {rs.close();} catch (SQLException e2) {}
			if (conn != null)try {conn.close();} catch (SQLException e2) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException e2) {}
		}

	}

}
