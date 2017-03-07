package com.akkunsoft.flappybird.states;

import com.akkunsoft.flappybird.FlappyBird;
import com.akkunsoft.flappybird.sprites.Bird;
import com.akkunsoft.flappybird.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Akkun on 2017-03-04.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_OFFSET_Y = -50;

    private Bird bird;
    private Texture background;
    private Texture ground;
    private Vector2 grndPos1, grndPos2;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, FlappyBird.WIDTH/2, FlappyBird.HEIGHT/2);
        background = new Texture("bg.png"); //For penguin ("background.png");
        ground = new Texture("ground.png");
        grndPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_OFFSET_Y);
        grndPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + ground.getWidth(), GROUND_OFFSET_Y);

        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT;i++){
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPos().x + 80;

        // if tube get off screen, reposition it after the others
        for(int i = 0; i < tubes.size; i++){
            Tube tube  = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING)*TUBE_COUNT));
            }

            //Since there's only 4 objects, we can check individually each collider
            if(tube.collides(bird.getCollider())){
                gsm.set(new PlayState(gsm));
            }
        }
        if(bird.getPos().y <= ground.getHeight() + GROUND_OFFSET_Y)
            gsm.set(new PlayState(gsm));
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(bird.getTexture(),bird.getPos().x,bird.getPos().y);
        for (Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.draw(ground, grndPos1.x, grndPos1.y);
        sb.draw(ground, grndPos2.x, grndPos2.y);
       sb.end();
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth /2) > grndPos1.x + ground.getWidth())
            grndPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth /2) > grndPos2.x + ground.getWidth())
            grndPos2.add(ground.getWidth() * 2, 0);
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for(Tube tube :tubes) {
            tube.dispose();
        }
        ground.dispose();
        System.out.println("Play State Disposed");
    }
}
