package com.spectrum.androidgame;
import java.util.Stack;

import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Game logic.
 * 
 */
public class Ring extends Sprite {
	private int weight;		// weight of every ring
	private Stack stack;	// the stack in its splendor, used to managed the normal
							// stack behaviour
	private Sprite tower;	// oviously the tower itself

	public Ring(int weight, float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public Stack getStack() {
		return stack;
	}

	public void setStack(Stack stack) {
		this.stack = stack;
	}

	public Sprite getTower() {
		return tower;
	}

	public void setTower(Sprite tower) {
		this.tower = tower;
	}

	
}
