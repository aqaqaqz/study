package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.DoubleStream;
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
		Util.print.line();
		Util.print.objList(
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
		
		//reduce : stream에서 하나의 결과를 만들어냄
		//	- accumulator : 각 요소를 처리하는 계산 로직. 각 요소가 올 때마다 중간 결과를 생성하는 로직.
		//	- identity : 계산을 위한 초기값으로 스트림이 비어서 계산할 내용이 없더라도 이 값은 리턴.
		//	- combiner : 병렬(parallel) 스트림에서 나눠 계산한 결과를 하나로 합치는 동작하는 로직.
		Util.print.line();
		//하나의 인자 (BinaryOperator<T> accumulator) return Optional<T>;
		Util.print.line(userList.stream().reduce((u1, u2) -> (new User.Builder("reduceUser1").age(u1.getAge() + u2.getAge()).build())).get().toString());
		//두개의 인자 (T identity, BinaryOperator<T> accumulator) return T; //name을 보면 defaultUser가 아닌 reduceUser2가 출력. 초기값은 로직에서 사용되는 값만 사용.
		Util.print.line(userList.stream().reduce( 
				new User.Builder("defaultUser").age(10).build(),
				(u1, u2) -> (new User.Builder("reduceUser2").age(u1.getAge() + u2.getAge()).build())).toString()
		);
		//세개의 인자 (U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) return <U>;
		//아래의 로직은 각각의 stream 값+100을 더해주고 세번째 combiner에서 해당 스트림들을 계산한다
		//(100+1) + (100+2) + (100+3) = 306
		//일반 stream의 reduce에는 세번째 인자를 넣어도 작동하지 않기에 변화가 없음.
		Util.print.line();
		Util.print.line("parallelStream : " + Arrays.asList(1,2,3).parallelStream().reduce(
				100,
				(n1, n2) -> n1+n2,
				(n1, n2) -> n1+n2
		).toString());
				
		//calculating
		Util.print.line();
		List<Double> calSampleList = new ArrayList<>(Arrays.asList(1d,2d,3d,4d,5d,6d,7d,8d,9d));
		Util.print.objList(
			"Max:" + calSampleList.stream().max((d1, d2) -> d1==d2 ? 1 : (d1>d2?1:-1)).get(),
			"Min:" + calSampleList.stream().min((d1, d2) -> d1==d2 ? 1 : (d1>d2?1:-1)).get(),
			"Sum:" + calSampleList.stream().mapToDouble(i -> i).sum(),
			"Avg:" + calSampleList.stream().mapToDouble(i->i).average().getAsDouble(), //Optional로 리턴
			"Cnt:" + calSampleList.stream().mapToDouble(i -> i).count()
		);
	}
}

