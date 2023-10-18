package kr.or.ddit.basic.args;



public class ArgsTest {
	
	/*
	 	가변형인수 ==> 메서드의 인수의 개수가 메서드를 호출할 때마다 다를 수 있을 때 사용한다.
	 	
	 	-가변형 인수는 메서드 안에서는 배열로 처리된다.
	 	-가변형 인수는 한가지 자료형만 사용할 수 있다.
	 */
	
	// 매개변수로 받은 정수들의 합계를 구하는 메서드 만들기
	// (이 정수들의 개수는 상황에 따라 다르다.)
	
	//배열을 이용한 메서드
	public int sumArr(int[] data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum +=data[i];
		}
		return sum;
	}
	
	// 가변형 인수를 이용한 메서드
	public int sumArg(int...data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum +=data[i];
		}
		return sum;
	}
	
	// 가변형 인수와 일반적인 인수를 같이 사용할 경우에는 가변형 인수를 제일 뒤쪽에 배치해야 한다.
	// 가변형과 다음 매개변수가 같은 타입일 경우 모든 데이터가 정수로 들어가기 때문에 순서가 가변형 
	// 인수가 앞쪽에 있으면 구분 짓기 어렵기 때문
	public String sumArg2(String name,int...data) {
		int sum = 0;
		for(int i=0; i<data.length; i++) {
			sum +=data[i];
		}
		return name +"씨의 합계 : "+sum;
	}
	
	public static void main(String[] args) {
		ArgsTest at=new ArgsTest();
		
		//1,2,3,4,5fmf sumArr() 메서드에 전달해서 결과를 출력하시오
		
		//배열을 선언함과 동시에 초기화 하고싶을때 사용
		int[] numArr = {1,2,3,4,5};
		//미리 배열변수를 선언후 배열을 초기화 하고싶을때 사용할 수 잇음
		int[] numArr2 = new int[]{1,2,3,4,5};
		
		// 반환값 저장
		int result =at.sumArr(numArr);
		System.out.println("결과 : "+ result);
		
		// 100,200,300,400 합계
		System.out.println(at.sumArr(new int[] {100,200,300,400}));
		System.out.println("-------------------------------------");
		
		System.out.println(at.sumArg(1,2,3,4,5));
		System.out.println(at.sumArg(100,200,300,400));
		
		System.out.println(at.sumArg2("홍길동",30,60,90));
//		// 인수
//		at.test01(0, "일지매");
//		System.out.println("--------------------------");
//		at.test01(3, "이순신");
	}
	
//	public void test01(int a, String str) {
//		System.out.println("a = "+ a);
//		System.out.println("str ="+ str);
//	}
//
//	public int test02(int a, int b, int c) {
//		return a+b+c;
//	}

}

