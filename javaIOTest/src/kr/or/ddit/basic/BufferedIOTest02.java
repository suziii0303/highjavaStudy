package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {
	public static void main(String[] args) {
		// 문자 기반의 Buffered스트림 사용 예제 ==> 입력용
		
		try {
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			// 버퍼 스트림의 크기를 지정하지 않으면 기본적으로 8KB(8196bytes)크기로 설정된다.
			BufferedReader br = new BufferedReader(fr);
			
			String temp ="";	// 읽어온 문자열이 저장될 변수
			
			// 문자 기반의 입력용 Buffered스트림에는 한 줄 단위로 읽어오는 메서드가 있다.
			//		==> readLine()메서드
			for(int i=1; (temp=br.readLine())!=null;i++) {
				System.out.printf("%4d : %s\n",i,temp);
			}
			
			br.close();	// 스트림 닫기
			
		} catch (IOException e) {
			System.out.println("입출력 오류 : "+e.getMessage());
		}
	}
}
