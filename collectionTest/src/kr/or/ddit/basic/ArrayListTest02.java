package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 	문제) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에 
 		 이 ArrayList에 저장된 이름들 중에서 '김'씨 성의 이름을 모두 출력하시오.
 		 (입력은 Scanner객체를 이용한다.
 */
public class ArrayListTest02 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
//		String name = scanner.nextLine();
		for(int i=0; i<5; i++) {
			System.out.print("5명의 이름 입력 ==>");
			String name = scanner.nextLine();
			list.add(name);
		}
		System.out.println("<김씨 성을 가진 사람들>");
		 for (String name : list) {
	            if (name.startsWith("김")) { // startsWith 메소드로 김씨 성을 판별
	                System.out.println(name);
		
//		list.contains(list)
//		System.out.println("'김'의 위치값 : "+ list.contains());
		
//	    for(int i=0; i<list.size(); i++) {
//	    	if(list.get(i).substring(0,1).equals("김")){
//	    		System.out.println(list.get(i));
//	         if(list.get(i).indexOf("김")==0){
//	    	 System.out.println(list.get(i));
//	    	}
//	   	     if(list.get(i).charAt(0)=='김'){
//	   	     System.out.println(list.get(i));
//	   	    	}
//	   
//	    }
//		
		
		
	}
	
	

		 }
		 }
}
