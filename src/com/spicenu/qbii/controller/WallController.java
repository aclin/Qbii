package com.spicenu.qbii.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Wall;

public class WallController {
	
	private enum Keys {
		FLIP;
	}
	
	private Crate crate;
	private List<Wall> walls;
	
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
		
		for (Wall w : walls) {
			w.update(delta);
		}
	}
	
	private void processInput() {
		if (keys.get(Keys.FLIP)) {
			for (Wall w : walls)
				w.flipState();
		}
		keys.get(keys.put(Keys.FLIP, false));
	}
	
	/* Key presses and touches */
	public void flipPressed() {
		keys.get(keys.put(Keys.FLIP, true));
	}
}
