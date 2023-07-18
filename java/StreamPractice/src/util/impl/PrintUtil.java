package util.impl;

import java.util.List;

import dto.User;

public class PrintUtil {
	public void list(List<?> list) {
		list.stream().forEach(o -> System.out.print(o.toString()));
	}
	
	public void booleanList(boolean... arr) {
		for(boolean b : arr) {
			System.out.println( b ? "T" : "F" );
		}
	}
}
