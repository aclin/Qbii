package com.spicenu.qbii.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Jo {
	
	public static final float SPEED = 1.5f;
	public static final float SPEED_INC = 0.5f;
	public static final float SIZE = 0.5f;
	
	private Vector2 position = new Vector2();
	private Vector2 velocity = new Vector2(SPEED, 0);
	private Vector2 acceleration = new Vector2();
	
	private Rectangle bounds = new Rectangle();
	private State state = State.FALLING;
	
	private float stateTime = 0;
	
	public enum State {
		FALLING, DYING;
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
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public Vector2 getAcceleration() {
		return acceleration;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public State getState() {
		return state;
	}
	
	public float getStateTime() {
		return stateTime;
	}
	
	public void resetPosition() {
		this.position.x = -1;
	}
}
