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

	Inventory inventory;
	Shop shop; // 290, 940 | 600, 940 | 290, 620 | 600, 620
	Texture farmbackground;

	// 2000player.xPos 1300
	@Override
	public void create() {
		// Sprites
		batch = new SpriteBatch();

		player = new Player(100, 200, 100); // xPos,player.yPosPos, speed
		// pZombie = new Planted_Zombie(0, 0, 0);
		pZombieList = new ArrayList<>();
		farmbackground = new Texture("FarmGrass.png");
		inventory = new Inventory(0, 0);
		inventory.addItem(new Planted_Zombie(0, 0, 0));

		// Interacble Buildings
		shop = new Shop(-500, -500);

		// World Setting
		camera = new OrthographicCamera(player.x, player.y);
		camera.setToOrtho(false);
		font = new BitmapFont();

	}

//test
	@Override
	public void render() {
		ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

		// Player Checks
		if (!inventory.open && !shop.inventory.open) {
			if (Gdx.input.isKeyPressed(Input.Keys.W)) {
				player.y = player.y + Gdx.graphics.getDeltaTime() * player.speed;
				player.updatePosition(player.x, player.y);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				player.x = player.x - Gdx.graphics.getDeltaTime() * player.speed;
				player.updatePosition(player.x, player.y);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
				player.y = player.y - Gdx.graphics.getDeltaTime() * player.speed;
				player.updatePosition(player.x, player.y);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				player.x = player.x + Gdx.graphics.getDeltaTime() * player.speed;
				player.updatePosition(player.x, player.y);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.P) && inventory.pZList.size() != 0 && player.plantCooldown()
					&& player.x > 0 && player.y > 0) {
				player.restartTimer();
				Planted_Zombie pZTemp = inventory.pZList.get(0);
				inventory.removeItem(pZTemp);
				pZombieList.add(pZTemp);
				pZTemp.updatePosition(player.x, player.y);
				pZTemp.startTimer();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.B) && pZombieList.size() != 0) {
				for (int i = 0; i < pZombieList.size(); i++) {
					Planted_Zombie pZ = pZombieList.get(i);
					if (pZ.isReady() && pZ.hitboxCheck(player)) {
						player.addCoins(pZ.coinValue);
						player.addEnergy(pZ.energyValue);
						pZ.image.dispose();
						pZombieList.remove(i);
					}
				}
			}
		}

		// Beging
		batch.begin();
		batch.draw(farmbackground, 0, 0);

		camera.position.set(player.x, player.y, 0);
		camera.update();

		for (int i = 0; i < pZombieList.size(); i++) {
			Planted_Zombie pZ = pZombieList.get(i);
			pZ.batchDraw(batch);

			font.getData().setScale(1.6f);
			String timerText = (pZ.lengthRemaining() != 0) ? "Timer: " + pZ.lengthRemaining() : "Ready to Harvest!";
			font.draw(batch, timerText, pZ.x, pZ.y);
		}
		shop.batchDraw(batch);

		font.getData().setScale(1.70f);
		font.draw(batch, "Coins : " + player.coins, 0, 0);
		font.draw(batch, "Energy : " + player.energy, 150, 0);

		player.batchDraw(batch);

		if (Gdx.input.isKeyPressed(Input.Keys.E) && inventory.checkTimer() && !shop.inventory.open) {
			inventory.open = !inventory.open;
			inventory.startTimer();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.hitboxCheck(shop) && shop.checkTimer()
				&& !inventory.open) {
			shop.inventory.open = !shop.inventory.open;
			shop.startTimer();
			shop.showWares(batch);
		}
		if (inventory.open) {
			inventory.inventoryOpen(batch, player.x, player.y);
		} else if (shop.inventory.open) {
			shop.inventory.inventoryOpen(batch, player.x, player.y);
		} // test
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
		shop.image.dispose();
		font.dispose();
	}

}
