package com.spicenu.qbii.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuRenderer {
	
	private static final int WIDTH = Gdx.graphics.getWidth();
	private static final int HEIGHT = Gdx.graphics.getHeight();
	
	Stage stage;
	Skin skin;
	
	public MenuRenderer() {
		stage = new Stage(WIDTH, HEIGHT, false);
		System.out.println("Width: " + WIDTH + ", Height: " + HEIGHT);
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
	}
	
	private void drawTable(boolean debug) {
		// create labels in the table
		Label title = new Label("QBII", skin);
		Button btnStart = new TextButton("Start Game", skin);
		Button btnOptions = new TextButton("Options", skin);
		
		// create the table actor
		Table table = new Table();
		if (debug)
			table.debug();
		table.setPosition(0, 0);
		table.setWidth(WIDTH);
		table.setHeight(HEIGHT);
		table.center();
		table.add(title).padBottom(10);
		table.row();
		table.add(btnStart).padBottom(10);
		table.row();
		table.add(btnOptions);
		
		// add the table to the stage and retrieve its layout
		table.layout();
		stage.addActor(table);
	}
	
	public void render(boolean debug) {
		drawTable(debug);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		if (debug)
			Table.drawDebug(stage);
	}
	
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}
}
