package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// userid, pass, chkid 정보 받아오기
		String userId =request.getParameter("userid");
		String userPass=request.getParameter("pass");
		String chkId =request.getParameter("chkid");
		
		// 일단 id를 저장할 쿠키 생성
		Cookie cookie = new Cookie("USERID", userId);
		
		// 체크박스의 체크 여부
		if(chkId!=null) {// 체크박스가 체크 되었을 때
			response.addCookie(cookie); 	//쿠키 추가
		}else {			// 체크박스가 체크되지 않았을 때 
			// 유지시간을 0으로 설정한 후 쿠키추가 ==> 즉 쿠키 삭제
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		// 로그인 성공 여부 검사
		if("test".equals(userId)&&"1234".equals(userPass)) { //로그인 성공
			response.sendRedirect(request.getContextPath()+
					"/basic/cookie/cookieMain.jsp");
		}else {		//로그인 실패
			response.sendRedirect(request.getContextPath()+
					"/basic/cookie/cookieLogin.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
