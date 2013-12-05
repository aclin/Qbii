package com.spicenu.qbii.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Jo {
	
	public static final float SPEED = 2f;
	public static final float SIZE = 0.5f;
	
	private Vector2 position = new Vector2();
	private Vector2 velocity = new Vector2();
	
	private Rectangle bounds = new Rectangle();
	private State state = State.IDLE;
	private boolean faceDown = true;
	
	private float stateTime = 0;
	
	public enum State {
		IDLE, WALKING;
	}
	
	public Jo(Vector2 pos) {
		this.position = pos;
		this.bounds.setX(position.x);
        this.bounds.setY(position.y);
        this.bounds.setHeight(SIZE);
        this.bounds.setWidth(SIZE);
	}
	
	public void update(float delta) {
		stateTime += delta;
	}
	
	public Vector2 getPosition() {
		return position;
	}
}
