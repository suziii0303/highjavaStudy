<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div{
padding-top : 20px;
text-align : center;
border : 1px solid red;
width : 300px;
height : 150px;
margin : 0 auto;
}
</style>
</head>

<body>
<%
// JSP문서에서 세션객체는 'session'이라는 이름으로 저장되어 있다.

// 로그인에 성공 했을 때 저장한 세션 데이터 가져오기
MemberVO memVo = (MemberVO)session.getAttribute("loginMember");
if(memVo != null){
%>
	<div>	
	<h3><%=memVo.getMem_name()%>님 반갑습니다.</h3><br>

	<a href="<%= request.getContextPath()%>/sessionLogoutDB.do">로그아웃</a>
	</div>
<% 
}else{
%>
	<form action="<%=request.getContextPath()%>/sessionLoginDB.do"
		method="post">
		<table border ="1" style="margin: 0 auto;">
			<tr>
				<td>ID:</td>
				<td><input type="text" name="userid"
					placeholder="ID 입력하세요"></td>
			</tr>
			<tr>
				<td>PASS:</td>
				<td><input type="password" name="pass"
					placeholder="PassWord 입력하세요"></td>
			</tr>

			<tr>
				<td colspan="2" style="text-align:center;">
				<input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
<%	
}
%>

</body>
</html>