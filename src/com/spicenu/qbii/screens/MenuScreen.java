package com.spicenu.qbii.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.spicenu.qbii.Qbii;

public class MenuScreen implements Screen {
	
	private static final boolean DEBUG = true;
	private static final int WIDTH = Gdx.graphics.getWidth();
	private static final int HEIGHT = Gdx.graphics.getHeight();
	private Qbii qbii;
	
	private AssetManager manager;
	Stage stage;
	Skin skin;
	SpriteBatch spriteBatch;
	BitmapFont font;
	
	TextureAtlas atlas;
	TextureRegion titleBGTextureRegion;
//	TextureRegion trStartUp, trStartDown;
//	TextureRegion trOptionsUp, trOptionsDown;
	TextureRegion trBigUp, trBigDown;
	TextureRegion trSmallUp, trSmallDown;
	
	public MenuScreen(Qbii g) {
		qbii = g;
		
		stage = new Stage(WIDTH, HEIGHT, false);
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		Resolution[] resolutions = {new Resolution(480, 320, "480x320"), new Resolution(960, 640, "960x640")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		
		manager = new AssetManager();
		manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
		manager.load("atlas/textures.atlas", TextureAtlas.class);
		manager.finishLoading();
		Gdx.app.log("MenuScreen", "Constructor");
		font = new BitmapFont();
		
//		atlas = new TextureAtlas(Gdx.files.internal("atlas/textures.atlas"));
		atlas = manager.get("atlas/textures.atlas", TextureAtlas.class);
//		titleBGTextureRegion = atlas.findRegion("title-bg");
//		trStartUp = atlas.findRegion("btnStart-up");
//		trStartDown = atlas.findRegion("btnStart-down");
//		trOptionsUp = atlas.findRegion("btnOptions-up");
//		trOptionsDown = atlas.findRegion("btnOptions-down");
		trBigUp = atlas.findRegion("btnBig-up");
		trBigDown = atlas.findRegion("btnBig-down");
		trSmallUp = atlas.findRegion("btnSmall-up");
		trSmallDown = atlas.findRegion("btnSmall-down");
	}
	
	private void drawTable() {
		// create labels in the table
		Label title = new Label("QBII", skin);
//		ImageButtonStyle btnStartStyle = new ImageButtonStyle();
//		ImageButtonStyle btnOptionsStyle = new ImageButtonStyle();
//		btnStartStyle.imageUp = new TextureRegionDrawable(trStartUp);
//		btnStartStyle.imageDown = new TextureRegionDrawable(trStartDown);
//		btnOptionsStyle.imageUp = new TextureRegionDrawable(trOptionsUp);
//		btnOptionsStyle.imageDown = new TextureRegionDrawable(trOptionsDown);
//		Button btnStart = new ImageButton(btnStartStyle);
//		Button btnOptions = new ImageButton(btnOptionsStyle);
		ImageButtonStyle btnBigStyle = new ImageButtonStyle();
		ImageButtonStyle btnSmallStyle = new ImageButtonStyle();
		btnBigStyle.imageUp = new TextureRegionDrawable(trBigUp);
		btnBigStyle.imageDown = new TextureRegionDrawable(trBigDown);
		btnSmallStyle.imageUp = new TextureRegionDrawable(trSmallUp);
		btnSmallStyle.imageDown = new TextureRegionDrawable(trSmallDown);
		
		Button btnBig = new ImageButton(btnBigStyle);
		Button btnSmall = new ImageButton(btnSmallStyle);
		
		btnBig.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("QBII", "Touch down BIG button");
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.log("QBII", "Touch up BIG button");
				qbii.setScreen(new CrateScreen());
			}
		});
		
		// create the table actor
		Table table = new Table();
		if (DEBUG)
			table.debug();
		table.setPosition(0, 0);
		table.setWidth(WIDTH);
		table.setHeight(HEIGHT);
		table.center();
		table.add(title).padBottom(10);
		table.row();
//		table.add(btnStart).padBottom(10);
		table.add(btnBig).padBottom(10);
		table.row();
//		table.add(btnOptions);
		table.add(btnSmall);
		
		// add the table to the stage and retrieve its layout
//		table.layout();
		stage.addActor(table);
	}
	
	@Override
	public void render(float delta) {
//		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (manager.update()) {
			Gdx.app.log("AssetManager", "Asset manager finished updating");
		} else {
			Gdx.app.log("AssetManager", "Asset manager still updating...");
		}
		
		spriteBatch.begin();
		spriteBatch.draw(titleBGTextureRegion, 0, 0, WIDTH, HEIGHT);
		font.draw(spriteBatch, "fps:"+Gdx.graphics.getFramesPerSecond(), 26, 40);
        spriteBatch.end();
		
		stage.act(delta);
		stage.draw();
		if (DEBUG)
			Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		if (manager.isLoaded("atlas/textures.atlas")) {
			Gdx.app.log("AssetManager", "textures.atlas is loaded");
			atlas = manager.get("atlas/textures.atlas", TextureAtlas.class);
			titleBGTextureRegion = atlas.findRegion("title-bg");
			
			drawTable();
			if (DEBUG)
				Gdx.app.log("Dimensions", "H:" + HEIGHT + " W:" + WIDTH);
		} else {
			Gdx.app.log("AssetManager", "textures.atlas is not loaded");
		}
//		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("atlas/textures.pack"));
		
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
		stage.dispose();
		skin.dispose();
	}

}
