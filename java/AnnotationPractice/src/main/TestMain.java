package main;

public class TestMain {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		TestClass c = new TestClass();
		c.functionAnnotation();			//ElementType.METHOD
		c.getClassAnnotationName();		//ElementType.TYPE
		c.getFieldAnnotationName();		//ElementType.FIELD
	}

}
