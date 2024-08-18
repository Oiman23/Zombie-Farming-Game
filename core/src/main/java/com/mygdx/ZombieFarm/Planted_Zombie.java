package com.mygdx.ZombieFarm;

import com.badlogic.gdx.graphics.Texture;

public class Planted_Zombie extends Entities {
	final int coinValue = 10;
	final int energyValue = 50;

	int harvestTimer = 5;
	int length = 0;
	boolean harvestable;

	public Planted_Zombie(float x, float y, int speed) {
		super(x, y, speed, new Texture("libgdx.png"));
		harvestable = false;
		length = getTime() + harvestTimer;
	}

	public boolean isReady() {
		harvestable = getTime() >= length;
		return harvestable;
	}

	public int lengthRemaining() {
		if (!isReady()) {
			return length - getTime();
		}

		return 0;
	}
}
