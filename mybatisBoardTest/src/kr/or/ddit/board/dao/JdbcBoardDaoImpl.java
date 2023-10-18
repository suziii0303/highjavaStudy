package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.MyBatisSqlSessionFactory;

public class JdbcBoardDaoImpl implements IJdbcBoardDao{
   private static JdbcBoardDaoImpl dao;
   
   private JdbcBoardDaoImpl() {}
   
   public static JdbcBoardDaoImpl getInstance() {
      if(dao==null) dao = new JdbcBoardDaoImpl();
      return dao;
   }

   public SqlSession session = null;

   @Override
   public int insertBoard(JdbcBoardVO boardvo) {
      try {
          session =MyBatisSqlSessionFactory.getSqlSession();
          
          int insertCnt = session.insert("board.insertBoard",boardvo);
          if(insertCnt>0) {
                  session.commit();
               } 
          return insertCnt;
      }finally {
         if(session!=null)session.close();
      }

   }

   @Override
   public int deleteBoard(int boardNo) {
      try {
         session =MyBatisSqlSessionFactory.getSqlSession();
         int cnt = session.insert("board.deleteBoard",boardNo);
         if(cnt>0) {
               session.commit();
            } 
         return cnt;
      }finally {
         if(session!=null)session.close();
      }

   }

   @Override
   public int updateBoard(JdbcBoardVO boardVo) {
      try {
         session =MyBatisSqlSessionFactory.getSqlSession();
         int cnt = session.insert("board.updateBoard",boardVo);
         if(cnt>0) {
   
               session.commit();
            }

         return cnt;
      }finally {
         if(session!=null)session.close();
         
      }

   }

   @Override

     public JdbcBoardVO getBoard(int boardNo) {
          JdbcBoardVO boardVo = null;
          try {
             session = MyBatisSqlSessionFactory.getSqlSession();
             boardVo = session.selectOne("board.getBoard",boardNo);
          } finally {
               if(session!=null) session.close();
          }
            
           return boardVo;
         }

   @Override
   public List<JdbcBoardVO> getAllBoard() {
	   
	   List<JdbcBoardVO> boardList = null;
	   try {
         session =MyBatisSqlSessionFactory.getSqlSession();
         
         boardList = session.selectList("board.getAllBoard");
         
      } finally {
         if(session!=null)session.close();
      }
	   return boardList;
   }
   
   @Override
   public List<JdbcBoardVO> getSearchBoard(String title) {
      try {
         session =MyBatisSqlSessionFactory.getSqlSession();
         
         List<JdbcBoardVO> boardList = session.selectList("board.getSearchBoard",title);
         
         return boardList;
      } finally {
         if(session!=null)session.close();
      }

   }
   @Override
   public int setCountIncrement(int boardNo) {
      try {
    	  int cnt = 0;
         session =MyBatisSqlSessionFactory.getSqlSession();
          cnt = session.update("board.setCountIncrement",boardNo);
         if(cnt>0) {
               session.commit();
            } 
         return cnt;
      }finally {
         if(session!=null)session.close();
      }

   }



}