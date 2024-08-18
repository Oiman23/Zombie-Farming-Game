package com.mygdx.ZombieFarm;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Entities {
	int timer;
	int length;
	boolean timerDone;
	Texture image;
	float x;
	float y;
	int speed;
	Rectangle hitbox;

	public Entities(float x, float y, int speed, Texture image) {
		timer = 0;
		length = 0;
		timerDone = false;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.image = image;
		hitbox = new Rectangle(x, y, image.getWidth(), image.getHeight());
	}

	public void batchDraw(SpriteBatch batch) {
		batch.draw(image, x, y);
	}

	public void batchDraw(SpriteBatch batch, int sizeW, int sizeH) {
		batch.draw(image, x, y, sizeW, sizeH);
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
		// first entity
		boolean checkOne = hitboxCornerCheck(x, y, e) || hitboxCornerCheck(x + image.getWidth(), y, e)
				|| hitboxCornerCheck(x, y + image.getHeight(), e)
				|| hitboxCornerCheck(x + image.getWidth(), y + image.getHeight(), e);
		if (checkOne) {
			return true;
		}
		// second entity
		boolean checkTwo = hitboxCornerCheck(e.x, e.y, this) || hitboxCornerCheck(e.x + e.image.getWidth(), e.y, this)
				|| hitboxCornerCheck(e.x, e.y + e.image.getHeight(), this)
				|| hitboxCornerCheck(e.x + e.image.getWidth(), e.y + e.image.getHeight(), this);
		return checkTwo;
	}

	public boolean hitboxCornerCheck(float xC, float yC, Entities e) {
		boolean check = false;
		if (e.x <= xC && e.x + e.image.getWidth() >= xC && e.y <= yC && e.y + e.image.getHeight() >= yC) {
			check = true;
		}
		return check;
	}

	public void updatePosition() {
		hitbox.setPosition(x, y);
	}
}
