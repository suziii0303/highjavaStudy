<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div{
	border : 1px solid black;
	width: 200px;
	height: 200px;
	text-align: center;
}
</style>
</head>
<body>
<div>
<a href="<%= request.getContextPath()%>/cookieCountServlet.do">Cookie Count 증가하기</a><br><br>
<a href="<%= request.getContextPath()%>/cookieCountDelServlet.do">Cookie Count 초기화 하기</a><br><br>
</div>
</body>
</html>