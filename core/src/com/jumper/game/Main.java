package com.jumper.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {

	SpriteBatch sb;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Jumper";

	private GameStateManager gsm;

	@Override
	public void create () {
		sb = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		gsm.push(new MainMenu(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.rander(sb);
	}
	
	@Override
	public void dispose () {
		sb.dispose();

	}
}
