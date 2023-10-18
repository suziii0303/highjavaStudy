<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet 요청 연습</title>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("getBtn").addEventListener("click",function(){
		location.href ="http://localhost/webTest/servletTest03.do";
	})
}
</script>
</head>
<body>
<h2>Servlet 요청 하기</h2>
<br><hr><br>

<h3>GET방식 요청1 ==> 링크 방식</h3>
<a href="http://localhost/webTest/servletTest03.do">GET방식 요청1</a>
<hr>

<h3>GET방식 요청2 ==> form태그의 Method속성을 생략하거나 'get'으로 설정한 경우</h3>
<form action="http://localhost/webTest/servletTest03.do" method="get">
	<input type="submit"value="GET방식 요청2">
</form>
<hr>

<h3>GET방식 요청3 ==> JavaScript의 location.href속성을 이용한 경우</h3>
<form >
	<input type="button" id="getBtn" value="GET방식 요청3">
</form>
<br><hr><br>

<h3>POST방식 요청2 ==> form태그의 Method속성을 생략하거나 'post'으로 설정한 경우</h3>
<form action="http://localhost/webTest/servletTest03.do" method="post">
	<input type="submit"value="POST방식 요청2">
</form>
<hr>


</body>
</html>