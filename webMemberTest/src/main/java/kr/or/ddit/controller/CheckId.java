package kr.or.ddit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.service.IMyMemberService;
import kr.or.ddit.service.MyMemberServiceImpl;


@WebServlet("/checkId.do")
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputId = request.getParameter("id");
		
		IMyMemberService service = MyMemberServiceImpl.getInstance();
		
		String id = service.checkId(inputId);
		if(id!= null) {
			request.setAttribute("check","이미존재" );			
		}else {
			request.setAttribute("check","사용가능" );			
			
		}


		
		request.getRequestDispatcher("/MyMemberTest/checkId.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
