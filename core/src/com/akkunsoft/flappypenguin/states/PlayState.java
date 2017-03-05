package com.akkunsoft.flappypenguin.states;

import com.akkunsoft.flappypenguin.FlappyPenguin;
import com.akkunsoft.flappypenguin.sprites.Penguin;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Akkun on 2017-03-04.
 */

public class PlayState extends State {
    private Penguin penguin;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        penguin = new Penguin(50,100);
        cam.setToOrtho(false, FlappyPenguin.WIDTH/2, FlappyPenguin.HEIGHT/2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        penguin.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(penguin.getTexture(), penguin.getPos().x, penguin.getPos().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
