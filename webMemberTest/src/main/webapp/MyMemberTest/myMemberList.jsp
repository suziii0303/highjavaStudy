<%@page import="kr.or.ddit.vo.MyMemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#in{
text-align: right;

}
</style>
</head>
<%
List<MyMemberVO> memList = (List<MyMemberVO>)request.getAttribute("memList");
%>
<body>
<h3>회원목록 보기</h3>
<form>
<table border="1">
	<tr>
		<td colspan="5" id="in">
		<input type="button" value="회원추가" name="insert" onclick="location.href='<%=request.getContextPath()%>/MyMemberTest/insert.jsp'"> 
		</td>
	</tr>

	<tr>
		<th>ID</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>전화</th>
		<th>주소</th>
	</tr>
<%
if(memList==null||memList.size()==0){
%>
<tr><td colspan="5"> 회원이 하나도 없습니다.</td></tr>
<%
}
%>
<%
for(MyMemberVO memVO : memList){	
%>
	<tr>
		<td><a href="<%=request.getContextPath() %>/myMemberInfo.do?memid=<%= memVO.getMem_id() %>"><%= memVO.getMem_id() %></a></td>
		<td><%= memVO.getMem_pass()%></td>
		<td><%= memVO.getMem_name() %></td>
		<td><%= memVO.getMem_tel()%></td>
		<td><%= memVO.getMem_addr() %></td>
	</tr>
<% 
}
%>
</table>
</form>

</body>
</html>