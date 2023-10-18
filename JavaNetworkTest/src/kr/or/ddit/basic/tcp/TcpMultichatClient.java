package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultichatClient {
	public static void main(String[] args) {
		new TcpMultichatClient().clientStart();
	}
	
	// 클라이언트 시작 메서드
	public void clientStart() {
		Socket socket =null;
		try {
			socket = new Socket("192.168.142.15", 7777);		// Socket객체를 생성하고 서버에 접속하기
			System.out.println("서버에 연결되었습니다...");
			//---------------------------------------------
		
			// 메시지 전송용 쓰레드 객체와 수신용 쓰레드 객체를 생성하고 실행한다.
			ClientSender sender = new ClientSender(socket);
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}// 시작 메서드 끝...
	
	//----------------------------------------------------
	// 메시지 전송용 쓰레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;
		
		private String name;	// 대화명이 저장될 변수
		private Scanner scan;
		
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			
			try {
				din = new DataInputStream(this.socket.getInputStream());	//수신용
				dout = new DataOutputStream(this.socket.getOutputStream()); //송신용
				
				if(dout!=null) {
					// 클라이언트용 프로그램은 처음 실행하면 서버에 접속에 성공하면
					// 첫번째로 '대화명'을 입력받아 전송하고, '대화명'의 중복여부를 feedback으로 받아서 반환한다.

					// 대화명이 중복되지 않을 때까지 반복...
					while(true) {
						System.out.println("대화명 입력 >>");
						String name = scan.nextLine();
						
						dout.writeUTF(name);	// 입력받은 대화명을 서버로 전송한다.
						
						String feedback = din.readUTF(); // 대화명 중복 여부를 응답으로 받는다.
						
						if("대화명중복".equals(feedback)) {		// 대화명이 중복될 때...
							System.out.println(name+ "은 대화명이 중복됩니다...");
							System.out.println("다른 대화명을 입력하세요...");
						}else {		//대화명이 중복되지 않을 때...
							this.name =name;
							System.out.println(name+"대화명으로 대화방에 입장했습니다");
							break;	// 반복문 탈출...
						}
					}	// while문 끝
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} // 생성자 끝...
		
		@Override
		public void run() {
			try {
				while(dout!=null) {
					// 키보드로 입력한 메시지를 서버로 보낸다.
					dout.writeUTF("["+name+"]"+scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	
	
	//---------------------------------------------------------
	// 메시지 수신용 쓰레드
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream din;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}// 생성자 끝...
		
		@Override
		public void run() {
			try {
				while(din!= null) {
					// 
					System.out.println(din.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

