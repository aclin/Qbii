package com.spicenu.qbii.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Jo;

public class CrateRenderer {
	
	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;
	private static final float RUNNING_FRAME_DURATION = 0.06f;
	
	private Crate crate;
	private OrthographicCamera cam;
	
	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	/** Textures **/
	private TextureRegion trJo;
	
	private SpriteBatch spriteBatch;
	private boolean debug = false;
	
	private int width;
	private int height;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis
	
	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = width / CAMERA_WIDTH;
		ppuY = height / CAMERA_HEIGHT;
	}
	
	public CrateRenderer(Crate c) {
		this.crate = c;
		cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);			// Set camera height
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);	// Set camera position in the middle
		this.cam.update();
		
		spriteBatch = new SpriteBatch();
		loadTextures();
	}
	
	public void render() {
		spriteBatch.begin();
		drawJo();
		spriteBatch.end();
	}
	
	private void loadTextures() {
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.pack"));
		trJo = atlas.findRegion("jo-down");
	}
	
	private void drawJo() {
		Jo jo = crate.getJo();
		spriteBatch.draw(trJo, jo.getPosition().x * ppuX, jo.getPosition().y * ppuY, Jo.SIZE * ppuX, Jo.SIZE * ppuY);
	}
}
