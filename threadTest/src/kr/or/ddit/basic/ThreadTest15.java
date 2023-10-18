package kr.or.ddit.basic;

public class ThreadTest15 {
	
	public static void main(String[] args) {
		ShareOvjext sObj = new ShareOvjext();
		
		TestThread th1 = new TestThread("Test1", sObj);
		TestThread th2 = new TestThread("Test2", sObj);
		
		th1.start();
		th2.start();
	}
}


// 공통으로 사용할 클래스
class ShareOvjext {
	private int sum = 0;
	
	// 동기화 설정하기
//	public synchronized void add() {	// 방법1 메서드 자체에 동기화 설정을 한다.
	public synchronized void add() {
		
		synchronized (this) {	// 방법2 ==> 동기화 블럭으로 동기화 설정을 한다.
			
		int n = sum;
		
		n += 10;
		
		sum = n;
		
		System.out.println(Thread.currentThread().getName()+" 합계 : "+ sum);
		}
	}
}


// Test용 쓰레드
class TestThread extends Thread{
	private ShareOvjext sObj;
	
	public TestThread(String name, ShareOvjext sObj) {
		super(name);
		this.sObj=sObj;
	}
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			sObj.add();
		}
	}
}