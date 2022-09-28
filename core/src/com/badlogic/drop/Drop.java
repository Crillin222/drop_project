package com.badlogic.drop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class Drop extends ApplicationAdapter {
	private Texture dropImage;
	private Texture bucketImage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Rectangle bucket;
	
	@Override
	public void create () {
		//load the images for the droplet and the bucket, 64x64 pixels each
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		//creating camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
		//stantiating spritebatch
		batch = new SpriteBatch();
		//stantiating the bucket and giving it value
		bucket = new Rectangle();
		//centering the bucket horizontally 
		bucket.x = 800 / 2 - 64 / 2;
		//placing bucket 20 pixels above the bottom edge of the screen
		bucket.y = 20;
		//bucket size
		bucket.width = 64;
		bucket.height = 64;
	}

	@Override
	public void render () {
		//clearing the screen with a dark blue color
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//updating camera
		camera.update();
		//rendering the bucket
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		batch.end();
		
		//making the bucket move with the mouse
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64 / 2;
		}
	}
	
	@Override
	public void dispose () {
	}
}
