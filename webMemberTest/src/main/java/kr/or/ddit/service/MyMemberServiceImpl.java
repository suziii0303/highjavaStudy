package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.IMyMemberDao;
import kr.or.ddit.dao.MyMemberDaoImpl;
import kr.or.ddit.vo.MyMemberVO;

public class MyMemberServiceImpl implements IMyMemberService{
	private static IMyMemberDao dao;
	private static IMyMemberService service;
	
	private MyMemberServiceImpl() {
		dao = MyMemberDaoImpl.getInstance();
	}
	public static IMyMemberService getInstance() {
		if(service == null)service = new MyMemberServiceImpl();
		return service;
	}
	
	@Override
	public List<MyMemberVO> memberList() {
		// TODO Auto-generated method stub
		return dao.memberList();
	}
	@Override
	public MyMemberVO memInfo(String memId) {
		// TODO Auto-generated method stub
		return dao.memInfo(memId);
	}
	@Override
	public int insertMember(MyMemberVO inMem) {
		// TODO Auto-generated method stub
		return dao.insertMember(inMem);
	}
	@Override
	public String checkId(String checkId) {
		// TODO Auto-generated method stub
		return dao.checkId(checkId);
	}
	@Override
	public int updateMember(MyMemberVO upmem) {
		
		return dao.updateMember(upmem);
	}
	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return dao.deleteMember(id);
	}

	

}
