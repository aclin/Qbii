package com.spicenu.qbii;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spicenu.qbii.screens.SplashScreen;

public class Qbii extends Game {
	
	public SpriteBatch spriteBatch;
	
	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		setScreen(new SplashScreen(this));
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		spriteBatch.dispose();
	}
}
