package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import rk.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService {
	private IJdbcBoardDao dao;
	private static JdbcBoardServiceImpl service;

	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}

	public static JdbcBoardServiceImpl getInstance() {
		if (service == null)
			service = new JdbcBoardServiceImpl();
		return service;
	}

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {

		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {

		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {

		return dao.updateBoard(boardVo);
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		// 매개변수에 주어진 게시글 번호에 해당하는 게시글 내용을 가져올 때 
		// 조회수가 미리 증가되어야 하기 때문에
		// 조회수를 증가시키는 작업과 게시글 내용을 가져오는 내용을
		// Service의 하나의 메서드에서 처리한다.
		
		// 조회수를 증가시키고 조회수를 증가시키는 작업이 실패했을 때...
		if (dao.setCountIncrement(boardNo) == 0) {
			return null;
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public List<JdbcBoardVO> getAllBoard() {

		return dao.getAllBoard();
	}

	@Override
	public List<JdbcBoardVO> getSearchBoard(String title) {

		return dao.getSearchBoard(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {

		return dao.setCountIncrement(boardNo);
	}

}
