package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

/*
 	추가 조건
1) '6. 전화번호 저장'메뉴를 추가하고 구현한다.
	(저장파일명은 'phoneData.dat'로 한다.)
	
2) 이 프로그램이 시작될 때 저장된 파일이 이씅면 그 파일의 데이터를 읽어와 Map에 셋팅한다.

3) 이 프로그램이 종료될 때 Map의 데이터가 변경(추가,수정,삭제)되었으면 저장 후 종료 되도록한다.
 */

public class PhoneBookTest {

	Scanner sc = new Scanner(System.in);
	

	private HashMap<String, Phone> map;
	
	private String fileName = "d:/d_other/phoneData.dat";
	private String name;
	private String addr;
	private String tel;
	// 데이터가 변경되었는지 여부를 나타내느 변수 => 데이터가 변경되면 이 변수값이 true가 된다.
	private boolean dataChange;
	
	// 생성자
	public PhoneBookTest() {
		
		map = load(); // 파일 내용을 읽어오 Map객체에 저장한다.
		
		if(map==null){	// 파일이 없거나 잘못되엇을 때
			map = new HashMap<>();
		}
	}
	
	public static void main(String[] args) {
		PhoneBookTest b= new PhoneBookTest();
		b.start();
	}
	
	public void start(){

		while(true){
			System.out.println(" -----------");
			System.out.println("메뉴를 선택하세요 ");
			System.out.println(" -----------");
			System.out.println("1. 전화번호 등록");
			System.out.println("2. 전화번호 수정");
			System.out.println("3. 전화번호 삭제");
			System.out.println("4. 전화번호 검색");
			System.out.println("5. 전화번호 전체출력");
			System.out.println("6. 전화번호 저장");
			System.out.println("0. 프로그램 종료");
			int input = sc.nextInt();
			sc.nextLine(); 
			switch(input){
			case 1: add(); break;
			case 2: edit(); break;
			case 3: delete(); break;
			case 4: search(); break;
			case 5: print(); break;
			case 6: save(); break;
			case 0: 
				if(dataChange==true){	// 데이터의 변경 여부를 검사한다.
					System.out.println("변경된 데이터를 저장합니다.");
					dataChange = true;
					save();
				}
				return;
			}
		}

	}
	
	// 전화번호 정보를 파일로 저장하는 메서드 
	private void save() {
		ObjectOutputStream oos = null; //객체를 저장
       
		try {
			// 객체 출력용 스트림 객체 생성
			oos = new ObjectOutputStream(
						new BufferedOutputStream(
							new FileOutputStream(fileName)));
			// 데이터 저장하기 ==> Map객체 자체를 저장한다.
			oos.writeObject(map);
			System.out.println("저장이 완료되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 사용했던 스트림 객체 닫기
			if(oos!=null)
			try {
				oos.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
		dataChange=false;
		
	}
	// 파일로 저장된 전화번호 정보를 읽어와서 반환하는 메서드 => 읽어올 파일이 없으면 null반환
	private HashMap<String,Phone> load(){

		HashMap<String, Phone> pMap = null; // 읽어온 객체를 저장할 변수
		
		File file = new File(fileName);
		if(!file.exists()){	// 저장된 파일이 없으면...
			return null;
		}
	
		ObjectInputStream ois = null;
		try {
			// 입력용 스트림 객체 생성
			ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(file)));
			// 파일에 저장된 객체 읽어오기
			pMap =(HashMap<String, Phone>)ois.readObject()  ;
			
		} catch (IOException e) {
			// TODO: handle exception
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} finally{
			if(ois!=null)
				try {
					// 사용했던 스트림 닫기
					ois.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
		}
		return pMap;
	}


	//전화번호 등록
	public void add(){

		System.out.println("새롭게 등록할 전화번호를 입력하세요.");

		System.out.println("이름>");
		name =sc.nextLine();
		if(map.containsKey(name)==true){
			System.out.println("중복되는 이름이 있습니다.");
			add();
		}

		System.out.println("전화번호>");
		tel=sc.nextLine();

		System.out.println("주소>");
		addr=sc.nextLine();

		map.put(name, new Phone(name, addr, tel));
		System.out.println("등록이 완료되었습니다. "+ map.get(name));
		System.out.println();
		dataChange = true;	// 데이터가 변경되었음을 나타낸다
	}


	private void edit() {


		System.out.println("수정할 사람의 이름을 입력:");
		name = sc.nextLine();
		
		if(map.containsKey(name)==false){
			System.out.println("이름 없음");
			edit();
		}
		
		System.out.println("변경할 주소:");
		addr = sc.nextLine();
		System.out.println("변경할 번호:");
		tel = sc.nextLine();
		
		map.put(name, new Phone(name, addr, tel));
		dataChange=true;	// 데이터가 변경되었음을 나타낸다
		System.out.println("변경이 완료되었습니다. "+ map.get(name));

	}


	private void delete() {

		System.out.println("삭제할 사람 전화번호의 이름을 입력:");
		name = sc.nextLine();
		
		if(map.containsKey(name)!=true){
			System.out.println("검색되지않습니다.");
			return;
		}
		
		map.remove(name);

		System.out.println("삭제가 완료되었습니다. ");

		for(String key : map.keySet()){
			Phone value = map.get(key);
			System.out.println(key + ":"+value);
		}
		
		dataChange = true;
		
	}

	private void search() {



		System.out.println("검색할 사람의 이름을 입력:");
		name= sc.nextLine();
		if(map.containsKey(name)!=true){
			System.out.println("검색되지않습니다.");
			return;
		}
	
		System.out.println("검색결과 "+ map.get(name));

	}

	private void print() {


		if(map.size()==0){
			System.out.println("등록된 정보가 없습니다.");
		} 

		for(String key : map.keySet()){
			Phone value = map.get(key);
			System.out.println(key + ":"+value);
		}

	}
}
// 이름, 주소, 전화번호를 멤버로 갖는 클레스
class Phone implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String addr;
	private String tel;


	public Phone(String name, String addr, String tel) {
		super();
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "[이름=" + name + ", 주소=" + addr + ", tel=" + tel + "]";
	}


}