package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.vo.MyMemberVO;

public interface IMyMemberDao {

	public List<MyMemberVO> memberList();
	
	public MyMemberVO memInfo(String memId);

	public int insertMember(MyMemberVO inMem);
	
	public String checkId(String checkId);
	
	public int updateMember(MyMemberVO upmem);
	
	public int deleteMember(String id);
}
