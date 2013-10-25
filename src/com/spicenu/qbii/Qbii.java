package com.spicenu.qbii;

import com.spicenu.qbii.screens.SplashScreen;
import com.badlogic.gdx.Game;

public class Qbii extends Game {
	@Override
	public void create() {
		setScreen(new SplashScreen(this));
	}
}
