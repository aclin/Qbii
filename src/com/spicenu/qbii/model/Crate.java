package com.spicenu.qbii.model;

import com.badlogic.gdx.math.Vector2;

public class Crate {
	
	private Jo jo;
	
	public Crate() {
		createDemo();
	}
	
	private void createDemo() {
		this.jo = new Jo(new Vector2(5, 3));
	}
	
	public Jo getJo() {
		return jo;
	}
}
