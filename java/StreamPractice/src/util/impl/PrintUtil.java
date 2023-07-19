package util.impl;

import java.util.List;
import java.util.stream.Stream;

public class PrintUtil {
	public void stream(Stream<?> s) {
		this.list(s.toList());
	}
	
	public void list(List<?> list) {
		list.stream().forEach(o -> this.line(o.toString()+" "));
	}
	
	public void booleanList(boolean... arr) {
		for(boolean b : arr) {
			line( b ? "T" : "F" );
		}
	}
	
	public void line() {
		line("");
	};
	public void line(String str) {
		System.out.println(str);
	}
}
