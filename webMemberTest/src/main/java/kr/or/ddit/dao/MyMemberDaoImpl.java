package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisSqlSessionFactory;
import kr.or.ddit.vo.MyMemberVO;

public class MyMemberDaoImpl implements IMyMemberDao{
	private static IMyMemberDao dao;
	
	private MyMemberDaoImpl() {}
	
	public static IMyMemberDao getInstance() {
		if(dao==null) dao = new MyMemberDaoImpl();
		return dao;
	}
	@Override
	public List<MyMemberVO> memberList() {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		List<MyMemberVO> list =null;
		
		try {
			list = sqlSession.selectList("mymember.memberList");
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return list;
	}

	@Override
	public MyMemberVO memInfo(String memId) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		MyMemberVO vo =null;
		
		try {
			vo = sqlSession.selectOne("mymember.memInfo", memId);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		
		return vo;
	}

	@Override
	public int insertMember(MyMemberVO inMem) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		int member = 0;
		
		try {
			member = sqlSession.insert("mymember.insertMember",inMem);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return member;
	}

	@Override
	public String checkId(String checkId) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		String checkIkd = null;
		
		try {
			checkIkd = sqlSession.selectOne("mymember.checkId",checkId);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return checkIkd;
	}

	@Override
	public int updateMember(MyMemberVO upmem) {
		SqlSession sqlSession = MyBatisSqlSessionFactory.getSqlSession();
		
		int update = 0;
		
		try {
			update = sqlSession.update("mymember.updateMember",upmem);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return update;
	}

	@Override
	public int deleteMember(String id) {
		SqlSession session = MyBatisSqlSessionFactory.getSqlSession();
		int delet = 0;
		try {
			delet = session.delete("mymember.deleteMember", id);
			
		} finally {
			session.commit();
			session.close();
		}
		return delet;
	
	}

}
