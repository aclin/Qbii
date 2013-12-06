package com.spicenu.qbii.model;

import com.badlogic.gdx.math.Vector2;

public class Crate {
	
	private Jo jo;
	private Wall wall;
	
	public Crate() {
		createDemo();
	}
	
	private void createDemo() {
		this.jo = new Jo(new Vector2(0, 3));
		this.wall = new Wall(new Vector2(8, 2.75f), Wall.State.OPAQUE);
	}
	
	public Jo getJo() {
		return jo;
	}
	
	public Wall getWalls() {
		return wall;
	}
}
