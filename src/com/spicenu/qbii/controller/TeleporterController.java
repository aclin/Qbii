package com.spicenu.qbii.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Teleporter;

public class TeleporterController {
	
	private enum Keys {
		SWITCH;
	}
	
	private Crate crate;
	private List<Teleporter> teleporters;
	
	public static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
	static {
		keys.put(Keys.SWITCH, false);
	}
	
	public TeleporterController(Crate c) {
		this.crate = c;
		this.teleporters = c.getTeleporters();
	}
	
	public void update(float delta) {
		processInput();
	}
	
	private void processInput() {
		if (keys.get(Keys.SWITCH)) {
			for (Teleporter t : teleporters)
				t.switchState();
		}
		keys.get(keys.put(Keys.SWITCH, false));
	}
	
	/* Key presses and touches */
	public void switchPressed() {
		keys.get(keys.put(Keys.SWITCH, true));
	}
}
