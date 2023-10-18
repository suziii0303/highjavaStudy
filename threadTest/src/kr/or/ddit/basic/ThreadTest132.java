package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// 경마 프로그램

public class ThreadTest132 {
	static int strRank = 1;

	public static void main(String[] args) {
		ArrayList<Horse2> horseArr = new ArrayList<>();

		horseArr.add(new Horse2("1번마"));
		horseArr.add(new Horse2("2번마"));
		horseArr.add(new Horse2("3번마"));
		horseArr.add(new Horse2("4번마"));
		horseArr.add(new Horse2("5번마"));
		horseArr.add(new Horse2("6번마"));
		horseArr.add(new Horse2("7번마"));
		horseArr.add(new Horse2("8번마"));
		horseArr.add(new Horse2("9번마"));
		horseArr.add(new Horse2("10번마"));
		
		for (Horse2 th : horseArr) {
			th.start();
		}
		for (Horse2 hh : horseArr) {
			try {
				hh.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		Collections.sort(horseArr);
		System.out.println();
		System.out.println("경기 결과");
		for (Horse2 horse : horseArr) {
			System.out.println(horse.gethN() + " " + horse.getRanking() + "등");
		}

	}
}

class Horse2 extends Thread implements Comparable<Horse2> {
	private String hN; // 말이름
	private int location; // 현재 위치
	private int ranking; // 순위

	public Horse2(String hN) {
		this.hN = hN;
	}

	public String gethN() {
		return hN;
	}

	public void sethN(String hN) {
		this.hN = hN;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return "Horse [hN=" + hN + ", ranking=" + ranking + "]";
	}

	@Override
	public void run() {

		Random rnd = new Random();

		for (int i = 0; i <= 50; i++) {
			this.location=i;
			System.out.printf("\n" + hN + "현재 위치 : ");
			
			System.out.println();
			System.out.println();
			System.out.println();
			try {
				Thread.sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {
			}
		}
		System.out.println(hN + " 끝");

		setRanking(ThreadTest132.strRank);
		ThreadTest132.strRank++;
	}

	@Override
	public int compareTo(Horse2 o) {
		return Integer.compare(this.getRanking(), o.getRanking());
	}
}