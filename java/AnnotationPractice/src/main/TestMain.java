package main;

public class TestMain {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		TestClass c = new TestClass();
		c.functionAnnotation();			//ElementType.METHOD
		c.getClassAnnotationName();		//ElementType.TYPE
		c.getFieldAnnotationName();		//ElementType.FIELD
		
		c.setPhone("aaa");
//		스프링의 LocalValidatorFactoryBean에 등록을 해줘야 검사를 함
//		validation-api-1.1.0.final.jar 다운받아 추가시킨 상태에서는 코드 작성은 되도 기능동작이 안됨
//		스프링부트에서는 의존성 설정만해주면 해당 기능이 자동으로 설정됨 => 나중에 다시 해봐야함!
		
	}

}
