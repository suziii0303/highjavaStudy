package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 	UDP방식 : 비연결 지향, 신뢰성 없다., 데이터가 순서대로 도착한다는 보장이 없다., 그렇지만 TCP방식보다 빠르다.
 	
 	DatagramSocket객체와 DatagramPacket객체를 이용해서 통신한다.
 	- DatagramSocket : 데이터의 송수신과 관련된 작업을 수행한다. (우체부)
 	- DatagramPacket : 주고 받는 데이터와 관련된 작업을 수행한다. (소포, 편지)
 	   		==> 수신용 생성자와 송신용 생성자를 따로 제공한다.
 	   		
 	TCP방식의 경우에는 스트림 객체를 이용해서 송수신하지만
 	UDP방식의 경우에는 테이터그램을 이용해서 송수신한다.
 
 */

public class UdpServer {
	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			
			System.out.println("서버 실행 중...");
			
			while(true) {
				// 클라이언트가 보낸 메시지 받기
				
				// 데이터가 저장될 byte형 배열 선언
				byte[] bMsg = new byte[512];
				
				// 수신용 패킷 객체 생성
				//		==> 수신된 데이터가 저장될 byte형 베열, 배열의 길이를 인수값으로 지정하여 생성한다.
				
				DatagramPacket inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터를 수신한다. ==> receive()메서드를 이용한다.
				//		이 메서드는 데이터 올 때까지 기다린다.
				//		수신된 데이터는 수신용패킷객체에 저장된다.
				socket.receive(inpacket);
				
				// 수신받은 패킷을 이용하여 상대방의 IP주소와 Port번호를 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("상대방의 IP정보 : "+ address);
				System.out.println("상대방의 Port번호 : "+ port);
				System.out.println();
				
				// 상대방이 보낸 메시지 출력하기
				// 상대방이 보낸 데이터는 '수신용패킷객체'의 getData()메서드로 구할 수 있고,
				// 					수신용 패킷객체를 생성할 때 지정한 byte형 배열에도 저장된다.
				// 상대방이 보낸 데이터의 크기는 '수신용패킷객체'의 getLength()메서드로 구할 수 있다.
				
				
				// 수신받은 데이터는 byte형 배열이기 때문에 이것을 문자열로 변환해야 한다.
				
				// 방법1 ==> 패킷객체 생성할 때 사용한 byte형 배열 이용하기
//				String msg = new String(bMsg, 0, inpacket.getLength(), "utf-8");
				
				// 방법2 ==> getDate()메서드 이용하기
				String msg = new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8");
				
				if("/end".equals(msg)) {
					break;	// 반복문 탈출...
				}
				
				System.out.println("상대방이 보낸 메시지 : "+msg);
				System.out.println();
				
				//-----------------------------------------------------------
				
				// 상대방에게 메시지 보내가 (수신 받은 메시지를 그대로 송신하는 예제)
				
				// 송신할 메시지를 byte형 배열로 변환한다.
				byte[] sendMsg = msg.getBytes("utf-8");
				
				// 송신용 페킷 객체 생성
				//		==> 전송할 데이터가 지정된 byte형 배열,
				//		==> 전송할 데이터의 길이, 상대방주소정보, 포트번호 이렇게 4가지를 지정하여 생성한다
				DatagramPacket outpacket =
						new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 데이터 송신하기 ==> send()메서드 이용
				socket.send(outpacket);
				
				System.out.println("송신 완료...");
				System.out.println();
			
			
			} // while문 끝 ...
			
			
			System.out.println("통신 끝...");
			socket.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
