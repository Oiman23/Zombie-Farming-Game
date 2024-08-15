package com.mygdx.ZombieFarm;

import com.badlogic.gdx.graphics.Texture;

public class Player{
	Texture image;
	float x;
	float y;
	int speed;
	public Player (int x, int y, int speed, Texture image) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.image = image;
	}
}
