package main;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annotation.ClassAnnotation;
import annotation.FieldAnnotation;
import annotation.FuncAnnotation;

@ClassAnnotation
public class TestClass {
	
	@FieldAnnotation(name="string")
	public String str1;
	@FieldAnnotation(name="string")
	public String str2;
	@FieldAnnotation(name="int")
	public int int1;
	@FieldAnnotation(name="int")
	public int int2;
	
	@FuncAnnotation(myValue1 = "input value")
	public void functionAnnotation() throws NoSuchMethodException, SecurityException {
		System.out.println("=======================functionAnnotation==========================");
		
		Annotation[] annotationList = this.getClass().getDeclaredMethod("functionAnnotation").getAnnotations();
		
		for(Annotation a : annotationList) {
			System.out.println(a.toString());
			
			if(a instanceof FuncAnnotation) {
				System.out.println("myValue1 : " + ((FuncAnnotation)a).myValue1());
				System.out.println("myValue2 : " + ((FuncAnnotation)a).myValue2());
			}
		}
		
		System.out.println();
	}
	
	public void getClassAnnotationName() {
		System.out.println("=======================getClassAnnotationName==========================");
		
		Annotation[] annotationList = this.getClass().getAnnotations();
		for(Annotation a : annotationList) {
			System.out.println(a.toString());
			
			if(a instanceof ClassAnnotation) {
				System.out.println("class name : " + ((ClassAnnotation)a).name());
			}
		}
		
		System.out.println();
	}
	
	public void getFieldAnnotationName() {
		System.out.println("=======================getFieldAnnotationName==========================");
		
		Map<String, List<String>> m = new HashMap<>();
		Field[] fieldList = this.getClass().getDeclaredFields();
		for(Field f : fieldList) {
			for(Annotation a : f.getAnnotations()) {
				if(a instanceof FieldAnnotation) {
					String name = ((FieldAnnotation)a).name();
					if(!m.containsKey(name)) m.put(name, new ArrayList<String>());
					m.get(name).add(f.getName());
				}
			}
		}
		
		for(String key : m.keySet()) {
			System.out.print(key + " : ");
			for(String name : m.get(key))
				System.out.print(name + " ");
			System.out.println();
		}
		
		System.out.println();
	}
}
