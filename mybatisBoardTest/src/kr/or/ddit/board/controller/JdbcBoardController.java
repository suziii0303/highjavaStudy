package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IJdbcBoardService;
import kr.or.ddit.board.service.JdbcBoardServiceImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardController {
	private IJdbcBoardService service;
	private Scanner scan;

	public JdbcBoardController() {
		scan = new Scanner(System.in);
		service = JdbcBoardServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}

	// 시작 메서드
	private void boardStart() {
		String searchTitle = null;		// 검색할 단어가 저장될 변수
		int choice = -1;
		while (true) {
			if(choice !=3) {	// 선택 매뉴가 '검색'(3)번이 아니면 그 전에 입력했던 검색단어를 없앤다. 
				searchTitle = null;
			}
			
			choice = displayMenu(searchTitle);
			
			switch (choice) {
			case 1:			// 새글 작성
				insertBoard();
				break;
			case 2: 		// 게시글 보기
				viewBoard();
				break;
			case 3:		//검색
				searchTitle = searchBoard();		
				break;
			case 0:
				System.out.println();
				System.out.println("게시판 프로그램을 종료 합니다...");
				return;
			default:
				System.out.println("번호를 잘못 입력 했습니다. 다시입력하세요.");
			}
		}
	}
	// 검색핳 단어를 입력받아 반환하는 메서드
	private String searchBoard() {
		scan.nextLine();	// 입력 버퍼 비우기
		System.out.println();
		System.out.println("검색작업");
		System.out.println("-------------------------------");
		System.out.println(" - 검색할 제목 입력 >> ");
		return scan.nextLine();
	}
	
	// 게시글 보기
	private void viewBoard() {
		System.out.println();
		System.out.println("보기를 원하는 게시물 번호 입력 >>");
		int boardNo = scan.nextInt();
		
		JdbcBoardVO boardVo = service.getBoard(boardNo);
		
		if(boardVo == null) {
			System.out.println(boardNo+"번의 게시글이 존자해지 않습니다...");
			return;
			
		}
		System.out.println();
		System.out.println(boardNo+"번 게시글 내용");
		System.out.println("------------------------------------");
		System.out.println(" - 제 목 : "+boardVo.getBoard_title());
		System.out.println(" - 작성자 : "+boardVo.getBoard_writer());
		System.out.println(" - 내용 : "+boardVo.getBoard_content());
		System.out.println(" - 작성일 : "+boardVo.getBoard_date());
		System.out.println(" - 조회수 : "+boardVo.getBoard_cnt());
		System.out.println("------------------------------------");
		System.out.println(" 메뉴 : 1.수정  2.삭제  3.리스트로 가기");
		System.out.println("작업선택 >>");
		int choice = scan.nextInt();
		
		switch(choice) {
		case 1 :	//수정
			updateBoard(boardNo);
			break;
		case 2 :	//삭제
			deleteBoard(boardNo);
			break;
		case 3 :	//리스트로 돌아가기
			return;
		}
	}
	// 게시글을 삭제하는 메서드
	private void deleteBoard(int boardNo) {
		int cnt =service.deleteBoard(boardNo);
		
		if(cnt >0) {
			System.out.println(boardNo + "번글이 삭제 되었습니다.");
		}else {
			System.out.println(boardNo + "번글이 삭제 실패 하였습니다.");
			
		}
	}
	// 게시글을 수정하는 메소드
	private void updateBoard(int boardNo) {
		System.out.println();
		scan.nextLine();
		
		System.out.println("수정 작업하기");
		System.out.println("--------------------------------");
		System.out.println(" - 제 목 >> ");
		String title = scan.nextLine();
		System.out.println(" - 내용 >> ");
		String content = scan.nextLine();
		
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt>0) {
			System.out.println(boardNo +"번 글이 수정 되었습니다.");
		}else {
			System.out.println(boardNo +"번 글이 수정 실패하였습니다.");
			
		}

	}
	
	// 새글을 작성하는 메서드
	private void insertBoard() {
		System.out.println();
		scan.nextLine();
		
		System.out.println(" 새 글 작성하기");
		System.out.println("-------------------------");
		System.out.println(" - 제목 >>");
		String title = scan.nextLine();
		
		System.out.println(" - 작성자 >>");
		String writer = scan.nextLine();
		
		System.out.println(" - 내용 >>");
		String content = scan.nextLine();
		
		// 입력 받은 자료를 VO객체에 저장하기
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt>0 ) {
			System.out.println("새글이 추가되었습니다");
		}else {
			System.out.println("새글 추가 실패");
		}
	}

	// 게시판 목록을 보여주고 메뉴를 나타내며 사용자가 입력한 메뉴 번호를 입력 받아 반환하는 메서드
	private int displayMenu(String title) {
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println(" NO     제 목      작성자      조회수");
		System.out.println("----------------------------------------");
		List<JdbcBoardVO> boardList= null;
		
		if(title==null) {
			boardList = service.getAllBoard();
			
		}else {
			boardList = service.getSearchBoard(title);
		}
		
		if (boardList == null || boardList.size() == 0) {
			System.out.println(" 출력할 게시글이 하나도 없습니다...");
		} else {
			for (JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "\t" + boardVo.getBoard_title() + "\t"
						+ boardVo.getBoard_writer() + "\t" + boardVo.getBoard_cnt() + "\t");
			}

		}
		System.out.println("----------------------------------------");
		System.out.println("메뉴 : 1.새글작성  2.게시글보기  3.검색  0.작업끝");
		System.out.print("작업선택 >> ");
		return scan.nextInt();

	}
}
