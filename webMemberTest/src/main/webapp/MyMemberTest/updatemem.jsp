<%@page import="kr.or.ddit.vo.MyMemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.7.0.min.js"></script>
<%
String id =(String)request.getParameter("memid");
MyMemberVO vo = (MyMemberVO)request.getAttribute("vo");
%>

<style>
#btn{
text-align: center;
}
</style>
</head>
<body>
<form action="<%=request.getContextPath()%>/updateMember.do" method="post" id="update">
	
<table border="1">
	<tr>
		<td colspan="2"></td>		
	</tr>
	<tr>
		<td>회워Id</td>		
		<td>
		<input type="text" readonly="readonly" name="id" value="<%=id%>">
		</td>
		
	</tr>
	<tr>
		<td>비밀번호</td>		
		<td><input type="password" id="pass" name="pass"></td>		
	</tr>
	<tr>
		<td>회원이름</td>		
		<td><input type="text" id="name"name="name"></td>		
	</tr>
	<tr>
		<td>전화번호</td>		
		<td><input type="text" id="tel"name="tel"></td>		
	</tr>
	<tr>
		<td>회원주소</td>		
		<td><input type="text" id="addr"name="addr"></td>		
	</tr>
	<tr>
		<td>프로필사진</td>		
		<td><input type="file" id="photo"name="photo"></td>		
	</tr>
	
	<tr>
		<td id= "btn"colspan="2">
		
		<input type="submit" value="저장" >
		<input type="button" value="삭제" onclick="location.href='<%=request.getContextPath()%>/mymemList.do'">
		<input type="button" value="회원목록"onclick="location.href='<%=request.getContextPath()%>/mymemList.do'">
		</td>		
		
</table>
</form>
</body>
