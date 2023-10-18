package kr.or.ddit.basic;

/*
	Thread의 stop()메서드를 호출하면 해당 쓰레드가 바로 멈춘다.
	이 때 사용하던 자원을 정리하지 못하고 해당 쓰레드가 종료되어 다른 쓰레드가 실행되는데 영향을 줄 수 있다.
	(즉, 교착상태가 될 수 있다.)
	그래서 stop()메서드는 비추천으로 되어 있다...
 */

public class ThreadTest11 {
	public static void main(String[] args) {
		

/*		ThreadStopTest1 th1 = new ThreadStopTest1();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
//		th1.stop();	// 만나서 멈추는건 같지만 사용하던 자원에 접근을 하지 못함
		th1.setStop(true);
 */
		
		// interrupt()메서드를 이용한 쓰레드 멈추기(효율적이지 않음)-반복이 많이 될수록 비효율적
		ThreadStopTest2 th2 = new ThreadStopTest2();
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		th2.interrupt();
	}

}

// 쓰레드를 멈추게 하는 연습용 쓰레드
class ThreadStopTest1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop=stop;
	}
	@Override
	public void run() {
		while(!stop) {
			System.out.println("쓰레드 실행 중...");
		}
		System.out.println("자원 정리...");
		System.out.println("쓰레드 종료...");
	}
}

// interrupt()메서드를 이용하여 쓰레드를 멈추게 하는 연습용 쓰레드
class ThreadStopTest2 extends Thread{
	
	@Override
	public void run() {
		/*
		// 방법1 ==> interrupt()메서드와 sleep()메서드를 이용하는 방법
		try {
			while(true) {
				System.out.println("작업 실행 중...");
				Thread.sleep(1);
			}
		}catch (InterruptedException e) {}
			System.out.println("자원 정리...");
			System.out.println("쓰레드 종료...");
		*/
		/*
		// 방법2
		while(true) {
			System.out.println("Thread 실행 중...");
			
			// interrupt()메서드가 호출되었는지 여부를 검사한다.
			
			// 검사 방법1 ==> Thread객체의 인스턴스 메서드인 isInterrupted()메서드 이용하기
			//  isInterrupted()메서드는 interrupt()메서드가 호출되면 true를 반환한다.
			if(this.isInterrupted()) {
				break;
			}
		}
		
		System.out.println("자원 정리...");
		System.out.println("쓰레드 종료...");
		*/
		
		// 검사 방법2 ==> Thread클래스의 정적 메서드인 interrupted()메서드 이용하기
		//  interrupted()메서드도 interrupt()메서드가 호출되면 true를 반환한다.
		while(true) {
			System.out.println("Thread 실행 중...");
		if(Thread.interrupted()) {
			break;	
		}
	}
	}
}
