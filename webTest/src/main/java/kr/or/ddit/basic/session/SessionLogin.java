package kr.or.ddit.basic.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String userId =request.getParameter("userid");
		String userPass=request.getParameter("pass");
		
		if("admin".equals(userId)&&"1234".equals(userPass)) {
			
			session.setAttribute("userId", "admin");
			session.setAttribute("userPass", "1234");
			
			response.sendRedirect(request.getContextPath()+"/basic/session/sessionLogin.jsp");
			
			
		}else{
			response.sendRedirect(request.getContextPath()+"/basic/session/sessionLogin.jsp");
		}
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
