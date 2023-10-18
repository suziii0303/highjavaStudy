<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<%
// 쿠키 정보를 읽어서 처리한다.

String cookieUserId =""; //쿠키값이 저장될 변수
String chk = "";		//체크박스 체크용 변수

Cookie[] cookieArr = request.getCookies();	// 전체 쿠키 정보 가져오기

for(Cookie cookie : cookieArr){
	if("USERID".equals(cookie.getName())){	//원하는 쿠키이름찾기
		cookieUserId = cookie.getValue();
		chk = "checked";
	}else{
		chk="";
	}
}


%>
<body>
	<form action="<%=request.getContextPath()%>/cookieLoginServlet.do"
		method="post">
		
		<table border ="1" style="margin: 0 auto;">
			<tr>
				<td>ID:</td>
				<td><input type="text" name="userid" value="<%=cookieUserId%>"
					placeholder="ID 입력하세요"></td>
			</tr>
			<tr>
				<td>PASS:</td>
				<td><input type="password" name="pass"
					placeholder="PassWord 입력하세요"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="checkbox" value="check"
					name="chkid"checked="<%=chk%>">id 기억하기</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;"><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
</body>
</html>