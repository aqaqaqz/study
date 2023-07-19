package main;

import java.util.Arrays;
import java.util.Collection;
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
		
		//filter(Predicate<T>) -> Predicate : boolean을 리턴하는 함수형 인터페이스
		Util.print.line("\n************************Man List************************");
		Util.print.stream(userList.stream().filter(u -> Gender.M.equals(u.getGender())));
		Util.print.line("\n***********************Woman List***********************");
		Util.print.stream(userList.stream().filter(u -> Gender.F.equals(u.getGender())));
		
		//match
		System.out.println();
		Util.print.booleanList(
			userList.stream().allMatch(u -> u.getAge()>50), //allMatch => 모든 요소들이 일치해야 true
			userList.stream().anyMatch(u -> u.getAge()>50), //anyMatch => 하나라도 일치하면 true
			userList.stream().noneMatch(u -> u.getAge()>50) //noneMatch => 모두가 불일치해야 true
		);
		
		//mapping(Function<? super T, ? extends R> mapper)
		Util.print.line();
		Util.print.stream(userList.stream().map(u -> u.getAge()*-1)); //각 나이에 -1을 곱해서 나이만 추출하여 stream으로 생성한다.
		Util.print.line();
		Util.print.stream(userList.stream().map(u -> { u.setAge(1); return u; })); //자신을 리턴해서 stream으로 생성도 가능하다.
		
		//flatMap(Function<? super T, ? extends R> mapper)
		//중첩구조를 한단계 제거해줌 ex) [[1,2], [3,4], [5,6]] => [1,2,3,4,5,6]
		Util.print.stream(Arrays.asList(Arrays.asList(1,2), Arrays.asList(3,4), Arrays.asList(5,6)).stream().flatMap(Collection<Integer>::stream));
		
		//builder
		//Stream<?> builderStream = Stream.<String>builder().add("value1").add("vaule2").build();
		Util.print.line();
		Util.print.stream(Stream.<String>builder().add("value1").add("vaule2").build());
		
		//generate(Supplier<T>) -> Supplier는 인자는 없고 리턴값만 존재하는 함수형 인터페이스
		// limit(n)을 안써주면 무한대로 생성해서 힙 터짐(Out Of Memory) 
		Util.print.line();
		Util.print.stream(Stream.generate( ()-> new User.Builder("generateUser").age(1).gender(Gender.M).build() ).limit(3));
		
	}
}

