package util.impl;

import java.util.List;

import dto.User;

public class PrintUtil {
	public void userList(List<User> list) {
		for(User u : list) {
			System.out.print(u.toString());
		}
	}
}
