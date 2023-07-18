package dto;

import dto.entity.Gender;

public class User {
	private String name;
	private Gender gender;
	private int age;
	
	///////////////////////////////////////////////////////////
	
	public String getName() {
		return name;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	///////////////////////////////////////////////////////////
	
	private User(Builder builder) {
		this.name = builder.name;
		this.gender = builder.gender;
		this.age = builder.age;
	}
	
	public static class Builder {
		private final String name;
		
		private Gender gender = Gender.M; 
		private int age = 1;
		
		public Builder(String name) {
			this.name = name;
		}
		
		public Builder gender(Gender gender) {
			this.gender = gender;
			return this;
		}
		
		public Builder age(int age) {
			this.age = age;
			return this;
		}
		
		public User build() {
            return new User(this);
        }
	}
	
	///////////////////////////////////////////////////////////
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("=====================\n");
		sb.append("Name   : ").append(this.name).append("\n");
		sb.append("Gender : ").append(this.gender.name()).append("\n");
		sb.append("Age    : ").append(this.age).append("\n");
		
		return sb.toString();
	}
}
