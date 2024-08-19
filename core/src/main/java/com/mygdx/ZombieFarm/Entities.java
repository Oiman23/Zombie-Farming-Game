package com.mygdx.ZombieFarm;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Entities {
	int timer, length;
	boolean timerDone;
	Texture image;
	float x, y;
	float xHB, yHB, wHB, hHB;
	int speed;

	public Entities(float x, float y, int speed, Texture image) {
		timer = 0;
		length = 0;
		timerDone = false;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.image = image;
		this.xHB = x;
		this.yHB = y;
		this.wHB = image.getWidth();
		this.hHB = image.getHeight();
	}

	public Entities(float x, float y, int speed, Texture image, float xHB, float yHB, float wHB, float hHB) {
		timer = 0;
		length = 0;
		timerDone = false;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.image = image;
		this.xHB = xHB;
		this.yHB = yHB;
		this.wHB = wHB;
		this.hHB = hHB;
	}

	public void batchDraw(SpriteBatch batch) {
		batch.draw(image, x, y);
	}

	public int getTime() {
		timer = (int) (System.currentTimeMillis() / 1000);
		return timer;
	}

	public boolean hitboxCheck(Entities e) {
		// Bot Left x,y
		// Bot right x + image.width, y
		// Top Left x, y + image.height
		// Top Right x + image.width, y + image.height
		// first entity yHB
		boolean checkOne = hitboxCornerCheck(xHB, yHB, e) || hitboxCornerCheck(xHB + wHB, yHB, e)
				|| hitboxCornerCheck(xHB, yHB + hHB, e) || hitboxCornerCheck(xHB + wHB, yHB + hHB, e);
		if (checkOne) {
			return true;
		}
		// second entity
		boolean checkTwo = hitboxCornerCheck(e.xHB, e.yHB, this) || hitboxCornerCheck(e.xHB + e.wHB, e.yHB, this)
				|| hitboxCornerCheck(e.xHB, e.yHB + e.hHB, this)
				|| hitboxCornerCheck(e.xHB + e.wHB, e.yHB + e.hHB, this);
		return checkTwo;
	}

	public boolean hitboxCornerCheck(float xC, float yC, Entities e) {
		boolean check = false;
		if (e.xHB <= xC && e.xHB + e.wHB >= xC && e.yHB <= yC && e.yHB + e.hHB >= yC) {
			check = true;

		}
		return check;
	}

	public void updatePosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void updateHitboxPosition(float xHB, float yHB) {
		this.xHB = xHB;
		this.yHB = yHB;
	}

	public void updateHitboxPosition(float xHB, float yHB, float wHB, float hHB) {

		this.xHB = xHB;
		this.yHB = yHB;
		this.wHB = wHB;
		this.hHB = hHB;
	}
}
