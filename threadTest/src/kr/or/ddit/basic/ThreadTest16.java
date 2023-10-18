package kr.or.ddit.basic;


// 은행의 입출금을 쓰레드로 처리하는 예제

public class ThreadTest16 {		// 이 클래스의 객체가 공통으로 사용할 객체가 된다.
	
	private int balance;		// 잔액이 저장될 변수
	
	public int getBalnace() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance=balance;
	}
	
	
	// 입금을 처리하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	// 출금을 처리하는 메서드 ( 반환값은 출금 성공이면 true, 출금 실패이면 false)
	public synchronized boolean withdraw(int money) {
		
		if(balance >= money) {
			for(long i=1L; i<=1000000000L; i++) { } // 시간 지연용
			balance -=money;
			System.out.println("메서드 안에서 balance ="+balance);
			return true;
		}else {
			return false;	// 출금 실패!!
		}
	}
	
	
	public static void main(String[] args) {
		ThreadTest16 acount = new ThreadTest16();
		acount.setBalance(10000);	// 잔액을 10000원으로 설정
		
		// 쓰레드는 익명 구현체로 구현...
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000);
				System.out.println("쓰레드에서 result ="+result + ", balance =" +acount.getBalnace());
			}
		};
		//-------------------------------------------------
		
		Thread th1 =new Thread(r);
		Thread th2 =new Thread(r);
		
		th1.start();
		th2.start();
	}
}
