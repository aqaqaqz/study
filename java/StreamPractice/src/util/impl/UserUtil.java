package util.impl;

import java.util.ArrayList;
import java.util.List;

import dto.User;
import dto.entity.Gender;

public class UserUtil {
	public List<User> getUserTestList(){
		return getUserTestList(10);
	}
	
	public List<User> getUserTestList(int size){
		List<User> arr = new ArrayList<>();
		
		for(int i=0;i<size;i++)
			arr.add(getRandUserData("name_"+(i+1)));
		
		return arr;
	}

	private static User getRandUserData(String name) {
		User user = new User
			.Builder(name)
			.age((int)(Math.random()*100))
			.gender(Gender.getRandomGender())
			.build();
			
		return user;
	}
}
