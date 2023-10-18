<%@page import="kr.or.ddit.vo.MyMemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#btn{
text-align: center;
}
</style>
</head>
<%
MyMemberVO vo =(MyMemberVO)request.getAttribute("memInfo");
%>
<body>
<form>
<table border="1">
	<tr>
		<td colspan="2"></td>		
	</tr>
	<tr>
		<td>회워Id</td>		
		<td><%=vo.getMem_id() %></td>		
	</tr>
	<tr>
		<td>비밀번호</td>		
		<td><%=vo.getMem_pass() %></td>		
	</tr>
	<tr>
		<td>회원이름</td>		
		<td><%=vo.getMem_name() %></td>		
	</tr>
	<tr>
		<td>전화번호</td>		
		<td><%=vo.getMem_tel() %></td>		
	</tr>
	<tr>
		<td>회원주소</td>		
		<td><%=vo.getMem_addr() %></td>		
	</tr>
	<tr>
		<td id= "btn"colspan="2">
		
		<input type="button" value="수정"onclick="location.href='<%=request.getContextPath()%>/MyMemberTest/updatemem.jsp?memid=<%=vo.getMem_id() %>'">
		<input type="button" value="삭제"onclick="location.href='<%=request.getContextPath()%>/deleteMember.do?id=<%=vo.getMem_id()%>'">
		<input type="button" value="회원목록"onclick="location.href='<%=request.getContextPath()%>/mymemList.do'">
		</td>		
		
</table>
</form>

</body>
</html>