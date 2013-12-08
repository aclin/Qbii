package com.spicenu.qbii.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class Crate {
	
	private Jo jo;
	private Wall wall;
	private List<Wall> walls = new ArrayList<Wall>();
	
	public Crate() {
		createDemo();
	}
	
	private void createDemo() {
		this.jo = new Jo(new Vector2(0, 3));
		this.wall = new Wall(new Vector2(8, 2.75f), Wall.State.OPAQUE);
		walls.add(new Wall(new Vector2(3, 2.75f), Wall.State.OPAQUE));
		walls.add(new Wall(new Vector2(6, 2.75f), Wall.State.CLEAR));
		walls.add(new Wall(new Vector2(9, 2.75f), Wall.State.OPAQUE));
	}
	
	public Jo getJo() {
		return jo;
	}
	
	public List<Wall> getWalls() {
		return walls;
	}
}
