<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>File Upload 연습</h3>

<!-- 
	파일을 업로드할 때의 <form>태그의 속성 중에서 method속성은 'post'로
	enctype속성은 'multipart/form-data'f로 설정해야 한다.
 -->

<form action="<%=request.getContextPath()%>/fileUpload.do"
	method="post" enctype="multipart/form-data">

	작성자 이름:<input type="text" name="username"><br><br>
	한 개의 파일 선택 :<input type="file" name="upfile"><br><br>
	여러 개의 파일 선택 :<input type="file" name="upfile2" multiple><br><br>
	<button type ="submit">전 송</button>
</form>
<br><hr><br>

<a href="<%=request.getContextPath()%>/fileList.do">파일 목록 보기</a>

</body>
</html>