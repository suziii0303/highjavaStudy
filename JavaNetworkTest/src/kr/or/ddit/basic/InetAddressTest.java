package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 ==> IP주소를 다루기 위한 클래스

		// www.never.com의 IP 정보 가져오기
		InetAddress ip = InetAddress.getByName("www.naver.com");

		System.out.println("Host Name : " + ip.getHostName());
		System.out.println("Host Address : " + ip.getHostAddress());
		System.out.println("toString : " + ip.toString());

		
		
		
		// 자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("Host Name : " + localIp.getHostName()); // 컴퓨터 이름
		System.out.println("Host Address : " + localIp.getHostAddress()); //
		System.out.println();

		// IP주소가 여러개인 호스트의 IP정보 가져오기
		InetAddress[] ipArr = InetAddress.getAllByName("www.naver.com");
		for(InetAddress nip : ipArr) {
			System.out.println(nip);
		}

	}

}
