package com.spicenu.qbii.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Jo;
import com.spicenu.qbii.model.Wall;

public class JoController {
	
	private static final float GRAVITY = 1f;
	
	private Crate crate;
	private Jo jo;
	
	// This is the rectangle pool used in collision detection
	// Good to avoid instantiation each frame
	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
	
	public JoController(Crate c) {
		this.crate = c;
		this.jo = c.getJo();
	}
	
	public void update(float delta) {
		// Set Jo to fall automatically
		fall(delta);
		
		jo.update(delta);
	}
	
	public void fall(float delta) {
		// Setting initial vertical acceleration
//		jo.getAcceleration().x = GRAVITY;
		
		// Convert acceleration to frame time
//		jo.getAcceleration().scl(delta);
		
		// apply acceleration to change velocity
//		jo.getVelocity().add(jo.getAcceleration().x, 0);
		
		// Scale velocity to frame units
		jo.getVelocity().scl(delta);
		
		// Check for possible collision with wall
		checkCollisionWithWalls();
		
		// To move Jo, add current velocity to position
		jo.getPosition().add(jo.getVelocity());
		
		jo.getBounds().x = jo.getPosition().x;
		
		// Check if Jo as passed the end of the level
		checkPassLevel();
				
		jo.getVelocity().scl(1 / delta);
	}
	
	// Checks if Jo collides with a Wall
	public void checkCollisionWithWalls() {
		// Obtain the rectangle from the pool instead of instantiating it
		Rectangle joRect = rectPool.obtain();
		// set the rectangle to Jo's bounding box
		joRect.set(jo.getBounds().x, jo.getBounds().y, jo.getBounds().width, jo.getBounds().height);
		
		joRect.x += jo.getVelocity().x;
		
		// Reset Jo's position if he collides with an opaque wall
		for (Wall w : crate.getWalls()) {
			if (joRect.overlaps(w.getBounds()) && w.getState() == Wall.State.OPAQUE) {
				
				jo.setState(Jo.State.DEAD);
			}
		}
		
		// Free this rectangle back to the pool
		rectPool.free(joRect);
	}
	
	// Checks if Jo has reached the end of a level
	public void checkPassLevel() {
		if (jo.getPosition().x >= Crate.WIDTH) {
			jo.setState(Jo.State.PASS);
//			jo.resetPosition();
		}
	}
}
