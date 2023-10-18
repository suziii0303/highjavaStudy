package test02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test03 {
	public static void main(String[] args)  {

	// 쓰고 있는 파일을 읽기
	
	// 1. 읽기 객체 생성
	//	  - FileInputStream, FileReader
	//    - BufferedInputStream, BufferedReader
	File file = new File("");
	System.out.println(file.getAbsolutePath());
	// D:\A_study\highjava\workspace\Test\src\test\Test02.java // 절대 경로
	// D:\A_study\highjava\workspace\Test
	// 상대경로
	
	String path ="src/test02/Test02.java"; 
	
	
	// 2. 읽기
	BufferedReader br = null;
	try {
		br = new BufferedReader(new FileReader(path));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	while(true) {
		String readLine = null;
		try {
			readLine = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(readLine==null) {break;}
		System.out.println(readLine);
	}
	// 3. 종료
	try {
		br.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
	}
	
	}
}// 종료
