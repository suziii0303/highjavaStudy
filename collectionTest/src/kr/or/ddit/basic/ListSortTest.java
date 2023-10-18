package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

/*
 	정렬과 관련된 interface는 Comparable과 Comparator 이렇게 두가지가 있다.
 	
 	Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때 구현하는 인터페이스 이다.(내부 정렬 기준)
 	
 	Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때 구현하는 인터페이스 이다.(외부 정렬 기준)
 	
 	Comparable에서는 compareTo()메서드를 재정의하고
 	Comparator에서는 compare()메서드를 재정의해야 한다.
 	
 	String클래스, Wrapper클래스, Date클래스, File클래스에는 내부 정렬 기준이 이미 구현되어 있다.
 	(이 클래스들의 내부 정렬 기준은 '오름차순'으로 정렬되도록 구현되어 있다.
 	
 */

public class ListSortTest {
	public static void main(String[] args) {
		ArrayList<String>list =new ArrayList<>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬전 : "+list);
		
		//정렬은 Colletions.sort()메서드를 이용하여 정렬한다.
		//Colletions.sort()메서드는 기본적으로 내부 정렬 기준
		Collections.sort(list);
		System.out.println("정렬후 : "+list);
		
		Collections.shuffle(list);
		System.out.println("자료 섞기 후 : "+list);
		
		// 외부 정렬 기준을 적용하여 정렬하기
		Collections.sort(list,new Desc());
		
		System.out.println("내림차순 정렬 후 : "+list);
		
	}

}

// 외부 정렬 기준 만들기
// 정렬 방식을 정해 주는 Class를 만든다.(이 클래스를 외부 정렬 기준 클래스라고 한다.)
class Desc implements Comparator<String>{

		// 이 compare()메서드를 이용해서 정렬하는 기준으로 정해준다.
		
		// compare()메서드의 반환값의 의미
		// 반환값이 0이면 두 값이 같다.
		// 반환값이 양수면 두 값의 순서를 바꾼다.
		// 반환값이 음수면 두 값의 순서를 바꾸지 않는다.
		
	@Override
	public int compare(String str1, String str2) {
		
//		if (str1.compareTo(str2)>0) {
//			return -1;
//		}else if (str1.compareTo(str2)<0) {
//			return 1;
//		}else {
//		return 0;
//		}
		return str1.compareTo(str2)*-1;
	}
	
	
}