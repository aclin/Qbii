package com.spicenu.qbii.controller;

import com.spicenu.qbii.Qbii;
import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Jo;

public class JoController {
	
	private Crate crate;
	private Jo jo;
	
	enum Keys {
		UP, DOWN, LEFT, RIGHT;
	}
	
	public JoController(Crate c) {
		this.crate = c;
		this.jo = c.getJo();
	}
}
