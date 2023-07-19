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
	
	public void objList(Object... arr) {
		StringBuffer sb = new StringBuffer();
		
		for(Object o : arr) {
			if(!sb.isEmpty()) sb.append(", ");
			sb.append(o.toString());
		}
		
		line(sb.toString());
	}
	
	public void line() {
		line("");
	};
	public void line(String str) {
		System.out.println(str);
	}
}
