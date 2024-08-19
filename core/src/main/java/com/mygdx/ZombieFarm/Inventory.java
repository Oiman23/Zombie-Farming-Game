package com.mygdx.ZombieFarm;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Inventory extends Entities {
	ArrayList<Entities> items;
	int invCooldown;
	boolean open;
	int space;

	public Inventory(Texture image) {
		super(0, 0, 0, image);
		space = 25; // div by 5
		items = new ArrayList<>();
		open = false;
		invCooldown = 2;
	}

	public void restartCooldown() {
		length = getTime() + invCooldown;

	}

	public boolean addItem(Entities e) {
		if (items.add(e)) {
			return true;
		}
		return false;
	}

	public boolean removeItem(Entities e) {
		if (items.remove(e)) {
			return true;
		}
		return false;
	}

}
