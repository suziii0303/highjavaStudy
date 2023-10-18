package kr.or.ddit.basic;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MyBatisSqlSessionFactory;
import kr.or.ddit.vo.LprodVO;

//Jdbc예제 중 JdbcTest05.java를 Mybatis용으로 변환하시오.
public class JdbcToMybatisExplain {
   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      
      //------------------------------------
//      InputStream in = null;
//      SqlSessionFactory sqlSessionFactory = null;
//      try {
//         in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
//         sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
//      } catch (Exception e) {
//         System.out.println("myBatis 연결 실패 ... ");
//         e.printStackTrace();
//      }
      //------------------------------------
      
      SqlSession session = null;
      try {
         session =MyBatisSqlSessionFactory.getSqlSession();
         
         //작업 1)Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1 크게 한다.
         int maxNum = session.selectOne("jdbc.selectMax");
         maxNum++;
         
         //작업2)입력 받은 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
         String gu;
         int count = 0;
         do {
            System.out.print("상품 분류 코드(lprod_gu) 입력 >> ");
            gu = scan.nextLine();
            
            count = session.selectOne("jdbc.selectGu",gu);
            
            if(count>0) {
               System.out.println(gu+"는 이미 존재하는 상품 분류 코드입니다");
               System.out.println("다시 입력하세요");
            } 
         }while(count>0);
         
         System.out.print("상품 분류명(lprod_nm) 입력 >> ");
         String nm = scan.nextLine();
         
         //입력한 데이터들을 VO객체에 저장한다.
         LprodVO lvo = new LprodVO();
         lvo.setLprod_id(maxNum);
         lvo.setLprod_gu(gu);
         lvo.setLprod_nm(nm);
         
         //작업3)상품 분류명(LPROD_NM) 입력 받아 DB에 INSERT하기
         int cnt = session.insert("jdbc.insertAll",lvo);
         
         if(cnt>0) {
            System.out.println("insert 성공!!!");
            session.commit();
         } else {
            System.out.println("insert 실패!!!");
         }
         
      } finally {
         if(session!=null) session.close();
         
         scan.close();
      }
   }
}