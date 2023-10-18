package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.MyBatisSqlSessionFactory;

// DB에 데이터를 넣어주는 역할
public class MemberDaoImpl implements IMemberDao {
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() { }
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao==null)dao=new MemberDaoImpl();
		return dao;
	}
	 public SqlSession session = null;
	
	@Override
	public int insertMember(MemberVO memVo) {

		try {
			 session =MyBatisSqlSessionFactory.getSqlSession();
			 
			 int insertCnt = session.insert("member.insertMember",memVo);
			 if(insertCnt>0) {
		            session.commit();
		         } 
			 return insertCnt;
		}finally {
			if(session!=null)session.close();
		}

	}

	@Override
	public int deleteMember(String memId) {

		
		try {
			session =MyBatisSqlSessionFactory.getSqlSession();
			int cnt = session.insert("member.deleteMember",memId);
			if(cnt>0) {
	            session.commit();
	         } 
			return cnt;
		}finally {
			if(session!=null)session.close();
		}

	}

	@Override
	public int updateMember(MemberVO memVo) {
		
		try {
			session =MyBatisSqlSessionFactory.getSqlSession();
			int cnt = session.insert("member.updateMember",memVo);
			if(cnt>0) {
	
	            session.commit();
	         }

			return cnt;
		}finally {
			if(session!=null)session.close();
			
		}

	}

	@Override
	public List<MemberVO> getAllMember() {
		
		try {
			session =MyBatisSqlSessionFactory.getSqlSession();
			
			List<MemberVO> memList = session.selectList("member.getAllMember");
			
			return memList;
		} finally {
			if(session!=null)session.close();
		}

	}

	@Override
	public int getMemberCount(String memId) {
		
		try {
			session =MyBatisSqlSessionFactory.getSqlSession();
			int cnt = session.selectOne("member.getMemberCount",memId);
		
			if(cnt>0) {
	          
	            session.commit();
	         }
		return cnt;
		} finally {
			if(session!=null)session.close();
	}
	}


	@Override
	public int updateMember2(Map<String, String> paramMap) {
	
	      try {
	    	  	session =MyBatisSqlSessionFactory.getSqlSession();
	    		int cnt = session.update("member.updateMember2",paramMap);
	    		if(cnt>0) {
	  	          
		            session.commit();
		         }
	    	  return cnt;
	      }finally {
	    	  if(session!=null)session.close();
 }

}
}
