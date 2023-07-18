package main;

import java.util.List;
import java.util.stream.Stream;

import dto.User;
import dto.entity.Gender;
import util.Util;

//람다에서 지역변수가 안되는 이유
//https://dev-jwblog.tistory.com/153

public class Main {
	public static void main(String[] args) {
		//concat
		List<User> userList = Stream.concat(Util.user.getUserTestList(2).stream(), Util.user.getUserTestList(2).stream()).toList();
		Util.print.list(userList);
		
		//filter
		List<User> manList = userList.stream().filter(u -> Gender.M.equals(u.getGender())).toList();
		List<User> womanList = userList.stream().filter(u -> Gender.F.equals(u.getGender())).toList();
		System.out.println("\n************************Man List************************");
		Util.print.list(manList);
		System.out.println("\n***********************Woman List***********************");
		Util.print.list(womanList);
		
		//match
		System.out.println();
		Util.print.booleanList(
			userList.stream().allMatch(u -> u.getAge()>50), //allMatch => 모든 요소들이 일치해야 true
			userList.stream().anyMatch(u -> u.getAge()>50), //anyMatch => 하나라도 일치하면 true
			userList.stream().noneMatch(u -> u.getAge()>50) //noneMatch => 모두가 불일치해야 true
		);
		
		//mapping
		System.out.println();
		Util.print.list(userList.stream().map(u -> u.getAge()*-1).toList()); //각 나이에 -1을 곱해서 나이만 추출하여 stream으로 생성한다.
		System.out.println();
		Util.print.list(userList.stream().map(u -> { u.setAge(1); return u; }).toList()); //그냥 return값을 지정해서 stream으로 생성도 가능하다.
		
		
	}
}

