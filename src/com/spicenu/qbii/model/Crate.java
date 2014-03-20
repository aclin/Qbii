package com.spicenu.qbii.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Crate {
	
	public static final float WIDTH = 15f;
	public static final float HEIGHT = 9f;
	
	/** World Information **/
	public static int level;
	public static State state;
	
	private Jo jo;
	private Wall wall;
	private List<Wall> walls = new ArrayList<Wall>();
	
	public enum State {
		PLAYING, TRANSITION, PAUSE, END;
	}
	
	public Crate() {
		state = State.PLAYING; 
		level = 1;
		initializeJo();
	}
	
	public void initializeJo() {
		this.jo = new Jo(new Vector2(-1, 5));
	}
	
	public void loadLevel() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(Gdx.files.internal("levels/lvl" + Integer.toString(level)).reader());
			String line;
			while ((line = br.readLine()) != null) {
				Gdx.app.log("Crate", line);
				char key = line.charAt(0);
				String[] strs;
				switch (key) {
				case 'W':
					strs = line.split(" ");
					walls.add(new Wall(new Vector2(Float.parseFloat(strs[1]), Float.parseFloat(strs[2])),
										Float.parseFloat(strs[3]),
										Float.parseFloat(strs[4]),
										Integer.parseInt(strs[5]) == 0 ? Wall.State.CLEAR : Wall.State.OPAQUE));
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
	
	public void clearLevel() {
		walls.clear();
	}
	
	public Jo getJo() {
		return jo;
	}
	
	public List<Wall> getWalls() {
		return walls;
	}
	
	public void goToNextLevel() {
		level++;
		if (level > 2)
			level = 1;
	}
}
