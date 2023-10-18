package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.service.IMyMemberService;
import kr.or.ddit.service.MyMemberServiceImpl;
import kr.or.ddit.vo.MyMemberVO;

/**
 * Servlet implementation class MymemList
 */
@WebServlet("/mymemList.do")
public class MymemList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMyMemberService service = MyMemberServiceImpl.getInstance();
		// service메소드 홀출 - 결과값 받기
		List<MyMemberVO> memList = service.memberList();
		// 결과값을 request에 저장
		request.setAttribute("memList", memList);
		// view페이지로 이동 - html태그
		request.getRequestDispatcher("/MyMemberTest/myMemberList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
