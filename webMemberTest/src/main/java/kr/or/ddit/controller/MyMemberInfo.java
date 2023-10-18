package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.service.IMyMemberService;
import kr.or.ddit.service.MyMemberServiceImpl;
import kr.or.ddit.vo.MyMemberVO;

/**
 * Servlet implementation class MyMemberInfo
 */
@WebServlet("/myMemberInfo.do")
public class MyMemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memId = request.getParameter("memid");
		
		IMyMemberService service = MyMemberServiceImpl.getInstance();
		// service메소드 홀출 - 결과값 받기
		MyMemberVO memInfo = service.memInfo(memId);
		// 결과값을 request에 저장
		request.setAttribute("memInfo", memInfo);
		// view페이지로 이동 - html태그
		request.getRequestDispatcher("/MyMemberTest/MemberInfo.jsp").forward(request, response);
		request.getRequestDispatcher("/MyMemberTest/updatemem.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
