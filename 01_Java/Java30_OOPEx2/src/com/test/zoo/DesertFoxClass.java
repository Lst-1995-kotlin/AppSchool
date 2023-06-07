package com.test.zoo;

public class DesertFoxClass extends AnimalClass{

	@Override
	public void howEat() {
		System.out.println("손을 이용해 먹습니다.");
	}
	
	public DesertFoxClass(String name) {
		this.type = "사막여우";
		this.name = name;
		this.numberLegs = 4;
		this.lengthNose = "짧다";
		this.bodySize = "작다";
	}
	
}