package main;

import java.util.List;

import dto.User;
import util.Util;

public class Main {

	public static void main(String[] args) {
		List<User> arr = Util.user.getUserTestList();
		Util.print.userList(arr);
	}

}
