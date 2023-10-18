package kr.or.ddit.basic.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionLogout
 */
@WebServlet("/sessionLogoutDB.do")
public class SessionLogoutDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션을 삭제한 후 로그인 페이지로 이동
		HttpSession session =request.getSession();
		
		session.invalidate();// 전체 세션 삭제
		
		response.sendRedirect(request.getContextPath()+
						"/basic/session/sessionLoginDB.jsp");
	}

}
