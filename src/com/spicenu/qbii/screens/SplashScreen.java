package com.spicenu.qbii.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spicenu.qbii.Qbii;

public class SplashScreen implements Screen {

	private static final int WIDTH = Gdx.graphics.getWidth();
	private static final int HEIGHT = Gdx.graphics.getHeight();
	
	private Qbii qbii;
	
	private AssetManager manager;
	
	private BitmapFont font;
	private SpriteBatch spriteBatch;
	private Texture splashTexture;
	private TextureRegion splashTextureRegion;
	
	private MenuScreen menuScreen;
	
	public SplashScreen(Qbii g) {
		
		Resolution[] resolutions = {new Resolution(480, 320, "480x320"), new Resolution(960, 640, "960x640")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		
		manager = new AssetManager();
		manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
		
		manager.load("atlas/textures.atlas", TextureAtlas.class);
		
		font = new BitmapFont();
		qbii = g;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (manager.update()) {
			Gdx.app.log("SplashScreen render()", "Manager updated");
			
			spriteBatch.begin();
//	        spriteBatch.draw(splashTextureRegion, 0, 0);
	        spriteBatch.draw(manager.get("atlas/textures.atlas", TextureAtlas.class).findRegion("splash"), 0, 0, WIDTH, HEIGHT);
	        font.draw(spriteBatch, "fps:"+Gdx.graphics.getFramesPerSecond(), 26, 40);
	        spriteBatch.end();
	        
	        if(Gdx.input.justTouched())
	            qbii.setScreen(new MenuScreen(qbii));
		}
		
        
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
//		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/textures.atlas"));
//		splashTextureRegion = atlas.findRegion("splash");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		menuScreen.dispose();
		manager.dispose();
		spriteBatch.dispose();
		font.dispose();
		qbii.dispose();
	}

}
