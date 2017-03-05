package com.akkunsoft.flappypenguin.states;

import com.akkunsoft.flappypenguin.FlappyPenguin;
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
        background = new Texture("background.png");
        playbtn = new Texture("playButton.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, FlappyPenguin.WIDTH, FlappyPenguin.HEIGHT);
        sb.draw(playbtn, (FlappyPenguin.WIDTH / 2)-(playbtn.getWidth()/2),(FlappyPenguin.HEIGHT/2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playbtn.dispose();
    }
}
