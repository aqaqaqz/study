package dto.entity;

public enum Gender {
	M, F;
	
	public static Gender getRandomGender() {
		int r = (int)(Math.random() * 2);
		return (r==0?Gender.M:Gender.F);
	}
}
