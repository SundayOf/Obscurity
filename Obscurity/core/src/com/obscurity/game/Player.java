package com.obscurity.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Player {
    float xCenter,yCenter,width,height,movementSpeed;
    private float elapsedTime = 0f;
    TextureAtlas walktoright, walktoleft;
    Animation <TextureRegion> animation1,animation2;
    Texture texture1, texture2;
    boolean isWalking = false;
    TextureRegion tr;
    public Player(float xCenter, float yCenter, float width, float height, float movementSpeed){
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.width = width;
        this.height = height;
        this.movementSpeed = movementSpeed;
        tr = null;
    }
    public void Create(){
        texture1 = new Texture("HeroAnimations/Standing/hero1.png");
        texture2 = new Texture("HeroAnimations/Standing/hero2.png");
        walktoright = new TextureAtlas(Gdx.files.internal("HeroAnimations/Walk/walk.atlas"));
        walktoleft = new TextureAtlas(Gdx.files.internal("HeroAnimations/Walk/walktoleft.atlas"));
        animation1 = new Animation<TextureRegion>(1f / 4f, walktoright.getRegions());
        animation2 = new Animation<TextureRegion>(1f / 5f, walktoleft.getRegions());
    }
    public void DrawAnimation(Batch batch, Animation<TextureRegion> animation, int k){
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (tr == null) {
            tr = animation.getKeyFrame(elapsedTime, true);
        }
        else if(tr!= animation.getKeyFrame(elapsedTime, true)) {
            MainMenu.player.x +=k*movementSpeed;
            tr = animation.getKeyFrame(elapsedTime, true);
        }
        batch.draw(animation.getKeyFrame(elapsedTime, true), xCenter, yCenter, width, height);

        isWalking = true;
    }

    public void draw(Batch batch, Texture texture){
        batch.draw(texture,xCenter,yCenter,width,height);
    }
}
