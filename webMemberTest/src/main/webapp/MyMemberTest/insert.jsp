<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.7.0.min.js"></script>
<script>
$(function(){
	$('#send').on('click',function(){
		idvalue = $('#uid').val();
		passvalue = $('#upass').val();
		pass2value = $('#upass2').val();
		namevalue = $('#uname').val();
		telvalue = $('#utel').val();
		addrvalue = $('#uaddr').val();
		photovalue = $('#uphoto').val();
		
	
		if(passvalue==pass2value){
		fdata = {
			"id" : idvalue,
			"pass" :passvalue ,
			"pass2" : pass2value,
			"name" : namevalue,
			"tel" : telvalue,
			"addr" : addrvalue,
			"photo" : photovalue
		}
		
		
			$.ajax({
				url : '/webMemberTest/insetMem.do',
				data : fdata,
				type :'post',
				success: function(res){
 	 				alert("상태:"+res.flag); 					

				},
				error : function(xhr){
					alert("상태:"+xhr.status);
				},
				dataType :'json'
			})
		}else{
			alert("비밀번호가 다릅니다.")
			return false;
// 			$('#sppwd').html("비밀번호가 다릅니다.").css('color','red');
		}
			
	})
$('#checkid').on('click',function(){
		
		// 입력한 id를 가져온다.
		idvalue = $('#uid').val();
		
		$.ajax({
			url : '/webMemberTest/checkId.do',
			data : {"id" :idvalue},
			type : 'get',
			success:function(res){
// 				alert(res.flag);
				$('#spid').html(res.flag).css('color','red');
				
			},
			error:function(xhr){
				alert("상태 : "+ xhr.status);
			},
			dataType : 'json'
		})

	})
})
</script>
<style>
#btn{
text-align: center;
}
</style>
</head>
<form>
<table border="1">
	<tr>
		<td>회원ID</td>
		<td><input type="text" id="uid"><input type="button" value="중복검사" id="checkid">
		<span id="spid"></span>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password"id="upass"></td>
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td><input type="password"id="upass2">
		<span id="sppwd"></span>
		</td>
	</tr>
	<tr>
		<td>회원이름</td>
		<td><input type="text"id="uname"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text"id="utel"></td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td><input type="text"id="uaddr"></td>
	</tr>
	<tr>
		<td>프로필사진</td>
		<td><input type="file"id="uphoto"></td>
	</tr>
	<tr>
		
		<td colspan="2" id="btn">
		<input type="submit"value="저장" id="send" >
		<input type="reset"value="취소">
		<input type="button"value="회원목록" onclick="location.href='<%=request.getContextPath()%>/mymemList.do'">
		</td>
	</tr>
	
</table>
</form>
<body>

</body>
</html>