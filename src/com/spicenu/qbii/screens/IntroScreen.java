package com.spicenu.qbii.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.spicenu.qbii.Qbii;

public class IntroScreen implements Screen {

	
	private Qbii qbii;
	
	private AssetManager manager;
	private Stage stage;
	private Skin skin;
	private SpriteBatch spriteBatch;
	private TextureAtlas atlas;
	
	public IntroScreen(Qbii g) {
		qbii = g;
		
		stage = new Stage();
		skin = new Skin();
		spriteBatch = new SpriteBatch();
		
		Resolution[] resolutions = {new Resolution(480, 320, "480x320"), new Resolution(960, 640, "960x640")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		
		manager = new AssetManager();
		manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
		
		manager.load("atlas/textures.atlas", TextureAtlas.class);
		manager.finishLoading();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("resize width: " + width + " height: " + height);
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		atlas = manager.get("atlas/textures.atlas", TextureAtlas.class);
		
		Image[] intro = new Image[16];
		for (int i = 1; i <= 16; i++) {
			intro[i - 1] = new Image(new TextureRegion(atlas.findRegion("intro-" + i)));
		}
		float delay = 2f;
		for (int i = 0; i < 15; i++) {
			intro[i].setPosition(intro[i].getWidth(), 0f);
			intro[i].addAction(sequence(delay(delay * i),
										moveTo(0f, 0f)));
			stage.addActor(intro[i]);
		}
		intro[15].setPosition(intro[15].getWidth(), 0f);
		intro[15].addAction(sequence(delay(delay * 15),
									moveTo(0f, 0f),
									delay(2f),
									run(new Runnable() {
											public void run() {
												qbii.runCrateScreen();
												dispose();
											}
										}
									)));
		stage.addActor(intro[15]);
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
		manager.dispose();
		stage.dispose();
		skin.dispose();
		spriteBatch.dispose();
	}

}
