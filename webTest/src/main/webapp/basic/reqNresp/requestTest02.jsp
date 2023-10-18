<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="/webTest/requestTest02.do" method="get">
 <br><hr><br>
<h2>Request연습 Form(숫자 입력은 정수형으로 입력하세요)</h2>
 <br><hr><br>

<input size="10" name ="first">

 <select name="aa">
	<option value="+">+</option>
	<option value="-">-</option>
	<option value="*">*</option>
	<option value="/">/</option>
	<option value="%">%</option>
</select>

<input size="10" name ="last">
<input type ="submit"value="확인">

 </form>
</body>
</html>