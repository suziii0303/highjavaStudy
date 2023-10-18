package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		DecimalFormat form = new DecimalFormat("#.##");
		
		int first = Integer.parseInt(request.getParameter("first"));
		int last = Integer.parseInt(request.getParameter("last"));
		String aa = request.getParameter("aa");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charest='utf-8'><title>계산 결과</title></head>");
		out.println("<body>");
		
		out.println("<h2>계산 결과</h2>");
		out.println("<br><hr><br>");
		double value = 0;
		switch(aa) {
		case "+":
			value = first + last;
			break;
		case "-":
			value = first - last;
			break;
		case "*":
			value = (double)first * last;
			break;
		case "/":
			value = (double)first / last;
			break;
		case "%":
			value = (double)first % last;
			break;
		}
		out.println(first);
		out.println(aa);
		out.println(last);
		
		out.printf("=%.1f ",value);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
