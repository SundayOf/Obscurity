package com.obscurity.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Player {
    float xCenter,yCenter,width,height,movementSpeed;
    private float elapsedTime = 0f;
    TextureAtlas textureAtlas;
    Animation <TextureRegion> animation;
    Texture texture;
    boolean isWalking = false;
    public Player(float xCenter, float yCenter, float width, float height, float movementSpeed){
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.width = width;
        this.height = height;
        this.movementSpeed = movementSpeed;
    }
    public void Create(){
        texture = new Texture("HeroAnimations/Standing/hero1.png");
        textureAtlas = new TextureAtlas(Gdx.files.internal("HeroAnimations/Walk/walk.atlas"));
        animation = new Animation<TextureRegion>(1f/4f, textureAtlas.getRegions());

    }
    public void DrawAnimation(Batch batch){
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(elapsedTime,true),xCenter,yCenter,width,height);
        isWalking = true;
    }
    public void draw(Batch batch){
        batch.draw(texture,xCenter,yCenter,width,height);
    }
}
