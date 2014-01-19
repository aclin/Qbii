package com.spicenu.qbii.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
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
	
	private static final boolean DEBUG = false;
	private static final int WIDTH = Gdx.graphics.getWidth();
	private static final int HEIGHT = Gdx.graphics.getHeight();
	private Qbii qbii;
	
	Stage stage;
	Skin skin;
	SpriteBatch spriteBatch;
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
		
		atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.pack"));
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
		
		spriteBatch.begin();
        spriteBatch.draw(titleBGTextureRegion, 0, 0);
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
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.pack"));
		titleBGTextureRegion = atlas.findRegion("title-bg-s");
		
		drawTable();
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
