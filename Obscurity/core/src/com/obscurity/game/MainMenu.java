package com.obscurity.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class MainMenu extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TiledMap map;
	Stage stage;
	int[] ground;
	Rectangle player;
	Rectangle[] bounds;
	OrthogonalTiledMapRenderer render;
	ShapeRenderer sr;
	@Override
	public void create () {
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		map = new TmxMapLoader().load("Cave.tmx");
		stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		ground = new int[1];
		ground[0] = map.getLayers().getIndex("Ground");
		render = new OrthogonalTiledMapRenderer(map);

		int tmp = map.getLayers().get("Player").getObjects().getByType(RectangleMapObject.class).size;
		for (int i=0;i<tmp;i++){
			String stmp = (String)map.getLayers().get("Player").getObjects().getByType(RectangleMapObject.class).get(i).getProperties().get("name");
			if (stmp.matches("Player")) {
				player = map.getLayers().get("Player").getObjects()
						.getByType(RectangleMapObject.class).get(i).getRectangle();
			}
		}
		bounds = new Rectangle[map.getLayers().get("bounds").getObjects().getByType(RectangleMapObject.class).size];
		for (int i=0;i<bounds.length;i++){
			bounds[i] = map.getLayers().get("bounds").getObjects().getByType(RectangleMapObject.class).get(i).getRectangle();
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		render.setView((OrthographicCamera) stage.getCamera());
		render.render(ground);
		stage.draw();
		sr.begin(ShapeRenderer.ShapeType.Line);
		sr.setColor(Color.RED);
		sr.rect(player.x, player.y, player.width, player.height);
		for (int i=0;i<bounds.length;i++){
			sr.rect(bounds[i].x, bounds[i].y, bounds[i].width, bounds[i].height);
		}
		sr.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
