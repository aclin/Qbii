package com.spicenu.qbii.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Teleporter {
	
	public static final float WIDTH = 0.5f;
	public static final float HEIGHT = 0.5f;
	public static final float RENDER_WIDTH = 1f;
	public static final float RENDER_HEIGHT = 1.5f;
	private static final float RENDER_OFFSET_X = 0.25f;
	private static final float RENDER_OFFSET_Y = 0.5f;
	
	private Vector2 position = new Vector2();
	private Rectangle bounds = new Rectangle();
	private Entrance entrance;
	private Exit exit;
	private State state;
	private State initialState;
	
	public enum State {
		ON, OFF;
	}
	
	public Teleporter(Vector2 entrancePos, Vector2 exitPos, State s) {
		this.entrance = new Entrance(entrancePos);
		this.exit = new Exit(exitPos);
		this.state = s;
		this.initialState = s;
	}
	
	public Rectangle getEntranceBounds() {
		return entrance.bounds;
	}
	
	public void setEntrancePosition(Vector2 pos) {
		this.entrance.position = pos;
		this.entrance.renderPosition = new Vector2(pos.x - RENDER_OFFSET_X, pos.y - RENDER_OFFSET_Y);
	}
	
	public Vector2 getEntrancePosition() {
		return this.entrance.position;
	}
	
	public Vector2 getEntranceRenderPosition() {
		return this.entrance.renderPosition;
	}
	
	public void setExitPosition(Vector2 pos) {
		this.exit.position = pos;
		this.exit.renderPosition = new Vector2(pos.x - RENDER_OFFSET_X, pos.y - RENDER_OFFSET_Y);
	}
	
	public Vector2 getExitPosition() {
		return this.exit.position;
	}
	
	public Vector2 getExitRenderPosition() {
		return this.exit.renderPosition;
	}
	
	public void setState(State s) {
		state = s;
	}
	
	public State getState() {
		return this.state;
	}
	
	public void resetState() {
		state = initialState;
	}
	
	public void switchState() {
		if (state == State.ON)
			state = State.OFF;
		else
			state = State.ON;
	}
	
	// Entrance portal
	class Entrance {
		Vector2 position;
		Vector2 renderPosition;
		Rectangle bounds;
		
		Entrance(Vector2 pos) {
			this.position = pos;
			this.renderPosition = new Vector2(pos.x - RENDER_OFFSET_X, pos.y - RENDER_OFFSET_Y);
			this.bounds = new Rectangle(pos.x, pos.y, WIDTH, HEIGHT);
		}
	}
	
	// Exit portal
	class Exit {
		Vector2 position;
		Vector2 renderPosition;
		Rectangle bounds;
		
		Exit(Vector2 pos) {
			this.position = pos;
			this.renderPosition = new Vector2(pos.x - RENDER_OFFSET_X, pos.y - RENDER_OFFSET_Y);
			this.bounds = new Rectangle(pos.x, pos.y, WIDTH, HEIGHT);
		}
	}
}
