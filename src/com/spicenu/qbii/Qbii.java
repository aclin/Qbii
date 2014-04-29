package com.spicenu.qbii;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.spicenu.qbii.screens.CrateScreen;
import com.spicenu.qbii.screens.IntroScreen;
import com.spicenu.qbii.screens.MenuScreen;
import com.spicenu.qbii.screens.SplashScreen;

public class Qbii extends Game {
	
//	public final static Preferences corePrefs = Gdx.app.getPreferences("com.spicenu.qbii.corePreferences");
//	public final static Preferences settingsPrefs = Gdx.app.getPreferences("com.spicenu.qbii.settingsPreferences");
	
	public static final String HUD_SKIN = "HUD_SKIN";
	public static final String GAME_SKIN = "GAME_SKIN";
	
	public AssetManager manager;
	
	public Qbii() {
		manager = new AssetManager();
	}
	
	@Override
	public void create() {
		runSplashScreen();
		
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
	public void runSplashScreen() {
		setScreen(new SplashScreen(this));
	}
	
	public void runMenuScreen() {
		setScreen(new MenuScreen(this));
	}
	
	public void runIntroScreen() {
		setScreen(new IntroScreen(this));
	}
	
	public void runCrateScreen() {
		setScreen(new CrateScreen(this));
	}
	
	/**
	 * Loads all the assets necessary in the game
	 */
	private void createAssets() {
		Resolution[] resolutions = {new Resolution(480, 320, "480x320"), new Resolution(960, 640, "960x640")};
		ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
		
		manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
		manager.load("atlas/textures.atlas", TextureAtlas.class);
		manager.finishLoading();
	}
}
