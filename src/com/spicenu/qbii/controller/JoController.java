package com.spicenu.qbii.controller;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Jo;
import com.spicenu.qbii.model.Teleporter;
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
		
		// Check if Jo entered an teleporter
		checkEnterTeleporter();
		
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
		
		// Shift Jo over by 1 unit of velocity
		joRect.x += jo.getVelocity().x;
		
		// Reset Jo's position if he collides with an opaque wall
		for (Wall w : crate.getWalls()) {
			if (joRect.overlaps(w.getBounds())) {
				if (w.getState() == Wall.State.OPAQUE || w.getState() == Wall.State.PERSISTENT) {
					jo.setState(Jo.State.DEAD);
				}
			}
		}
		
		// Free this rectangle back to the pool
		rectPool.free(joRect);
	}
	
	public void checkEnterTeleporter() {
		// Obtain a rectangle from pool instead of instantiating
		Rectangle joRect = rectPool.obtain();
		// set the rectangle to Jo's bounding box
		joRect.set(jo.getBounds().x, jo.getBounds().y, jo.getBounds().width, jo.getBounds().height);
		
		// Shift Jo over by 1 unit of velocity
		joRect.x += jo.getVelocity().x;
		
		// Move Jo to the position of the teleporter's exit if the teleporter's SWITCH is ON
		for (Teleporter t : crate.getTeleporters()) {
			if (t.getState() == Teleporter.State.ON) {
				if (joRect.overlaps(t.getEntranceBounds())) {
					Vector2 ev = t.getExitPosition();
					jo.setPosition(ev);
					jo.setBounds(ev.x, ev.y);
				}
			}
		}
		
		// Free rectangle back to the pool
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
