<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
<script>
$(function(){
	// ajax를 이용
	$('#lprodBtn').on('click',function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/lprodListServlet.do",
			type :"post",
			success:function(lprodList){
				let code = "<table border='1'class='container'>";
				code += "<tr><td>LPOD_ID</td><td>LPROD_GU</td><td>LPROD_NM</td></tr>";
				$.each(lprodList,function(i,v){
					code += "<tr><td>"+v.lprod_id+"</td> <td>"+v.lprod_gu+"</td> <td>"+v.lprod_nm+"</td></tr>";
					
				})
				code+="</table>";
				
				$("#result").html(code);
			},
			error: function(xhr){
				alert("오류");
			},
			dataType:'json'
		})
	})
	//--------------------------------------------------------
	// ajax를 사용하지 않기
	$('#lprodBtn2').on('click',function(){
		// 서블릿으로 요청을 하면 서블릿에서 DB의 자료를 가져오고
		// 가져온 자료를 view용 JSP문서로 forward방식으로 보낸다.
		// view용 JSP문서에서는 서블릿이 보낸 데이터를 받아서 화면에 출력한다.
		
		location.href = "<%=request.getContextPath()%>/lprodListServlet2.do"
	})

})
</script>
<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,700);

body {
  font-family: 'Open Sans', sans-serif;
  font-weight: 300;
  line-height: 1.42em;
  color:#A7A1AE;
  background-color:black;
}


.blue { color: #185875; }
.yellow { color: #FFF842; }

.container th h1 {
    font-weight: bold;
    font-size: 1em;
  text-align: left;
  color: #185875;
}

.container td {
    font-weight: normal;
    font-size: 1em;
  -webkit-box-shadow: 0 2px 2px -2px #0E1119;
     -moz-box-shadow: 0 2px 2px -2px #0E1119;
          box-shadow: 0 2px 2px -2px #0E1119;
}

.container {
    text-align: left;
    overflow: hidden;
    width: 80%;
    margin: 0 auto;
  display: table;
  padding: 0 0 8em 0;
}

.container td, .container th {
    padding-bottom: 2%;
    padding-top: 2%;
  padding-left:2%;  
}

/* Background-color of the odd rows */
.container tr:nth-child(odd) {
    background-color: #323C50;
}

/* Background-color of the even rows */
.container tr:nth-child(even) {
    background-color: #2C3446;
}

.container th {
    background-color: #1F2739;
}

.container td:first-child { color: #FB667A; }

.container tr:hover {
   background-color: #464A52;  
-webkit-box-shadow: 0 6px 6px -6px #0E1119;
     -moz-box-shadow: 0 6px 6px -6px #0E1119;
          box-shadow: 0 6px 6px -6px #0E1119;
}

.container td:hover {
  background-color: hotpink;
  color: #403E10;
  font-weight: bold;
  
  box-shadow: pink -1px 1px, pink -2px 2px, pink -3px 3px, pink -4px 4px, pink -5px 5px, pink -6px 6px;
  transform: translate3d(6px, -6px, 0);
  
  transition-delay: 0s;
    transition-duration: 0.4s;
    transition-property: all;
  transition-timing-function: line;
}

@media (max-width: 800px) {
.container td:nth-child(4),
.container th:nth-child(4) { display: none; }
}
</style>
</head>
<body>
<form >
	<input type="button" id="lprodBtn" value="lprod자료 가져오기(Ajax이용)">
	<input type="button" id="lprodBtn2" value="Lpord자료 가져오기(Ajax사용 안함)">
</form>
<h3>Lprod 자료 목록</h3>
<div id="result"></div>
</body>
</html>