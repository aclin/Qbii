package com.spicenu.qbii.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.spicenu.qbii.controller.JoController;
import com.spicenu.qbii.controller.WallController;
import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Jo;
import com.spicenu.qbii.view.CrateRenderer;

public class CrateScreen implements Screen, InputProcessor {
	
	private Crate crate;
	private Jo jo;
	private CrateRenderer crateRenderer;
	private JoController joController;
	private WallController wallController;
	
	private int width, height;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (jo.getState() == Jo.State.PASS) {
			crate.goToNextLevel();
			crate.clearLevel();
			crate.loadLevel();
			jo.resetPosition();
			jo.setState(Jo.State.FALLING);
		}
		
		crateRenderer.render();
		joController.update(delta);
		wallController.update(delta);
	}

	@Override
	public void resize(int width, int height) {
		crateRenderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void show() {
		crate = new Crate();
		crate.loadLevel();
		jo = crate.getJo();
		joController = new JoController(crate);
		wallController = new WallController(crate);
		crateRenderer = new CrateRenderer(crate);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		wallController.flipPressed();
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
