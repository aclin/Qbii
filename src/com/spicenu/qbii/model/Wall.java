package com.spicenu.qbii.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Wall {
	
	public static final float SIZE = 1f;
	
	private Vector2 position = new Vector2();
	private float height;
	private float width;
	
	private Rectangle bounds = new Rectangle();
	private State state;
	
	public enum State {
		OPAQUE, CLEAR;
	}
	
	public Wall(Vector2 pos, float w, float h, State s) {
		this.position = pos;
		this.state = s;
		this.bounds.setX(position.x);
        this.bounds.setY(position.y);
        this.bounds.setHeight(h);
        this.bounds.setWidth(w);
        this.height = h;
        this.width = w;
	}
	
	public void update(float delta) {
		
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public Wall.State getState() {
		return state;
	}
	
	public void setState(State s) {
		this.state = s;
	}
	
	public void flipState() {
		if (state == State.OPAQUE)
			state = State.CLEAR;
		else
			state = State.OPAQUE;
	}
}
