package kr.or.ddit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.service.IMyMemberService;
import kr.or.ddit.service.MyMemberServiceImpl;

/**
 * Servlet implementation class deleteMember
 */
@WebServlet("/deleteMember.do")
public class deleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		// service메소드 호출하기 - 결과값 받기
		IMyMemberService service = MyMemberServiceImpl.getInstance();
		service.deleteMember(id);
		// view페이지 이동
		response.sendRedirect(request.getContextPath()+"/mymemList.do");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
