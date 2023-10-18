package kr.or.ddit.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
	private IMemberService service;		//Service객체 변수 선언
	private static Scanner scn;
	
	public MemberController() {
		service = MemberServiceImpl.getInstance();
		scn = new Scanner(System.in);
		
	}
	
	public static void main(String[] args) {
		new MemberController().memberStrart();
	}

		// 시작메서드
		public void memberStrart() {
			while (true) {
				int choice = displayMenu();

				switch (choice) {
				case 1: // 추가
					insertMember();
					break;
				case 2: // 삭제
					deleteMember();
					break;
				case 3: // 수정
					updateMember();
					break;
				case 4: // 전체 출력
					displayAll();
					break;
				case 5: // 수정 ==> 원하는 항목만 수정하기
					updateMember2();
					break;
				case 0: // 종료
					System.out.println();
					System.out.println("감사합니다.");
					return;
				default:
					System.out.println("작업 번호를 잘못 선택했습니다. 다시 선택하세요...");

				}
			}
		}
		
		// 전체 출력
		private void displayAll() {
			System.out.println();
			System.out.println("--------------------------------------");
			System.out.println(" ID    비밀번호    이름    전화번호    주소 ");
			System.out.println("--------------------------------------");
			
			// 전체 회원 목록을 가져온다.
			List<MemberVO> memList = service.getAllMember();
			
			if(memList ==null||memList.size()==0) {
				System.out.println("회원 목록이 하나도 없습니다.");
			}else {
				// List의 데티어 개수만큼 반복해서 데이터에 출력한다.
				for(MemberVO memVo : memList) {
					System.out.println(memVo.getMem_id()+"  "+memVo.getMem_pass()+"  "+memVo.getMem_name()+"  "+memVo.getMem_tel()+"  "+memVo.getMem_addr());
				}
			}
			
		}
		// 수정
		private void updateMember() {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요...");
			System.out.println("회원ID >>");
			String memId = scn.next();
			
			int count = service.getMemberCount(memId);

			if (count == 0) {// 회원이 없을 때
				System.out.println(memId + "은(는) 없는 회원 ID 입니다.");
				System.out.println("수정 작업을 종료합니다.");
				return;
			}
			
			System.out.println("새로운 비밀번호 >>");
			String newMemPass = scn.next();

			System.out.println("새로운 회원이름 >>");
			String newMemName = scn.next();

			System.out.println("새로운 전화번호 >>");
			String newMemTel = scn.next();

			scn.nextLine(); // 입력 버퍼 비우기
			System.out.println("새로운 회원 주소 >>");
			String newMemAddr = scn.nextLine();
			
			// 입력 받은 데이터를 VO객체에 저장한다.
			MemberVO memVo = new MemberVO();
			memVo.setMem_id(memId);
			memVo.setMem_pass(newMemPass);
			memVo.setMem_name(newMemName);
			memVo.setMem_tel(newMemTel);
			memVo.setMem_addr(newMemAddr);
			
			// Service에 일을 시켜서 자료를 DB에서 update 한다.
		      int cnt = service.updateMember(memVo);

		      if (cnt > 0) {
		         System.out.println("update 성공!!!");
		      } else {
		         System.out.println("update 실패!!!");
		      }

		}
		// 수정 ==> 원하는 항목 수정
		private void updateMember2() {
			 System.out.println();
		      
		      System.out.println("수정할 회원 정보를 입력하세요...");
		      System.out.print("수정할 ID입력>> ");
		      String memId = scn.next();
		      int count = service.getMemberCount(memId);
		      if(count==0) {
		         System.out.println(memId + " 는 없는 회원입니다.");
		         System.out.println("수정 작업을 종료합니다.");
		         return;
		      }
		      
		      int num;   //수정을 원하는 항목의 선택 번호가 저장될 변수
		      String updateField = null;   //수정할 컬럼명이 저장될 변수
		      String updateTitle = null;   //입력할 때 보여줄 제목
		      
		      do {
		         System.out.println();
		         System.out.println("수정할 항목을 선택하세요....");
		         System.out.println("1.비밀번호   2.회원이름   3.전화번호   4.회원주소");
		         System.out.println("-----------------------------------------");
		         System.out.print("수정할 항목 선택 >> ");
		         num = scn.nextInt();
		         
		         switch(num) {
		         case 1:
		            updateField = "mem_pass";
		            updateTitle = "비밀번호";
		            break;
		         case 2:
		            updateField = "mem_name";
		            updateTitle = "회원이름";
		            break;
		         case 3:
		            updateField = "mem_tel";
		            updateTitle = "전화번호";
		            break;
		         case 4:
		            updateField = "mem_addr";
		            updateTitle = "회원주소";
		            break;
		         default :
		            System.out.println("다시 입력하세요.");
		         }
		      }while(num<1||num>4);
		      scn.nextLine();	// 입력버퍼 비우기
		      System.out.println();
		      System.out.println("수정할"+ updateTitle + ">>");
		      String updateData = scn.nextLine();  // 수정할 데이터 이름
		      
		      // 수정할 정보를 Map에 추가한다.
		      Map<String, String> map = new HashMap<>();	//Map객체 생성
		      
		      map.put("memid", memId);			// 회원ID 셋팅
		      map.put("field", updateField);	// 수정할 컬럼명 셋팅
		      map.put("data", updateData);		// 수정할 데이터 셋팅
		      
		      int cnt = service.updateMember2(map);
		      
		      if (cnt > 0) {
			         System.out.println("수정 작업 성공!!!");
			  } else {
			         System.out.println("수정 작업 실패!!!");
			  }

		}
		
		
		// 삭제
		private void deleteMember() {
			System.out.println();
			System.out.println("삭제할 회원 정보를 입력하세요");
			System.out.println("id 입력 >>");
			String memId = scn.next();
			
			int cnt = service.deleteMember(memId);

			if (cnt > 0) {
				System.out.println(memId + "삭제성공");
			} else {
				System.out.println(memId + "회원은 없거나 삭제 실패");
			}
		}
		
			// 메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
			private int displayMenu() {
				System.out.println();
				System.out.println("----------------------");
				System.out.println("      1. 자료추가   	");
				System.out.println("      2. 자료 삭제   	");
				System.out.println("      3. 자료 수정	   	");
				System.out.println("      4. 전체 자료 출력   	");
				System.out.println("      5. 자료 수정2   	"); // 원하는 항목 1개만 추가하기
				System.out.println("      0. 작업 끝.   	");
				System.out.println("----------------------");
				System.out.print("원하는 작업 선택 >>");
				return scn.nextInt();

			}
			
			// 회원 정보를 추가하는 메서드
			private void insertMember() {
				System.out.println();
				System.out.println("추가할 회원 정보를 입력하세요.");

				String memId = null; // 회원ID가 저장될 변수
				int count = 0;
				do {
					scn.nextLine();
					System.out.print("id 입력 >>");
					memId = scn.nextLine();

					count = service.getMemberCount(memId);

					if (count > 0) {
						System.out.println("입력하신" + memId + "는(은) 이미 등록된 회원ID입니다.");
						System.out.println("다시 입력하세요");
					}

				} while (count > 0);

				System.out.println("pass 입력 >>");
				String memPass = scn.next();
				System.out.println("이름 입력 >>");
				String memName = scn.next();
				System.out.println("전화번호 입력 >>");
				String memTel = scn.next();

				scn.nextLine(); // 입력 버퍼 비우기...
				System.out.println("주소 입력 >>");
				String memAddr = scn.nextLine();
				
				// 입력 받은 자료를 VO객체에 저장한다.
				MemberVO memVo = new MemberVO();
				memVo.setMem_id(memId);
				memVo.setMem_pass(memPass);
				memVo.setMem_name(memName);
				memVo.setMem_tel(memTel);
				memVo.setMem_addr(memAddr);
				
				// Service에 일을 시켜서 자료를 DB에 insert한다.
				int cnt = service.insertMember(memVo);
				
				if(cnt >0) {
					System.out.println("insert 성공");
				}else {
					System.out.println("insert 실패");
				}
				
				
				
	}

}
