package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.xml.soap.Text;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	BackGround bg;
	Bird bird;
	Obsticles obsticles;
	boolean gameOver;
	Texture restartTexture;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new BackGround();
		bird = new Bird();
		obsticles = new Obsticles();
		gameOver = false;
		restartTexture = new Texture("RestartBtn.png");
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg.render(batch);
		obsticles.render(batch);
		if (!gameOver){
			bird.render(batch);
		}else{
			batch.draw(restartTexture, 200, 200);
		}
		batch.end();
	}

	public void update(){
		bg.update();
		bird.update();
		obsticles.update();
		for (int i = 0; i < Obsticles.obs.length; i++){
			if (bird.position.x > Obsticles.obs[i].position.x && bird.position.x < Obsticles.obs[i].position.x + 50){
				if(Obsticles.obs[i].emptySpace.contains(bird.position)){
					gameOver = true;
				}
			}
		}
		if (bird.position.y < 0 || bird.position.y > 600){
			gameOver = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver){
			recreate();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	public void recreate(){
		bird.recreate();
		obsticles.recreate();
		gameOver = false;
	}
}
