package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random ran = new Random();
      
        System.out.println("========================");
        System.out.println("Lotto 프로그램");

        int total = 0;
        int count = 0;

        while(true) {
        	System.out.println("------------------------");
            System.out.println("1. Lotto 구입");
            System.out.println("2. 프로그램 종료");
            System.out.println("========================");
            System.out.print("메뉴선택 : ");
            int endOrBuy = scanner.nextInt();

            switch (endOrBuy) {
                case 1:
                    System.out.println("Lotto 구입 시작");
                    System.out.println("(1000원에 로또번호 하나입니다.)");
                    System.out.print("금액 입력 : ");
                    int money = scanner.nextInt();

                    if(money < 1000) {
                        System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
                    } else if(money > 100000) {
                        System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
                    } else if(total + money/1000 > 100) {
                        System.out.println("로또 번호는 최대 100장까지입니다.");
                    } else {
                        count = money/1000;
                        total += count*1000;

                        System.out.println("행운의 로또번호는 아래와 같습니다.");
                        for(int i=0; i<count; i++) {
                            HashSet<Integer> numbers = new HashSet<>();
                            while(numbers.size() < 6) {
                                numbers.add(ran.nextInt(45)+1);
                            }
                            System.out.println("로또번호" + (i+1) + " : " + numbers);
                        }

                        int change = money - count*1000;
                        System.out.printf("받은 금액은 %,d원이고 거스름돈은 %,d원입니다.\n", money, change);
                    }
                    break;

                case 2:
                    System.out.println("감사합니다.");
                    return;

                default:
                    System.out.println("잘못된 메뉴 선택입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }
}

