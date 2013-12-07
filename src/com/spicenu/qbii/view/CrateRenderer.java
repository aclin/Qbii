package com.spicenu.qbii.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.spicenu.qbii.model.Crate;
import com.spicenu.qbii.model.Jo;
import com.spicenu.qbii.model.Wall;

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
	private TextureRegion trWallOpaque, trWallClear, trWallFrame;
	
	private SpriteBatch spriteBatch;
	private boolean debug = true;
	
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
		drawWalls();
		spriteBatch.end();
		if (debug)
			drawDebug();
	}
	
	private void loadTextures() {
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.pack"));
		trJo = atlas.findRegion("jo-right");
		trWallOpaque = atlas.findRegion("wall-op");
		trWallClear = atlas.findRegion("wall-cl");
	}
	
	private void drawJo() {
		Jo jo = crate.getJo();
		spriteBatch.draw(trJo, jo.getPosition().x * ppuX, jo.getPosition().y * ppuY, Jo.SIZE * ppuX, Jo.SIZE * ppuY);
	}
	
	private void drawWalls() {
		Wall wall = crate.getWalls();
		trWallFrame = (wall.getState() == Wall.State.OPAQUE) ? trWallOpaque : trWallClear;
		spriteBatch.draw(trWallFrame, wall.getPosition().x * ppuX, wall.getPosition().y * ppuY, Wall.SIZE * ppuX, Wall.SIZE * ppuY);
	}
	
	private void drawDebug() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		
		// render Jo
		Jo jo = crate.getJo();
		Rectangle rect = jo.getBounds();
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		
		// render Wall
		Wall wall = crate.getWalls();
		rect = wall.getBounds();
		debugRenderer.setColor(new Color(1, 0, 0, 1));
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		
		debugRenderer.end();
	}
}
