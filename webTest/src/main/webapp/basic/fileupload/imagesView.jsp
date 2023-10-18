<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
img{
width : 300px;
}
</style>
</head>
<body>
<img src="../../images/펭귄.jpg" alt="펭귄.jpg"><br><br>

<img src="<%=request.getContextPath()%>/images/펭귄.jpg" alt="펭귄.jpg"><br><br>
<img src="<%=request.getContextPath()%>/images/imageView.do?fileno=25" alt="사자.jpg"><br><br>

</body>
</html>