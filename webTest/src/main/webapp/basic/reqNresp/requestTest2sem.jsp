<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
<h2>Request연습 Form(숫자 입력은 정수형으로 입력하세요.)</h2>
<hr>
 <form action="/webTest/requestTest02sem.do" method="get">
 <table>
<tr>
   <td><input name="num1" size="10"></td>
   <td>
      <select name="op">
         <option value="+"> + </option>
         <option value="-"> - </option>
         <option value="*"> * </option>
         <option value="/"> / </option>
         <option value="%"> % </option>
      </select>
   </td>
   <td><input name="num2" size="10"></td>
   <td><input type="submit" value="확 인"></td>
</tr>
</table>
 </form>
</body>
</html>