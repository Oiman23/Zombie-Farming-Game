package com.mygdx.ZombieFarm;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Shop extends Entities {
	ArrayList<Entities> items;

	public Shop(float x, float y, Texture image) {
		super(x, y, 0, image);
		items = new ArrayList<>();
	}

	public void showWares() {
		if (items.size() == 0) {
			System.out.println("shop is empty");
		}
		for (int i = 0; i < items.size(); i++) {
			System.out.println("Planted_zombie");
		}
	}

}
