package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// http://localhost:80/webTest/servletTest01.do ==> 실행할 URL주소
// - http   	==> 프로토콜
// - localhost 	==> 컴퓨터 이름(도메인명) 또는 IP주소
// - 80			==> 포트번호 (80번인 경우에는 생략이 가능하다.)
// - /webTest	==> 컨텍스트패스( 보통 '프로젝트명'으로 지정된다.)
// - /servletTest01.do ==> 서블릿 요청 URL패턴


// 이 예제는 배포서술자(web.xml)을 이용해서 실행할 Servlet을 설정하는 방식의 예제이다.

// servlet클래스는 HttpServlet을 상속해서 만든다.
public class ServletTest01 extends HttpServlet{
	// 이 곳에서는 대부분 service()메서드나 doGet()메서드 또는 doPost()메서드를
	// 재정의해서 작성한다.
	
	// doGet()메서드나 doPost()메서드는 service()메서드를 통해서 호출된다.
	// 이 메서드의 매개변수에는 HttpServletREquest객체와 HttpServletResponse객체
	// 가 자동으로 저장된다.
	
	// HttpServletRequest객체  ==> 서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
	// HttpServletResponse객체 ==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	
	
	// doGet()메서드 ==> GET방식의 요청을 처리하는 메서드이다.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로또번호를 출력해 보기
		HashSet<Integer> lottoSet = new HashSet<>();
		Random rnd = new Random();
		
		while(lottoSet.size()<6) {
			lottoSet.add(rnd.nextInt(46)+1);
		}
		
		ArrayList<Integer> lottoList = new ArrayList<>(lottoSet);
		Collections.sort(lottoList);
		
		// 처리한 결과를 응답으로 보내준다.
		response.setCharacterEncoding("utf-8"); // 응답 문서의 인코딩 방식 지정
		response.setContentType("text/html; charset=utf-8"); //응답 문서의 ContentType 지정
		
		// 처리한 내용을 응답으로 보내기 위해서 PrintWriter객체를 생성한다.
		
		PrintWriter out = response.getWriter();
		
		// 처리한 내용을 출력한다.
		// 방법1) append()메서드를 이용하여 출력한다.
		out.append("<html>")
			.append("<head>")
			.append("<meta charset='utf-8>")
			.append("<title>첫번째 Servlet 연습</title>")
			.append("</head>")
			.append("<body>")
			.append("<h2 style='text-align;'>안녕하세요 첫번째 Servlet 프로그램입니다.</h2>")
			.append("<h3>행운의 로또번호</h3>");
		for(int num : lottoList) {
			out.append("<em>"+num+"<em> ");
		}
		out.append("</body>");
		out.append("</html>");
	}
	
	
	// doPost()메서드 ==> POST방식의 요청을 정리하는 메서드이다.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
















