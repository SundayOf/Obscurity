package com.obscurity.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class MainMenu extends ApplicationAdapter {
	SpriteBatch batch;
	TiledMap map;
	Stage stage;
	int[] ground;
	Rectangle player;
	Rectangle[] bounds;
	OrthogonalTiledMapRenderer render;
	ShapeRenderer sr;
	Player pp;
	float gravity;
	Boolean isonground;
	OrthographicCamera cam;
	Vector2 tmpxy;
	@Override
	public void create () {
		isonground = false;
		gravity = 0;
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.x = Gdx.graphics.getWidth()/2;
		cam.position.y = Gdx.graphics.getHeight()/2;
		cam.zoom = 0.25f;
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		map = new TmxMapLoader().load("map/Cave.tmx");
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
		pp = new Player(player.x + player.width / 2, player.y + player.height / 2,player.width,player.height, .5f);
		pp.Create();

		bounds = new Rectangle[map.getLayers().get("bounds").getObjects().getByType(RectangleMapObject.class).size];
		for (int i=0;i<bounds.length;i++){
			bounds[i] = map.getLayers().get("bounds").getObjects().getByType(RectangleMapObject.class).get(i).getRectangle();
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		isonground = false;
		gravity += (10 * 9.8f)/2000;
		player.y-=gravity;
		for (int i = 0; i < bounds.length;i++){
			if (bounds[i].overlaps(player)) {player.y+=gravity; gravity=0; isonground = true;}
			}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){ player.x +=pp.movementSpeed;
		for (int i = 0; i < bounds.length;i++){
			if (bounds[i].overlaps(player))player.x-=pp.movementSpeed;
		}}
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)&&isonground){ player.y += 25f; }
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			player.x -=pp.movementSpeed;
			for (int i = 0; i < bounds.length;i++){
				if (bounds[i].overlaps(player))player.x+=pp.movementSpeed;
			}}

		cam.position.x = player.x + player.width / 2;
		cam.position.y = player.y + player.height / 2;
		cam.update();

//		render.setView((OrthographicCamera) stage.getCamera());
		render.render(ground);

		stage.getBatch().setProjectionMatrix(cam.combined);
		render.setView(cam);
		stage.draw();
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeRenderer.ShapeType.Line);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		pp.xCenter = player.x;
		pp.yCenter = player.y;
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {pp.isWalking = true;}
		if (!Gdx.input.isKeyPressed(Input.Keys.D)) {pp.isWalking = false;}
		if (pp.isWalking){
			pp.DrawAnimation(batch);
		} else pp.draw(batch);
		batch.end();
		sr.setColor(Color.GREEN);
		sr.rect(player.x, player.y, player.width, player.height);
		sr.setColor(Color.WHITE);
		for (int i=0;i<bounds.length;i++){
			sr.rect(bounds[i].x, bounds[i].y, bounds[i].width, bounds[i].height);
		}
		sr.end();
	}
}
