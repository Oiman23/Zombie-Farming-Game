package com.mygdx.ZombieFarm;

import com.badlogic.gdx.graphics.Texture;

public class Planted_Zombie extends Entities {
	final int coinValue;
	final int energyValue;
	boolean harvestable;

	public Planted_Zombie(float x, float y, int speed) {
		super(x, y, speed, new Texture("Planted_Zombie.png"), 5);
		coinValue = 10;
		energyValue = 50;
		harvestable = false;
	}

	public boolean isReady() {
		harvestable = getTime() >= timerDone;
		return harvestable;
	}

	public int lengthRemaining() {
		if (!isReady()) {
			return timerDone - getTime();
		}

		return 0;
	}
}
