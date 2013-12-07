package com.spicenu.qbii.controller;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Jo;
import com.spicenu.qbii.model.Jo.State;

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
		
		// To move Jo, we move Jo's bounding rectangle at each frame
		// Obtain the rectangle from the pool instead of instantiating it
		Rectangle joRect = rectPool.obtain();
		// set the rectangle to Jo's bounding box
		joRect.set(jo.getBounds().x, jo.getBounds().y, jo.getBounds().width, jo.getBounds().height);
		
		joRect.x += jo.getVelocity().x;
		
		jo.getPosition().add(jo.getVelocity());
		jo.getBounds().x = jo.getPosition().x;
		jo.getVelocity().scl(1 / delta);
	}
}
