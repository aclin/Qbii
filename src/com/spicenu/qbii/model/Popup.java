package com.spicenu.qbii.model;

import com.badlogic.gdx.math.Vector2;

public class Popup {
	
	private Vector2 position;
	private State state;
	private float stateTime;
	
	public enum State {
		IDLE, RUNNING;
	}
	
	public Popup(Vector2 pos) {
		this.position = new Vector2(pos);
		this.state = State.IDLE;
		this.stateTime = 0;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State s) {
		this.state = s;
	}
}
