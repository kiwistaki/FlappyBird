package com.akkunsoft.flappybird.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Akkun on 2017-03-07.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 pos;
    private Vector3 velocity;
    private Texture texture;
    private Animation birdAnimation;
    private Rectangle birdCollider;
    private Sound flappingSound;


    public Bird(int x, int y){
        pos = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        birdCollider = new Rectangle(pos.x, pos.y, texture.getWidth()/3, texture.getHeight());
        flappingSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if( pos.y >0)
            velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        pos.add(MOVEMENT*dt, velocity.y, 0);
        if(pos.y < 0){
            pos.y = 0;
        }
        velocity.scl(1/dt);
        birdCollider.setPosition(pos.x, pos.y);
    }

    public Rectangle getCollider(){
        return birdCollider;
    }
    public void jump(){
        velocity.y = 250;
        flappingSound.play(0.5f);
    }

    public float getRotation(){
        if(velocity.y >= 45)
            return 30f;
        if(velocity.y < 45)
            return -30f;
        return velocity.y;
    }
    public Vector3 getPos() {
        return pos;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void dispose(){
        texture.dispose();
        flappingSound.dispose();
    }
}