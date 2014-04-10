package com.spicenu.qbii.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.spicenu.qbii.Qbii;
import com.spicenu.qbii.model.Crate;

public class HowToScreen implements Screen {

	private static int WIDTH;
	private static int HEIGHT;
	
	private Qbii qbii;
	private AssetManager manager;
	Stage stage;
	Skin skin;
	SpriteBatch spriteBatch;
	private TextureAtlas atlas;
	
	private int pageNum;
	
	public HowToScreen(Qbii g) {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		this.qbii = g;
		this.spriteBatch = new SpriteBatch();
		
		stage = new Stage();
		skin = new Skin();
		
		Resolution[] resolutions = {new Resolution(480, 320, "480x320"), new Resolution(960, 640, "960x640")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		
		manager = new AssetManager();
		manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
		manager.load("atlas/HUD-textures.atlas", TextureAtlas.class);
		manager.finishLoading();
		
		atlas = manager.get("atlas/HUD-textures.atlas", TextureAtlas.class);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		System.out.println("Width: " + WIDTH + "\tHeight: " + HEIGHT);
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		buildScreen();
		Gdx.input.setInputProcessor(stage);
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
		manager.dispose();
	}
	
	private void buildScreen() {
		pageNum = 1;
		
		for (int i = 1; i <= 7; i++) {
			skin.add("page" + i, new TextureRegion(atlas.findRegion("page" + i)));
		}
//		skin.add("page1", new TextureRegion(atlas.findRegion("page1")));
		skin.add("btnBack", new TextureRegion(atlas.findRegion("back")));
		skin.add("btnNext", new TextureRegion(atlas.findRegion("next")));
		skin.add("btnExit", new TextureRegion(atlas.findRegion("exit")));
		
		final Image page = new Image(skin.getRegion("page1"));
		
		ButtonStyle btnBackStyle = new ButtonStyle();
		btnBackStyle.up = skin.newDrawable("btnBack");
		btnBackStyle.down = skin.newDrawable("btnBack", Color.LIGHT_GRAY);
		skin.add("backStyle", btnBackStyle);
		
		ButtonStyle btnNextStyle = new ButtonStyle();
		btnNextStyle.up = skin.newDrawable("btnNext");
		btnNextStyle.down = skin.newDrawable("btnNext", Color.LIGHT_GRAY);
		skin.add("nextStyle", btnNextStyle);
		
		ButtonStyle btnExitStyle = new ButtonStyle();
		btnExitStyle.up = skin.newDrawable("btnExit");
		btnExitStyle.down = skin.newDrawable("btnExit", Color.LIGHT_GRAY);
		skin.add("exitStyle", btnExitStyle);

		final Button btnBack = new Button(skin, "backStyle");
		final Button btnNext = new Button(skin, "nextStyle");
		final Button btnExit = new Button(skin, "exitStyle");
		btnBack.setVisible(false);
		
		btnBack.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				if (pageNum == 7)
					btnNext.setVisible(true);
				pageNum--;
				if (pageNum == 1)
					btnBack.setVisible(false);
				page.setDrawable(skin, "page" + pageNum);
				System.out.println("Clicked! btnBack");
			}
		});
		
		btnNext.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				if (pageNum == 1)
					btnBack.setVisible(true);
				pageNum++;
				if (pageNum == 7)
					btnNext.setVisible(false);
				page.setDrawable(skin, "page" + pageNum);
				System.out.println("Clicked! btnNext");
			}
		});
		
		btnExit.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				qbii.setScreen(new MenuScreen(qbii));
				System.out.println("Clicked! btnExit");
			}
		});
		
//		Table table = new Table();
//		table.debug();
//		table.setFillParent(true);
//		
//		table.add(page).expand().center();
//		table.row();
//		table.add(btnBack);
//		table.add(btnNext);
		
//		System.out.println("page width: " + page.getWidth());
//		System.out.println("offset: " + (WIDTH - page.getWidth()));
		float xOffset = (WIDTH - page.getWidth()) / 2;
		float yOffset = (HEIGHT - page.getHeight()) / 1.5f;
		page.setPosition(xOffset, yOffset);
		btnBack.setPosition(xOffset, yOffset - btnBack.getHeight());
		btnNext.setPosition(WIDTH - xOffset - btnNext.getWidth(), yOffset - btnBack.getHeight());
		btnExit.setPosition(WIDTH - xOffset - btnExit.getWidth() / 2, yOffset + page.getHeight() - btnExit.getHeight() / 2);
		
		stage.addActor(page);
		stage.addActor(btnBack);
		stage.addActor(btnNext);
		stage.addActor(btnExit);
	}

}
