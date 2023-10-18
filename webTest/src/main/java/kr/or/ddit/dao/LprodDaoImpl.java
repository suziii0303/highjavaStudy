package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisSqlSessionFactory;
import kr.or.ddit.vo.LprodVO;

public class LprodDaoImpl {
	private static LprodDaoImpl dao;
	public SqlSession session = null;
	private LprodDaoImpl() {}
	
	public static LprodDaoImpl getInstance() {
		if(dao==null)dao = new LprodDaoImpl();
		return dao;
	}
	
	public List<LprodVO> getAllLprod() {
		List<LprodVO> lprodList =null;   
		
		session =MyBatisSqlSessionFactory.getSqlSession();
		  try {
			  lprodList = session.selectList("lprod.getAllLprod");
	         
	      } finally {
	         if(session!=null)session.close();
	      }
		  return lprodList;
	 }

}
