package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Session정보를 삭제하는 서블릿
@WebServlet("/sessionDelete.do")
public class SessionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session정보 삭제
		
		// 1. Session객체를 생성하거나 현재 세션 구하기 
		HttpSession session = request.getSession();
		
		// 2. removeAttribute()메서드를 이용한 Session데이터 삭제하기
		//	형식) session객체.removeAttribute("key값");
		//	==> 세션 자체는 삭제되지 않고 개별적인 세션 데이터만 삭제된다.
		
//		session.removeAttribute("userName");
//		session.removeAttribute("age");
//		session.removeAttribute("sample");
//		session.removeAttribute("testSession");
		
		// 2-2. invalidate()메서드로 Session 삭제하기
		//	형식) session객체.invalidate();
		//	==> 세션 자체가 삭제된다.
		session.invalidate();
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'><title>세션 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2> Session 데이터 삭제하기</h2><br><br>");
		
		
		out.println("<a href='"+request.getContextPath()+
				"/basic/session/sessionTest.jsp'> 시작 문서로 이동하기</a>");
		
		out.println("</body></html>");
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
