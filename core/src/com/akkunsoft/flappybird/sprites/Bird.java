package com.akkunsoft.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
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
    private Texture bird;
    private Rectangle birdCollider;


    public Bird(int x, int y){
        pos = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png"); // for penguin ("penguin.png");
        birdCollider = new Rectangle(pos.x, pos.y, bird.getWidth(), bird.getHeight());
    }

    public void update(float dt){
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
    }

    public Vector3 getPos() {
        return pos;
    }

    public Texture getTexture() {
        return bird;
    }

    public void dispose(){
        bird.dispose();
    }
}