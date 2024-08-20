package com.mygdx.ZombieFarm;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Inventory extends Entities {
//	ArrayList<ArrayList<Entities>> storage;
	ArrayList<Planted_Zombie> pZList;
	boolean open;
	int space;
	float firstSquareX;
	float firstSquareY;

	public Inventory(float x, float y) {
		super(x, y, 0, new Texture("Inventory.png"), 1);
		space = 25; // div by 5
		firstSquareX = 0;
		firstSquareY = 0;
		pZList = new ArrayList<>();
		open = false;
	}

	public boolean addItem(Entities e) {
		if ((e instanceof Planted_Zombie) && pZList.add((Planted_Zombie) e)) {
			return true;
		}
		return false;
	}

	public boolean removeItem(Entities e) {
		if ((e instanceof Planted_Zombie) && pZList.remove(e)) {
			return true;
		}
		return false;
	}

	public void inventoryOpen(SpriteBatch batch, float x, float y) {
		updatePosition(x - 700, y - 700 / 2);
		firstSquareX = this.x + 20;
		firstSquareY = this.y + 550;
		batchDraw(batch);
		for (int i = 0; i < pZList.size(); i++) {
			Planted_Zombie pZTemp = pZList.get(i);
			pZTemp.updatePosition(firstSquareX, firstSquareY);
			pZTemp.batchDraw(batch);
		}
	}

}
