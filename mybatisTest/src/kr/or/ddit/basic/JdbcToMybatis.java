package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.vo.LprodVO;

// Jdbc예제 중 JdbcTest05.java Mybatis용으로 변환하시오.
public class JdbcToMybatis {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		LprodVO lvo = new LprodVO();
		SqlSession session = null;

		//----------------------------------------------------------------
		
		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;

		try {
			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			System.out.println("myBatis 초기화 실패");
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("실패");
				}
		}

		// -------------------------------------------------------

		// -----------------------------------------------------------

		// 입력 받은 Lprod_gu 이미 등록되어 있으면 다시 입력 받아서 처리한다.
		System.out.println("select 작업 시작...");
		
		
		String lprodGu = null;
		while (true) {
			System.out.print("상품분류 코드 (Lprod_gu) 입력 :");
			lprodGu = scan.next();

			try {

				session = sqlSessionFactory.openSession();

				int insertCnt = session.selectOne("jdbc.selectGu", lprodGu);
				if (insertCnt > 0) {

					System.out.println("다시 입력하세요");
				} else {
					System.out.println("가 능");
					lvo.setLprod_gu(lprodGu);
					break;
				}
			} finally {

				if (session != null)
					session.close();
			}
		}
		// 최대 lprodid를 구하고 1증가해서 등록
		int MaxlprodId = 0;
		
		try {
			session = sqlSessionFactory.openSession();
			
			// selectOne은 레코드 하나, selectList은 여러 레코드
			MaxlprodId = session.selectOne("jdbc.selectMax");
			lvo.setLprod_id(MaxlprodId+1);
			
		} finally {
			if (session != null) session.close();
				
		}
		// lprodnm을 입력하고 등록
		String lprodNm = null;
		System.out.print("상품명 (Lprod_NM) 입력 :");
		lprodNm = scan.next();
		
		lvo.setLprod_id(MaxlprodId);
		lvo.setLprod_gu(lprodGu);
		lvo.setLprod_nm(lprodNm);
		
		try {

			session = sqlSessionFactory.openSession();
			
			int insertCnt = session.insert("jdbc.insertAll",lvo);
			
			if(insertCnt>0) {
				System.out.println("insert 작업 성공");
	           
				session.commit();
			}else {
				System.out.println("insert 작업 실패");
			}
		} finally {
			if(session!=null)session.close();
		}
	}
	

}
