package com.mygdx.ZombieFarm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Core extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture playerImage;
	Player player;
	// 2000player.xPos 1300
	@Override
	public void create() {
		//Sprites
		batch = new SpriteBatch();
		playerImage = new Texture("Happystar.png");
		player = new Player(100, 200, 100, playerImage); //xPos,player.yPosPos, speed
		
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
		
		//Player Checks
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.y =player.y + Gdx.graphics.getDeltaTime() * player.speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.x =player.x - Gdx.graphics.getDeltaTime() * player.speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.y =player.y - Gdx.graphics.getDeltaTime() * player.speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.x =player.x + Gdx.graphics.getDeltaTime() * player.speed;
		}
		
		//Beging 
		batch.begin();
		batch.draw(player.image, player.x, player.y, 100, 70);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.image.dispose();
	}
}
