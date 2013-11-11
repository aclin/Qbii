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
		drawTable();
	}
	
	private void drawTable() {
		// create labels in the table
		Label title = new Label("QBII", skin);
		
		// create the table actor
		Table table = new Table();
		table.debug();
//		table.row();
		table.setPosition(WIDTH/2, HEIGHT/2);
		table.add(title).center();
		
		// add the table to the stage and retrieve its layout
		table.layout();
		stage.addActor(table);
	}
	
	public void render() {
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);
	}
	
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}
}
