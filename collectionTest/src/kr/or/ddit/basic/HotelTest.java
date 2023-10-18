package kr.or.ddit.basic;



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
public class HotelTest {

      private Scanner scan;
      private  Map<Integer, Room> hotelRoom;
      public HotelTest() {
         scan = new Scanner(System.in);
         hotelRoom = new HashMap<>();
         for (int i = 200; i < 500; i+=100) {
            for (int j = 1; j < 10; j++) {
               switch(i/100){
               case 2: 
                  hotelRoom.put(i+j, new Room(i+j, "싱글룸","-" ));
                  break; 
               case 3: 
                  hotelRoom.put(i+j, new Room(i+j,"더블룸","-" ));
                  break;
               case 4: 
                  hotelRoom.put(i+j, new Room(i+j, "스위트룸", "-" ));
                  break;
                  
               }
            }
         }
         

      }
      
      public static void main(String[] args) {
         new HotelTest().HotelStart();
         
      }
      public void in() {
         System.out.println("------------------------");
         System.out.println("체크인 작업");
         System.out.println("------------------------");
         System.out.println("* 201~209 : 싱글룸");
         System.out.println("* 301~309 : 더블룸");
         System.out.println("* 401~409 : 스위트룸");
         System.out.println("방 번호 입력 >>");
         int roomNum =scan.nextInt();
         Room r = hotelRoom.get(roomNum);
         if(!hotelRoom.containsKey(roomNum)) {
            System.out.println(roomNum+"호 객실은 존재하지 않습니다");
            return;
         }if(!hotelRoom.get(roomNum).getName().equals("-")){
              System.out.println(roomNum + "호 객실은 이미 손님이 있습니다.");
              return;
          }
         
         System.out.println("누구를 체크인 하시겠습니까?");
         System.out.println("이름입력 >>");
         String name =scan.next();
         System.out.println("체크인 완료되었습니다.");
         hotelRoom.get(roomNum).setName(name);
            System.out.println(roomNum + "객실의"+name+"체크인 완료");
      
         }
         
      public void out() {
          System.out.println();
          System.out.println("------------------------");
         System.out.println("체크아웃 작업");
         System.out.println("------------------------");
            System.out.println("체크아웃할 방번호를 입력하세요");
            System.out.print("방번호 입력 >> ");
            int roomNum = scan.nextInt();
            if(!hotelRoom.containsKey(roomNum)) {
               System.out.println(roomNum+"호 객실");
               return;
            }
            if(hotelRoom.get(roomNum).getName().equals("-")){
                 System.out.println(roomNum + "호 객실은 체크인한 사람이 없습니다.");
                 return;
             }
           
             System.out.println(roomNum + "호 객실의  "+hotelRoom.get(roomNum).getName()+"님을 체크아웃을 완료하였습니다.");
             hotelRoom.get(roomNum).setName("-");
      }
         


   public void State() {
      System.out.println("----------------------------------------------");
      System.out.println(" 현재 객실 상태");
      System.out.println("----------------------------------------------");
      System.out.println("방 번호    방 종류    투숙객 이름");
      System.out.println("----------------------------------------------");
//      Set<Integer> setKey=hotelRoom.keySet();
      //hotelRoom.keySet 정렬
      List<Integer> listKeySet = new ArrayList<>(hotelRoom.keySet());
      Collections.sort(listKeySet);
      List<Room>list = new ArrayList<>();
      
      for(Integer key : listKeySet){
          Room value =  hotelRoom.get(key);
          
          list.add(value);
       }

      

      for(int i = 0; i < list.size(); i++) {
         System.out.println(list.get(i).getRoomNum()+"\t"+list.get(i).getRoom()+"\t"+list.get(i).getName());
      }
      System.out.println("----------------------------------------------");
   }
      public void HotelStart() {
         System.out.println("***************************************");
         System.out.println("호텔문을 열엇습니다 어서오십시오");
         System.out.println("***************************************");
         System.out.println();
         while (true) {
            System.out.println("------------------------");
            System.out.println("1.체크인 \t 2.체크아웃 \t 3.객실상태 \t 업무종료");
            System.out.println("--------------------------");
            System.out.print("선택 >>");
            int menuNum = scan.nextInt();
            
         
         switch (menuNum) {
         case 1:
            
            in();
            
            break;
         case 2:
            out();
            break;
         case 3:
            State();
            break;
         case 4:
            System.out.println("업무를 종료 합니다...");
            break;
         default:
            System.out.println("잘못 입력하셨습니다.");
         }
      }
      }   
   }
   class Room{
      private int roomNum;
      private String room;
      private String name;
      
      public Room(int roomNum, String room,String name ) {
        
         this.roomNum=roomNum;
         this.name=name;
         this.room=room;
      }

      public int getRoomNum() {
         return roomNum;
      }

      public void setRoomNum(int roomNum) {
         this.roomNum = roomNum;
      }
      
      public String getRoom() {
         return room;
      }

      public void setRoom(String room) {
         this.room = room;
      }

      public String getName() {
         return name;
      }
      
      public void setName(String name) {
         this.name = name;
      }

      @Override
      public String toString() {
         return "Room [roomNum=" + roomNum + ", room=" + room + ", name=" + name + "]";
      }
      
   }

