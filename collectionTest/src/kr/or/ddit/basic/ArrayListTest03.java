package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 	문제) 5명의 별명을 입력 받아 ArrayList에 저장하고 저장된 별명들 중에 
 		 별명의 길이가 제일 긴 별명을 출력하시오.
 		 (단, 각 별명의 길이는 모두 다르게 입력한다.)
  */
public class ArrayListTest03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<String> list = new ArrayList<>();

		for(int i=0; i<5; i++) {
			System.out.print("5명의 별명 입력 ==>");
			String name = scanner.nextLine();
			list.add(name);
		}
		//제일 긴 별명이 저장될 변수를 선언하고 List의 첫번째 데이터로 초기화한다.
		String maxList = list.get(0);
		System.out.println("<제일 긴 별명>");

		 for(int i=1; i<list.size();i++) {
			 if(maxList.length()< list.get(i).length()) {
				 maxList = list.get(i);
			 }
		 }
		 System.out.println("==>"+maxList);
		String result = list.get(0);	 
		for (String str : list) {
		        if (str.length() < result.length()) {
		        	result = str;
		        }
			 }
		System.out.println("==>"+result);
		    }
		 
		   

}
