package kr.or.ddit.basic;

/*
	wait(), notify()메서드를 이용해서
	두 쓰레드가 번갈아 한번씩 실행되는 예제
 */
public class ThreadTest18 {
	public static void main(String[] args) {
		WorkObject workObj =new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}


// 공통으로 사용할 객체
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 실행 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 메서드에서 처리하는 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}

// WorkObject의 methodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workobj;
	
	public ThreadA(WorkObject wokrkobj) {
		this.workobj=wokrkobj;
	}
	@Override
	public void run() {
		for(int i=0; i<=10; i++) {
			workobj.methodA();
		}
		
		synchronized (workobj) {
			workobj.notify();
		}
	}
}

class ThreadB extends Thread{
	private WorkObject workobj;
	
	public ThreadB(WorkObject wokrkobj) {
		this.workobj=wokrkobj;
	}
	@Override
	public void run() {
		for(int i=0; i<=10; i++) {
			workobj.methodB();
		}
		synchronized (workobj) {
			workobj.notify();	
		}
	}
}