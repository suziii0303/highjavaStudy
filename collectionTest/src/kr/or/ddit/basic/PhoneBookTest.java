package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PhoneBookTest {
   private Scanner scan;
   private  Map<String, Phone> phoneBookMap;
   
   // 생성자
   public PhoneBookTest() {
      scan = new Scanner(System.in);
      phoneBookMap = new HashMap<>();
   }
   
  // 메뉴를 출력하고 작업번호를 입력밭아 반환하는 메서드
   public void displayMenu(){
      System.out.println();
      System.out.println("=======메 뉴=====");
      System.out.println(" 1. 전화번호 등록");
      System.out.println(" 2. 전화번호 수정");
      System.out.println(" 3. 전화번호 삭제");
      System.out.println(" 4. 전화번호 검색");
      System.out.println(" 5. 전화번호 전체 출력");
      System.out.println(" 0. 프로그램 종료");
      System.out.println("================");
      System.out.print(" 번호입력 >> ");      
   }
   
   // 프로그램 시작하는 메소드
   public void phoneBookStart(){
      System.out.println("===============================================");
      System.out.println("   전화번호 관리 프로그램");
      System.out.println("===============================================");
      
      while(true){
         
         displayMenu();  
         
         int menuNum = scan.nextInt();   // 메뉴 번호 입력
         
         switch(menuNum){
            case 1 : insert();      // 등록
               break;
            case 2 : update();      // 수정
               break;
            case 3 : delete();      // 삭제
               break;
            case 4 : search();      // 검색
               break;
            case 5 : displayAll();   // 전체 출력
               break;
            case 0 :
               System.out.println("프로그램을 종료합니다...");
               return;
            default :
               System.out.println("잘못 입력했습니다. 다시입력하세요.");
         } 
      } 
   }
  // 전화번호 정보를 검색하는 메서드
   private void search() {
      System.out.println();
      System.out.println("검색할 전화번호 정보를 입력하세요");
      System.out.print("이 름 >> ");
      String name = scan.next();
      
      Phone p = phoneBookMap.get(name);
//      Phone phone = phoneBookMap.containsKey(name);
      
      if(p == null) {
         System.out.println(name + " 씨의 전화번호 정보가 없습니다.");
      } else {
         System.out.println(name + " 씨의 전화번호 정보");
         System.out.println("이      름 : " + p.getName());
         System.out.println("전화번호 : " + p.getTel());
         System.out.println("주      소 : " + p.getAddr());
      }
      System.out.println("검색 작업 완료....");
   }

   
   private void delete() {
      System.out.println();
      System.out.println("삭제할 전화번호 정보를 입력하세요");
      System.out.print("이 름 >> ");
      String name = scan.next();
      
//      if(!phoneBookMap.containsKey(name)) {
//    	  System.out.println(name+"씨 전화번호 정보가 없습니다.");
//      return;
//      }
      
      if(phoneBookMap.remove(name) == null) {
         System.out.println(name + " 씨는 등록된 사람이 아닙니다.");
      } else {
         System.out.println(name + " 씨의 정보를 삭제했습니다.");
      }
      System.out.println("삭제 작업 완료...");
   }

   // 전화번호 정보를 수정하는메서드
   private void update() {
      System.out.println();
      System.out.println("새롭게 수정할 전화번호 정보를 입력하세요");
      System.out.print("이 름 >> ");
      String name = scan.next();
      
      //containsKey(name)
      if(phoneBookMap.get(name) == null) {
         System.out.println(name + " 씨는 전화번호 정보가 없습니다.");
         return; 
      }
      
      System.out.print("새로운 전화번호 >> ");
      String tel = scan.next();
      
      System.out.print("새로운 주소 >> ");
      scan.nextLine();   
                 
                     
      String addr = scan.nextLine();
      
      // 수정 작업 ==> 같은 key값에 새로운 전화번호 정보를 저장한다.
//      Phone p = new Phone(name, tel, addr);
//      phoneBookMap.put(name, p);
      phoneBookMap.put(name, new Phone(name, tel, addr));
      
      System.out.println(name + " 씨 수정 완료...");
      
      
      
   }

  // 전체 전화번호 정보를 출력하는 메서드
   private void displayAll() {
      Set<String> keySet = phoneBookMap.keySet();
      
      System.out.println("=====================================");
      System.out.println(" 번호\t이름\t전화번호\t\t주소 ");
      System.out.println("=====================================");
      
      if(keySet.size() == 0) {
         System.out.println("등록된 전화번호 정보가 없습니다.");
      } else {
         Iterator<String> it = keySet.iterator();
         
         int cnt = 0;//번호 출력용 변수 선언
         while(it.hasNext()) {
            cnt++;
            String name = it.next();//키 값(이름)가져오기
            Phone p = phoneBookMap.get(name);
            System.out.println(" " + cnt + "\t" + p.getName() + "\t" + p.getTel() + "\t\t" + p.getAddr());
         }
      }
      System.out.println("=====================================");
      System.out.println("출력 완료...");
   }
   /*
    -Scanner객체의 next(), nextDouble() ... 등 nextLine()이 아닌 메서드
    	==> 사이띄기, tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다.
    	
    -Scanner객체의 nextLIne()메서드
    	==> 한 줄 단위로 입력한다. 즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어가서 
      		Enter키를 뺀 나머지 데이터를 반환한다.
      		
    - 그래서 nextLine()메서드를 사용하기 전에 nextLine()이외의 메서드로 입력한 이력이 있는 경우에는 
    	nextLine()메서드를 한번 호출해서 입력 버퍼를 비워주어야 한다.
    */
   
   // 새로운 전화번호 정보를 등록하는 메서드
   private void insert() {
      System.out.println();
      System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
      System.out.print("이 름 >> ");
      String name = scan.next();
      
      //이미 등록된 사람인지 검사 .get
      if(phoneBookMap.containsKey(name)) {//이미 있으면..
         System.out.println(name + " 씨는 이미 등록된 사람입니다.");
         return; 
      }
      
      System.out.print("전화번호 >> ");
      String tel = scan.next();
      
      System.out.print("주소 >> ");
      scan.nextLine();  //버퍼 비우기 
      String addr = scan.nextLine();
      
//      Phone p =new Phone(name, tel, addr);
//      phoneBookMap.put(name, p);
      
      phoneBookMap.put(name, new Phone(name, tel, addr));
      System.out.println(name + "씨 새 등록 완료...");
   }

   	//프로그램을 시작하는 메서드
   public static void main(String[] args) {
      new PhoneBookTest().phoneBookStart();
   }
      

}


// 이름, 주소 전화번호를 멤버로 갖는 phone클래스 
class Phone{
   private String name;   
   private String tel;      
   private String addr;   
 
   public Phone(String name, String tel, String addr) {
      super();
      this.name = name;
      this.tel = tel;
      this.addr = addr;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getTel() {
      return tel;
   }
   public void setTel(String tel) {
      this.tel = tel;
   }
   public String getAddr() {
      return addr;
   }
   public void setAddr(String addr) {
      this.addr = addr;
   }
   @Override
   public String toString() {
      return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
   }
   
   
}
	