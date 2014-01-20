package com.spicenu.qbii.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Crate {
	
	private Jo jo;
	private Wall wall;
	private List<Wall> walls = new ArrayList<Wall>();
	
	public Crate() {
		createDemo();
		loadLevel();
	}
	
	private void createDemo() {
		this.jo = new Jo(new Vector2(-1, 5));
//		this.wall = new Wall(new Vector2(8, 4.75f), Wall.State.OPAQUE);
//		walls.add(new Wall(new Vector2(3, 4.75f), Wall.State.OPAQUE));
//		walls.add(new Wall(new Vector2(6, 4.75f), Wall.State.CLEAR));
//		walls.add(new Wall(new Vector2(9, 4.75f), Wall.State.OPAQUE));
	}
	
	private void loadLevel() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(Gdx.files.internal("levels/lvl1").reader());
			String line;
			while ((line = br.readLine()) != null) {
				Gdx.app.log("Crate", line);
				char key = line.charAt(0);
				String[] strs;
				switch (key) {
				case 'W':
					strs = line.split(" ");
					walls.add(new Wall(new Vector2(Float.parseFloat(strs[1]), Float.parseFloat(strs[2])),
										Integer.parseInt(strs[3]) == 0 ? Wall.State.CLEAR : Wall.State.OPAQUE));
					break;
				case 'T':
					break;
				case '%':
					break;
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public Jo getJo() {
		return jo;
	}
	
	public List<Wall> getWalls() {
		return walls;
	}
}
