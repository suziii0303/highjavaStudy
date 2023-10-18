package test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test02 {
	public static void main(String[] args) throws IOException {

	// 쓰고 있는 파일을 읽기
	
	// 1. 읽기 객체 생성
	//	  - FileInputStream, FileReader
	//    - BufferedInputStream, BufferedReader
	File file = new File("");
	System.out.println(file.getAbsolutePath());
	// D:\A_study\highjava\workspace\Test\src\test\Test02.java // 절대 경로
	// D:\A_study\highjava\workspace\Test
	// 상대경로
	String path ="src/test/Test02.java"; 
	
	
	FileReader fr1 = new FileReader(path);
	FileReader fr2 = new FileReader(new File(path));
	
	// 2. 읽기 
	// - 한글자씩 담기 : fr1.read()
	// - 한번에 담기 : fr1.read(char[])
	
	char[] ch = new char[100];
	
	while(true) {
		/*
		int read = fr1.read(); // 읽을게 없을때 까지 읽음
		if(read==1) {break;}
		System.out.println((char)read);
		*/
		
		/*
		int read = fr1.read();
		if(read==1) {break;}
			String string = new String(ch);
		System.out.println(string);
		*/
		
		int read = fr1.read(ch); // 읽을게 없을때 까지 읽음 read: 읽은 글자의 수, 글자는 ch 배열에 담김
		if(read==-1) {break;}
		System.out.println((char)read);
		
		// ch에 담긴 글자를 String 으로 변환 할 수 있다.
		String string = new String(ch, 0 , read); // ch배열에서  0~d에서 읽은 글자 수 만큼 String화
		System.out.println(string);
		
	}
	// 3. 종료
	
	fr1.close();
	
	}
}// 종료
