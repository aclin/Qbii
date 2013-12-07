package com.spicenu.qbii.controller;

import java.util.HashMap;
import java.util.Map;

import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Wall;

public class WallController {
	
	private enum Keys {
		FLIP;
	}
	
	private Crate crate;
	private Wall walls;
	
	static Map<Keys, Boolean> keys = new HashMap<WallController.Keys, Boolean>();
	static {
		keys.put(Keys.FLIP, false);
	}
	
	public WallController(Crate c) {
		this.crate = c;
		this.walls = c.getWalls();
	}
	
	public void update(float delta) {
		processInput();
		
		walls.update(delta);
	}
	
	private void processInput() {
		if (keys.get(Keys.FLIP)) {
			walls.flipState();
		}
		keys.get(keys.put(Keys.FLIP, false));
	}
	
	/* Key presses and touches */
	
	public void flipPressed() {
		keys.get(keys.put(Keys.FLIP, true));
	}
}
