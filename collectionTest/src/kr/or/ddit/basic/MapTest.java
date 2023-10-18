package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
	/*
		 Map ==> key값과 value값을 한 쌍으로 관리하는 객체
		 	 - key값은 중복을 허용하지 않고 순서가 없다. (Set의 특징을 갖는다.)
		 	 - value값은 중복을 허용한다.
		 	 
	*/
		HashMap<String, String> map = new HashMap<>();
		
		// 자료 추가 ==> put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map => "+map);
		
		// 자료 수정 ==> 데이터를 추가할 때 'key값'이 같으면 나중에 추가한 값이 저장된다.
		map.put("addr", "서울");
		System.out.println("map => "+map);

		// 자료 읽기 ==> get(key값) 
		//		==> 주어진 'key값'과 짝이되는 'value값'을 반환한다.
		//		==> 주어진 'key값'이 없으면 null이 반환된다.
		System.out.println("이름 : "+map.get("name"));
		System.out.println("별명 : "+map.get("alias"));
		
		// 자료 삭제 ==> remove(key값)
		//		==> 주어진 'key값'이 같은 자료를 찾아서 삭제한다.
		//		==> 반환값 : 삭제된 자료의 'value값'이 반환된다.
		
//		String removeTel = map.remove("tel");
//		System.out.println("삭제 후 map => "+map);
//		System.out.println("삭제된 value 값 => "+removeTel);
		
		// key값이 존재하는지 여부를 나타내는 메서드 : containsKey(key값)
		// => 해당 'key값'이 있으면 true, 없으면 false 반환
		System.out.println("tel 키값의 존재 여부 : "+map.containsKey("tel"));
		System.out.println("alias 키값의 존재 여부 : "+map.containsKey("alias"));
		System.out.println("-------------------------------------------------");
		//-------------------------------------------------------------------------
		
		// Map에 저장된 모든 데이터를 읽어와서 사용하는 방법
		
		// 방법1 : Map의 모든 key값을 읽어와서 사용하는 방법 ==> keySet()메서드 이용하기
		
		// keySet()메서드 ==> Map의 모든 key값들을 Set형을 반환한다.
		
		Set<String> keySet = map.keySet();
		
		// 방법1-1 : Iterator이용하기
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next();		// key값 구하기
			String value =map.get(key);
			
			System.out.println(key +":"+value);
			
		}
		System.out.println("----------------------------------");
		
		// 방법1-2 : 향상된 for문 사용하기
		for(String key2: keySet) {
			String value2 = map.get(key2);
			System.out.println(key2 +":"+value2);
			System.out.println("----------------------------------");
			
			// 방법2 : value값만 읽어와서 처리하기
			//		==> values()메서드를 이용한다.
			for(String value3 : map.values()) {
				System.out.println(value3);
			}
			System.out.println("----------------------------------");
			
			
			
			// key값은 번호(int)로 하고 value값은 Member의 인스턴스로 하는 Map객체 생성하기
			HashMap<Integer, Member> memberMap = new HashMap<>();
			
			memberMap.put(1, new Member(1, "홍길동", "010-1111-1111"));
			
			Member mem = new Member(1, "이순신", "010-2222-2222");
			memberMap.put(2, mem);
			
			System.out.println("map의 개수 : "+memberMap.size());
			System.out.println("memberMap => "+ memberMap);
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
	}

}
