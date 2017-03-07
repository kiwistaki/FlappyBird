package com.akkunsoft.flappybird.states;

import com.akkunsoft.flappybird.FlappyBird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Akkun on 2017-03-04.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playbtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");   //for penguin ("background.png");
        playbtn = new Texture("playbtn.png"); //for penguin ("playButton.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        sb.draw(playbtn, (FlappyBird.WIDTH / 2)-(playbtn.getWidth()/2),(FlappyBird.HEIGHT/2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
