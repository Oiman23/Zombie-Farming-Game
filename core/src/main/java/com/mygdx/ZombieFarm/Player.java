package com.mygdx.ZombieFarm;

import com.badlogic.gdx.graphics.Texture;

public class Player extends Entities {
	final int planted_zombieTimer = 2;
	int coins, energy;
	boolean plantCooldown;
	Inventory inventory;

	public Player(float x, float y, int speed) {
		super(x, y, speed, new Texture("Happystar.png"), 0);
		coins = 0;
		energy = 0;
		plantCooldown = true;
		this.inventory = new Inventory(0, 0);
	}

	public boolean plantCooldown() {
		if (getTime() >= length) {
			plantCooldown = true;
		}
		return plantCooldown;
	}

	public void restartTimer() {
		if (plantCooldown) {
			length = planted_zombieTimer + getTime();
			plantCooldown = false;
		}
	}

	public void addCoins(int added) {
		coins = coins + added;
	}

	public void addEnergy(int added) {
		energy = energy + added;
	}
}
