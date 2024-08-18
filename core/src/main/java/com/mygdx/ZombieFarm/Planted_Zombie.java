package com.mygdx.ZombieFarm;

import com.badlogic.gdx.graphics.Texture;

public class Planted_Zombie extends Entities {
	final int coinValue = 10;
	final int energyValue = 50;

	int harvestTimer = 5;
	boolean harvestable;

	public Planted_Zombie(float x, float y, int speed) {
		super(x, y, speed, new Texture("libgdx.png"));
		harvestable = false;

	}

	public boolean isReady() {
		harvestable = getTime() >= harvestTimer;
		return harvestable;
	}
}
