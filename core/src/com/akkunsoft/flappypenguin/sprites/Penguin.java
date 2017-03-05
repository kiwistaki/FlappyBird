package com.akkunsoft.flappypenguin.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Akkun on 2017-03-04.
 */

public class Penguin {
    private static final int GRAVITY = -15;
    private Vector3 pos;
    private Vector3 velocity;
    private Texture penguin;

    public Penguin(int x, int y){
        pos = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        penguin = new Texture("penguin.png");
    }

    public void update(float dt){
        velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        pos.add(0,velocity.y,0);
        velocity.scl(1/dt);
    }

    public Vector3 getPos() {
        return pos;
    }

    public Texture getTexture() {
        return penguin;
    }
}
