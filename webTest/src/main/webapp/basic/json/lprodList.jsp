<%@page import="kr.or.ddit.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	// 서블릿이 보낸 데이터를 받는다.
	List<LprodVO> lprodList = (List<LprodVO>)request.getAttribute("lprodList");
%>
<body>
<h3 style="color :blue;">Lprod 자료 목록</h3>
<table border="1">
	<tr>
		<td>Lprod_ID</td>
		<td>Lprod_GU</td>
		<td>Lprod_NM</td>
	</tr>
<%
for(LprodVO lvo : lprodList){
%>
	<tr>
		<td><%=lvo.getLprod_id() %></td>
		<td><%=lvo.getLprod_gu() %></td>
		<td><%=lvo.getLprod_nm() %></td>
	</tr>
<%
}
%>	
	
</table>
</body>
</html>