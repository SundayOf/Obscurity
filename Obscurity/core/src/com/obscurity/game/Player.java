package com.obscurity.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    TextureRegion playerTextureRegion;
    Animation animation;
    float xCenter,yCenter,width,height,movementSpeed;

    public Player(TextureRegion playerTextureRegion,float xCenter, float yCenter, float width, float height, float movementSpeed){
        this.playerTextureRegion = playerTextureRegion;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.width = width;
        this.height = height;
        this.movementSpeed = movementSpeed;
    }
    public void draw(Batch batch){
        batch.draw(playerTextureRegion, xCenter, yCenter,width, height);
    }
}
