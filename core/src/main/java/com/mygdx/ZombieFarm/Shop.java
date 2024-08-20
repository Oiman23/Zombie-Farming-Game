package com.mygdx.ZombieFarm;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shop extends Entities {
	Inventory inventory;

	public Shop(float x, float y) {
		super(x, y, 0, new Texture("Shop.png"), 2);
		inventory = new Inventory(0, 0);
		inventory.addItem(new Planted_Zombie(0, 0, 0));
		inventory.addItem(new Planted_Zombie(0, 0, 0));
		inventory.addItem(new Planted_Zombie(0, 0, 0));
	}

	public void showWares(SpriteBatch batch) {
		
		int pZSize = inventory.pZList.size();
		if (pZSize == 0) {
			System.out.println("shop is empty");
		} else {
			System.out.println("The Items are: ");
			for (int i = 0; i < pZSize; i++) {
				System.out.println("pZItem: " + inventory.pZList.get(i));
			}
		}
	}

}
