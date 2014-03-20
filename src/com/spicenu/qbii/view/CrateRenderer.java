package com.spicenu.qbii.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

	private static final int WIDTH = Gdx.graphics.getWidth();
	private static final int HEIGHT = Gdx.graphics.getHeight();
	private static final float CAMERA_WIDTH = 15f;
	private static final float CAMERA_HEIGHT = 9f;
	private static final float RUNNING_FRAME_DURATION = 0.06f;
	
	private Crate crate;
	private OrthographicCamera cam;
	
	/** for debug rendering **/
	private ShapeRenderer debugRenderer = new ShapeRenderer();
	public static boolean debug = true;
	
	/** Textures **/
	private AssetManager manager;
	private TextureAtlas atlas;
	private TextureRegion trJo;
	private TextureRegion trWallOpaque, trWallClear, trWallFrame;
	private TextureRegion trLevelA;
	private BitmapFont font;
	
	private SpriteBatch spriteBatch;
	private boolean texturesInitialized = false;
	
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
		
		font = new BitmapFont();
		spriteBatch = new SpriteBatch();
		loadTextures();
	}
	
	public void render() {
		if (manager.update()) {
			// All assets loaded so far, don't need to do anything further
//			atlas = new TextureAtlas(Gdx.files.internal("atlas/textures.atlas"));
//			trJo = atlas.findRegion("jo-right");
//			trWallOpaque = atlas.findRegion("wall-op");
//			trWallClear = atlas.findRegion("wall-cl");
//			trLevelA = atlas.findRegion("level-a");
		}
		
		spriteBatch.begin();
		if (manager.isLoaded("atlas/textures.atlas") && !texturesInitialized) {
//			atlas = new TextureAtlas(Gdx.files.internal("atlas/textures.atlas"));
			atlas = manager.get("atlas/textures.atlas");
			trJo = atlas.findRegion("jo-right");
			trWallOpaque = atlas.findRegion("wall-op");
			trWallClear = atlas.findRegion("wall-cl");
			trLevelA = atlas.findRegion("level-a");
			texturesInitialized = true;
		}
		if (texturesInitialized) {
			drawLevel();
			drawJo();
			drawWalls();
		}
		font.draw(spriteBatch, "fps: " + Gdx.graphics.getFramesPerSecond(), 26, 40);
		spriteBatch.end();
		if (debug)
			drawDebug();
	}
	
	private void loadTextures() {
		manager = new AssetManager();
		Resolution[] resolutions = {new Resolution(480, 320, "480x320"), new Resolution(960, 640, "960x640")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
		manager.load("atlas/textures.atlas", TextureAtlas.class);
		
//		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/textures.atlas"));
//		trJo = atlas.findRegion("jo-right");
//		trWallOpaque = atlas.findRegion("wall-op");
//		trWallClear = atlas.findRegion("wall-cl");
//		trLevelA = atlas.findRegion("level-a");
	}
	
	private void drawLevel() {
		spriteBatch.draw(trLevelA, 0, 0, WIDTH, HEIGHT);
	}
	
	private void drawJo() {
		Jo jo = crate.getJo();
		spriteBatch.draw(trJo, jo.getPosition().x * ppuX, jo.getPosition().y * ppuY, Jo.SIZE * ppuX, Jo.SIZE * ppuY);
		font.draw(spriteBatch, "Jo x: " + jo.getPosition().x, 26, 55);
		font.draw(spriteBatch, "Jo y: " + jo.getPosition().y, 26, 70);
	}
	
	private void drawWalls() {
		for (Wall w : crate.getWalls()) {
			trWallFrame = (w.getState() == Wall.State.OPAQUE) ? trWallOpaque : trWallClear;
			spriteBatch.draw(trWallFrame, w.getPosition().x * ppuX, w.getPosition().y * ppuY, w.getWidth() * ppuX, w.getHeight() * ppuY);
		}
	}
	
	private void drawDebug() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		
		// render Jo
		Jo jo = crate.getJo();
		Rectangle rect = jo.getBounds();
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		
		// render Walls
		for (Wall w : crate.getWalls()) {
			rect = w.getBounds();
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		}
		
		debugRenderer.end();
	}
}
