package test02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test05 {
	public static void main(String[] args) {
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

		try (
			BufferedReader br = new BufferedReader(new FileReader(path));
			BufferedReader br1 = new BufferedReader(new FileReader(path));
		) {
			while(true) {
				String readLine = null;
				 readLine = br.readLine();
				if(readLine==null) {break;}
				System.out.println(readLine);
			}
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 3. 종료
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}// 종료


