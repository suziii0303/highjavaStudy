package kr.or.ddit.singleton;

public class SingletonTest {
	
	public static void main(String[] args) {
//		Mysingleton test1 = new Mysingleton(); // 외부에서 new명령으로 생성 불가
		
		// 객체는 getInstance()메서드를 호출해서 생성한다
		Mysingleton test2 = Mysingleton.getInstance();
		Mysingleton test3 = Mysingleton.getInstance();
		
		System.out.println("test2 => "+test2.toString());
		System.out.println("test3 => "+test3);
		System.out.println();
		System.out.println(test2==test3);	// 주소값이 같음
		
		test2.displayTest();
		
	}
}
