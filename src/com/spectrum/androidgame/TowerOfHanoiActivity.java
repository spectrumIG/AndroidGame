package com.spectrum.androidgame;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.media.RingtoneManager;

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
	private Stack stack1;
	private Stack stack2;
	private Stack stack3;

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

			this.stack1 = new Stack();
			this.stack2 = new Stack();
			this.stack3 = new Stack();

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

		this.tower1 = new Sprite(192, 63, this.towerTextureRegion,
				getVertexBufferObjectManager());
		this.tower2 = new Sprite(400, 63, this.towerTextureRegion,
				getVertexBufferObjectManager());
		this.tower3 = new Sprite(604, 63, this.towerTextureRegion,
				getVertexBufferObjectManager());
		scene.attachChild(this.tower1);
		scene.attachChild(this.tower2);
		scene.attachChild(this.tower3);

		Ring ring1 = new Ring(1, 139, 174, this.ring1,
				getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent sceneTouchEvent,
					float touchAreaLocalX, float touchAreaLocalY) {
				if (((Ring) this.getStack().peek()).getWeight() != this
						.getWeight()) {
					return false;
				}
				this.setPosition(sceneTouchEvent.getX() - this.getWidth() / 2,
						sceneTouchEvent.getY() - this.getHeight() / 2);
				if (sceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					checkForCollisionsWithTower(this);
				}
				return true;
			}

		};
		Ring ring2 = new Ring(2, 118, 212, this.ring2,
				getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent sceneTouchEvent,
					float touchAreaLocalX, float touchAreaLocalY) {
				if (((Ring) this.getStack().peek()).getWeight() != this
						.getWeight()) {
					return false;
				}
				this.setPosition(sceneTouchEvent.getX() - this.getWidth() / 2,
						sceneTouchEvent.getY() - this.getHeight() / 2);
				if (sceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					checkForCollisionsWithTower(this);
				}
				return true;
			}
		};
		Ring ring3 = new Ring(3, 97, 255, this.ring3,
				getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent sceneTouchEvent,
					float touchAreaLocalX, float touchAreaLocalY) {
				if (((Ring) this.getStack().peek()).getWeight() != this
						.getWeight()) {
					return false;
				}
				this.setPosition(sceneTouchEvent.getX() - this.getWidth() / 2,
						sceneTouchEvent.getY() - this.getHeight() / 2);
				if (sceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					checkForCollisionsWithTower(this);
				}
				return true;
			}

		};
		scene.attachChild(ring1);
		scene.attachChild(ring2);
		scene.attachChild(ring3);

		this.stack1.add(ring3);
		this.stack1.add(ring2);
		this.stack1.add(ring1);
		ring1.setStack(stack1);
		ring2.setStack(stack1);
		ring3.setStack(stack1);
		ring1.setTower(tower1);
		ring2.setTower(tower1);
		ring3.setTower(tower1);

		scene.registerTouchArea(ring1);
		scene.registerTouchArea(ring2);
		scene.registerTouchArea(ring3);
		scene.setTouchAreaBindingOnActionDownEnabled(true);
		return scene;
	}

	private void checkForCollisionsWithTower(Ring ring) {
		Stack stack = null;
		Sprite tower = null;

		if (ring.collidesWith(this.tower1)
				&& (this.stack1.size() == 0 || ring.getWeight() < ((Ring) this.stack1
						.peek()).getWeight())) {
			stack = this.stack1;
			tower = this.tower1;

		} else if (ring.collidesWith(this.tower2)
				&& (this.stack2.size() == 0 || ring.getWeight() < ((Ring) this.stack2
						.peek()).getWeight())) {
			stack = this.stack2;
			tower = this.tower2;

		} else if (ring.collidesWith(this.tower3)
				&& (this.stack3.size() == 0 || ring.getWeight() < ((Ring) this.stack3
						.peek()).getWeight())) {
			stack = this.stack3;
			tower = this.tower3;
		} else {
			stack = ring.getStack();
			tower = ring.getTower();
		}
		ring.getStack().remove(ring);

		if (stack != null && tower != null && stack.size() == 0) {
			ring.setPosition(
					tower.getX() + tower.getWidth() / 2 - ring.getWidth() / 2,
					tower.getY() + tower.getHeight() - ring.getHeight());

		} else if (stack != null && tower != null && stack.size() > 0) {
			ring.setPosition(
					tower.getX() + tower.getWidth() / 2 - ring.getWidth() / 2,
					((Ring) stack.peek()).getY() - ring.getHeight());
		}
		
		stack.add(ring);
		ring.setStack(stack);
		ring.setTower(tower);
	}
}
