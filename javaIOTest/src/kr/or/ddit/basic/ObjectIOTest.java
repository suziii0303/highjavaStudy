package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {
	public static void main(String[] args) {
		// 객체를 저장하는 예제
		
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("홍길서", 30, "서울");
		Member mem3 = new Member("홍길남", 40, "부산");
		Member mem4 = new Member("홍길북", 50, "강릉");
		
		try {
			// 객체를 파일로 저장하기 위한 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_Other/memObj.bin");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			// 쓰기 작업
			System.out.println("객체 저장하기 시작...");
			oout.writeObject(mem1);
			oout.writeObject(mem2);
			oout.writeObject(mem3);
			oout.writeObject(mem4);
			oout.writeObject(null);		// 객체를 저장할 때 맨 마지막에 null값을 추가하여 저장하면
										// 읽어올 때 EOFException을 방지할 수 있다.
			System.out.println("객체 저장 작업 완료...");
			
			// 스트림 닫기
			oout.close();
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//---------------------------------------------------
		
		// 저장된 객체를 읽어와 그 내용을 화면에 출력하기
		try {
			ObjectInputStream oin = new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream("d:/d_Other/memObj.bin")
									)
							);
			
			Object obj;		// 읽어온 객체가 저장될 변수
			System.out.println("객체 읽기 작업 시작...");
			
			// readObject()메서드가 데이터를 끝까지 다 읽어오면 EOFException이 발생한다.
			while((obj=oin.readObject())!=null) {
				// 읽어온 객체를 원래의 객체형으로 형변환 후 사용한다.
				Member mem = (Member)obj;
				System.out.println("이름 : "+ mem.getName());
				System.out.println("나이 : "+ mem.getAge());
				System.out.println("주소 : "+ mem.getAddr());
				System.out.println("-----------------------");
				
			}
			System.out.println("읽기 작업 완료...");
			
			oin.close();		// 스트림 닫기
			
//		}catch(EOFException e) {
//			System.out.println("작업 끝...");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		
	}

	}
}


class Member implements Serializable{
	private String name;
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}