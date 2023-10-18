package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileIOTest04 {
	public static void main(String[] args) {
		// 파일 출력용 문자 기반 스트림을 이용해서 파일에 출력하기
		// ==> 사용자가 입력한 내용을 그대로 파일로 저장하는 예제
		
		
		// System.in ==> 콘솔(표준입출력장치)의 입력 장치와 연결된 바이트 기반의 입력 스트림 객체
		// System.out ==> 콘솔(표준입력장치)의 출력 장치와 연결된 바이트 기반의 출력용 스트림 객체
		try {
			/*
//			Scanner ss = new Scanner(System.in);	//콘솔하고 연결 되어 있는 System.in
			System.out.println("한 문자 입력 >>");
			int c = System.in.read();
			
			System.out.println("입력한 글자 : "+(char)c);
			*/
			
			// 바이트 기반으 스트림을 문자 기반의 스트림으로 변환해 주는 보조 스트림
			// ==> InputStreamReader(입력용), OutputStreamWriter(출력용)
			InputStreamReader isr = new InputStreamReader(System.in);
			
			FileWriter fw = new FileWriter("d:/d_Other/testchar.txt");
			
			System.out.println("아무 내용이나 입력하세요 (입력은 끝은 Ctrl + z 입니다.)");
			
			int c;
			
			// 콘솔에서 입력할 때 입력의 끝은 'Ctrl' + 'Z'키를 누르면 된다
			while((c=isr.read())!=-1) {
				fw.write(c);		// 입력바든 데이터를 파일에 출력한다.
			}
			
			isr.close();
			fw.close();
			
			
		} catch (IOException e) {
			System.out.println("입출력 오류 : "+ e.getMessage());
		}
		
	}

}
