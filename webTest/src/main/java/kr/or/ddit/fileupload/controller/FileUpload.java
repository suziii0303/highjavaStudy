package kr.or.ddit.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;


/*
  	-Servlet 3.0이상에서는 파일 업로드를 하려면 @MultipartConfig 애노테이션을 설정해야 한다.
  	
  	-@MultipartConfig 애노테이션에서 설정할 변수들...
  	1) location ==> 업로드한 파일이 임시적으로 저장될 경로 지정 (기본값 :"" ==> 시스템의 임시파일 저장위치)
  	2) fileSizeThreshold ==> 이 곳에 지정한 값보다 큰 파일이 전송되면 location에 지정한 임시 디렉토리에
  				파일을 저장해 놓고 처리한다.( 단위: byte, 기본값 : 0 (무조건 임시 디렉토리를 사용한다.))
  	3) maxFileSize ==> 1개 파일의 최대 크기 (단위 : byte, 기본값 : -1(무제한))
  	4) maxREquestSize ==> 서버로 전송되는 request데이터 전체의 최대크기 (단위 : byte, 기본값 : -1(무제한))
  						  ( 모든 파일 크기 + form의 데이터 )
 */

@WebServlet("/fileUpload.do")
@MultipartConfig(		
	fileSizeThreshold = 1024*1024*10, maxFileSize=1024*1024*30,
	maxRequestSize = 1024*1024*100
)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/basic/fileupload/fileUpload.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 업로드된 파일이 저장될 폴터 설정
		String uploadPath ="d:/d_Other/web/uploadFiles";

		// 저장될 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		// 파일이 아닌 일반 데이터는 getParameter()메서드나 getParameterValues()메서드를 이용한다.
		String userName = request.getParameter("username");// 일반 데이터를 받을때 
		
		System.out.println("일반 파라미터 데이터 : "+ userName);
		
		//-------------------------------------------------------
		
		// 수신 받은 파일 데이터 처리하기 시작..
		
		String fileName =""; 	//파일명이 저장될 변수 선언
		
		// upload한 파일 목록이 저장될 List객체 생성
		List<FileInfoVO> fileList = new ArrayList<>();
		
		
		/*
		 	-Servlet 3.0이상에서 새롭게 추가된 upload관련 메서드
		 	1) request객체.getParts()	  ==>전체 Part객체를 Collection객체에 담아서 반환한다.
		 	2) request객체.getPart("이름");	==> 지정된 '이름'을 갖는 개별 Part객체를 반환한다.
		 				'이름' ==> form태그 안의 입력 요소의 name속성값으로 구별한다.		
		 */
		// 전체 Part객체 개수만큼 반복 처리
		for(Part part : request.getParts()) {
			 fileName = extractFileName(part);
			//추출한 파일명이 공백("")이면 이겅는 파일이 아닌 일반 파라미터라는 의미
			if(!"".equals(fileName)) {
				//1개의 파일 정보를 저장할 VO객체 생성
				FileInfoVO fvo = new FileInfoVO();
				
				fvo.setFile_writer(userName);		// 작성자를 VO에 셋팅
				fvo.setOrigin_file_name(fileName);	// 실제 파일명을 VO에 셋팅
				
				// 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서 UUID클래스를 이용하여 
				// 저장할 파일명을 만든다.
				String saveFilename =UUID.randomUUID().toString()+"_"+fileName;
				
				// 이 저장할 파일명을 VO에 셋팅한다.
				fvo.setSave_file_name(saveFilename);
				
				// 파일의 크기는 Part객체의 getSize()메서드로 구할 수 있다.
				// 이 메서드는 'byte'단위의 값이 반환된다.
				
				// 파일의 크기는 KB다누이로 변환해서 VO에 셋팅한다.
				fvo.setFile_size( (long)Math.ceil(part.getSize()/1024.0));
				
				try {
					// upload된 파일 저장하기 ==> Part객체의 write()메서드를 이용한다.
					part.write(uploadPath + File.separator+saveFilename);
					
					// upload된 파일 정보를 List에 추가한다.
					fileList.add(fvo);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}// if 끝
		}// for 끝
		
		// Upload가 완료된 파일 정보가 List에 저장되어 있는데 List에 있는 데이터를 DB에 insert한다.
		IFileInfoService service = FileInfoServiceImpl.getInstance(); // Service객체 생성
		
		// List에 저장된 데이터를 DB에 insert하기
		for(FileInfoVO fvo : fileList) {
			service.insertFileinfo(fvo);
		}
		
		// 저장이 완료된 후 파일 목록을 보여준다.
		response.sendRedirect(request.getContextPath()+"/fileList.do");
			
		
		
		
		
	}// doPost()메서드 끝
	//----------------------------------------
	/*
	- Part의 구조
	
	1) 파일이 아닌 일반 데이터인 경우
	----------------sdklfldsj32409sdlfkj3049sdof	==> 각각의 Part를 구분하는 선
	content-disposition: form-data; name-"username"	==> 파라미터명
						 ==> 빈줄
	hong				 ==> 파라미터 값
	
	2) 파일일 경우
	----------------sdklfldsj32409sdlfkj3049sdof  ==> 각각의 Part를 구분하는 선
	content-disposition: form-data; name="upfile1"; filename="test1.txt"  ==> 파일정보
	content-type: text/plain 		==> 파일의 종류
						 			==> 빈줄
	abcd1234안녕하세요		 			==> 파일내용
	
	
	
	 */
	
	//-----------------------------------------
	
	// Part영역 안에서 fileName을 추출하는 메서드
	private String extractFileName(Part part) {
		String fileName = "";		// 추출할 fileName이 저장될 변수 선언
		
		// Part에서 'content-disposition'의 헤더값을 구해온다.
		String headerValue = part.getHeader("content-disposition");
		
		String[] items = headerValue.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")){ //파일인지 여부 검사
				fileName = item.substring(item.indexOf("=")+2,item.length()-1);	// 파일이름 추출하기
				
			}
		}
		
		return fileName;
	}

}
