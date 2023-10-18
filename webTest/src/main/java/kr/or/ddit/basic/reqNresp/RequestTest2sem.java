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
@WebServlet("/requestTest02sem.do")
public class RequestTest2sem extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		DecimalFormat form = new DecimalFormat("#.##");
		
		String strNum1 = request.getParameter("num1");
		String strNum2 = request.getParameter("num2");
		String op = request.getParameter("op");
		
		int num1 =Integer.parseInt(strNum1);
		int num2 =Integer.parseInt(strNum2);
		
		
		double result = 0;
		
		boolean calcOk = true; // 계산 성공 여부가 저장될 변수
		
		switch(op) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = (double)num1 * num2;
			break;
		case "/":
			if(num2==0) {
				calcOk =false;
			}else {
			result = (double)num1 / num2;
			break;
			}
		case "%":
			if(num2==0) {
				calcOk = false;
			}else {
			result = (double)num1 % num2;
			break;
			}
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charest='utf-8'><title>계산 결과</title></head>");
		out.println("<body>");
		
		out.println("<h2>계산 결과</h2>");
		out.println("<br><hr><br>");
		
		out.println(num1 + " "+op+" "+num2+" = " );
		if(calcOk) {
			out.println(result);
		}else {
		out.println("계산 불가능(0으로 나누기)");
		}		
		out.print("</body>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
