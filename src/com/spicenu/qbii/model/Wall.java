package com.spicenu.qbii.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Wall {
	
	public static final float SIZE = 1f;
	
	private Vector2 position = new Vector2();
	
	private Rectangle bounds = new Rectangle();
	private State state;
	
	public enum State {
		OPAQUE, CLEAR;
	}
	
	public Wall(Vector2 pos, State s) {
		this.position = pos;
		this.state = s;
		this.bounds.setX(position.x);
        this.bounds.setY(position.y);
        this.bounds.setHeight(SIZE);
        this.bounds.setWidth(SIZE);
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
