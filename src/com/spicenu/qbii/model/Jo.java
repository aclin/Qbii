package com.spicenu.qbii.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Jo {
	
	public static final float SPEED = 2.5f;
	public static final float SPEED_INC = 0.5f;
	public static final float SIZE = 1f;
	
	private Vector2 position, initialPosition;
	private Vector2 velocity = new Vector2(SPEED, 0);
	private Vector2 acceleration = new Vector2();
	
	private Rectangle bounds;
	private State state;
	
	private float stateTime = 0;
	
	public enum State {
		FALLING, PASS, DEAD;
	}
	
	public Jo() {
		this.state = State.FALLING;
	}
	
	public Jo(Vector2 pos) {
		this.position = new Vector2(pos);
		this.initialPosition = new Vector2(pos);
		this.bounds = new Rectangle(pos.x, pos.y, SIZE, SIZE);
        this.state = State.FALLING;
	}
	
	public void update(float delta) {
		stateTime += delta;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(Vector2 newPos) {
		this.position = newPos;
	}
	
	public void setPosition(float x, float y) {
		this.position = new Vector2(x, y);
	}
	
	public void setInitialPosition(Vector2 newPos) {
		this.initialPosition = newPos;
	}
	
	public void setInitialPosition(float x, float y) {
		this.initialPosition = new Vector2(x, y);
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
	
	public void setBounds(Rectangle newBounds) {
		this.bounds = newBounds;
	}
	
	public void setBounds(float x, float y) {
		this.bounds = new Rectangle(x, y, SIZE, SIZE);
	}
	
	public void setState(State s) {
		this.state = s;
	}
	
	public State getState() {
		return state;
	}
	
	public float getStateTime() {
		return stateTime;
	}
	
	public void resetPosition() {
		this.position = new Vector2(initialPosition);
		this.bounds = new Rectangle(initialPosition.x, initialPosition.y, SIZE, SIZE);
	}
}
