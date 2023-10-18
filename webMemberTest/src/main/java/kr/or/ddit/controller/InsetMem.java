package kr.or.ddit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.service.IMyMemberService;
import kr.or.ddit.service.MyMemberServiceImpl;
import kr.or.ddit.vo.MyMemberVO;


@WebServlet("/insetMem.do")
public class InsetMem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("id");
		String userPass = request.getParameter("pass");
		String userName = request.getParameter("name");
		String userTel = request.getParameter("tel");
		String userAddr = request.getParameter("addr");
		String userPhoto = request.getParameter("photo");
		
		
		
		MyMemberVO vo = new MyMemberVO();
		
		vo.setMem_id(userId);
		vo.setMem_name(userName);
		vo.setMem_pass(userPass);
		vo.setMem_tel(userTel);
		vo.setMem_addr(userAddr);
		vo.setMem_photo(userPhoto);
		
		Part part = request.getPart("mem_photo");
		
		if(part!=null) {
//			String fileName = extra
		}
		
		System.out.println(vo);
		// service객체 얻기
		IMyMemberService service = MyMemberServiceImpl.getInstance();
		// service메소드 호출하기 - 결과값 받기
		service.insertMember(vo);
		// view페이지 이동
		response.sendRedirect(request.getContextPath()+"/mymemList.do");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
