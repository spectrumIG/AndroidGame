package com.spectrum.androidgame;
import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

public class TowerOfHanoiActivity extends SimpleBaseGameActivity {
	private static int CAMERA_WIDTH = 800;
	private static int CAMERA_HEIGHT = 480;
	private ITextureRegion backgroundTextureRegion;
	private ITextureRegion towerTextureRegion;
	private ITextureRegion ring1;
	private ITextureRegion ring2;
	private ITextureRegion ring3;
	private Sprite tower1;
	private Sprite tower2;
	private Sprite tower3;

	public EngineOptions onCreateEngineOptions() {
		Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);

	}

	@Override
	protected void onCreateResources() {
		try {
			ITexture backgroundITexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {

						public InputStream open() throws IOException {
							return getAssets().open("gfx/background.png");
						}
					});
			ITexture towerITexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {

						public InputStream open() throws IOException {
							return getAssets().open("gfx/tower.png");
						}
					});
			ITexture ring1 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						public InputStream open() throws IOException {
							return getAssets().open("gfx/ring1.png");
						}
					});
			ITexture ring2 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						public InputStream open() throws IOException {
							return getAssets().open("gfx/ring2.png");
						}
					});
			ITexture ring3 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						public InputStream open() throws IOException {
							return getAssets().open("gfx/ring3.png");
						}
					});
			backgroundITexture.load();
			towerITexture.load();
			ring1.load();
			ring2.load();
			ring3.load();

			this.backgroundTextureRegion = TextureRegionFactory
					.extractFromTexture(backgroundITexture);
			this.towerTextureRegion = TextureRegionFactory
					.extractFromTexture(towerITexture);
			this.ring1 = TextureRegionFactory.extractFromTexture(ring1);
			this.ring2 = TextureRegionFactory.extractFromTexture(ring2);
			this.ring3 = TextureRegionFactory.extractFromTexture(ring3);

		} catch (IOException e) {
			Debug.e(e);
		}

	}

	@Override
	protected Scene onCreateScene() {
		Scene scene = new Scene();
		Sprite backgroundSprite = new Sprite(0, 0,
				this.backgroundTextureRegion, getVertexBufferObjectManager());
		scene.attachChild(backgroundSprite);

		return scene;
	}

}
