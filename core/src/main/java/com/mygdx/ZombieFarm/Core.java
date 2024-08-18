package com.mygdx.ZombieFarm;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class Core extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	BitmapFont font;

	Player player;
	Planted_Zombie pZombie;
	ArrayList<Planted_Zombie> pZombieList;
	Texture farmbackground;

	// 2000player.xPos 1300
	@Override
	public void create() {
		// Sprites
		batch = new SpriteBatch();

		player = new Player(100, 200, 100, new Texture("Happystar.png")); // xPos,player.yPosPos, speed
//		pZombie = new Planted_Zombie(0, 0, 0);
		pZombieList = new ArrayList<>();
		farmbackground = new Texture("FarmGrass.png");

		// World Setting
		camera = new OrthographicCamera(player.x, player.y);
		camera.setToOrtho(false);
		font = new BitmapFont();
		font.getData().setScale(2);

	}

//test
	@Override
	public void render() {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

		// Player Checks
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.y = player.y + Gdx.graphics.getDeltaTime() * player.speed;
			player.updatePosition();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.x = player.x - Gdx.graphics.getDeltaTime() * player.speed;
			player.updatePosition();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.y = player.y - Gdx.graphics.getDeltaTime() * player.speed;
			player.updatePosition();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.x = player.x + Gdx.graphics.getDeltaTime() * player.speed;
			player.updatePosition();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.P) && player.plantCooldown() && player.x > 0 && player.y > 0) {
			player.restartTimer();
			pZombieList.add(new Planted_Zombie(player.x, player.y, 0));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.B) && pZombieList.size() != 0) {
			for (int i = 0; i < pZombieList.size(); i++) {
				Planted_Zombie pZ = pZombieList.get(i);
				if (pZ.hitboxCheck(player)) {
					player.addCoins(pZ.coinValue);
					player.addEnergy(pZ.energyValue);
					pZ.image.dispose();
					pZombieList.remove(i);
				}
			}
		}

		// Beging
		batch.begin();
		batch.draw(farmbackground, 0, 0);

		camera.position.set(player.x, player.y, 0);
		camera.update();

		for (int i = 0; i < pZombieList.size(); i++) {
			pZombieList.get(i).batchDraw(batch);
		}

		player.batchDraw(batch, 100, 70);

		font.draw(batch, "Coins : " + player.coins, 0, 0);
		font.draw(batch, "Energy : " + player.energy, 150, 0);
		batch.setProjectionMatrix(camera.combined);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		player.image.dispose();
		for (int i = 0; i < pZombieList.size(); i++) {
			pZombieList.get(i).image.dispose();
		}
		farmbackground.dispose();
	}

}
