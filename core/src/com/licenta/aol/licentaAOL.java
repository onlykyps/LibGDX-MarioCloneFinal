package com.licenta.aol;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.licenta.aol.Screens.PlayScreen;
import com.sun.org.apache.bcel.internal.generic.PUSH;

public class licentaAOL extends Game {
	public static final int width=400;
	public static final int height = 208;
	public static final float PPM = 100;

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render ()
	{
			super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
