package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by aphanel on 18/10/16.
 */

public class Player {

    public static final String TAG = Player.class.getName();

    Vector2 position;
    Viewport viewport;
    int numberOfHits;

    Player(Viewport viewport){
        this.viewport = viewport;
        init();
    }

    public void init(){
        position = new Vector2(
                Constants.WORLD_SIZE / 2,
                Constants.PLAYER_LEG_HEIGHT+Constants.PLAYER_TORSO_HEIGHT);
        numberOfHits = 0;
    }

    public void update(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            position.x -= delta * Constants.PLAYER_SPEED;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            position.x += delta * Constants.PLAYER_SPEED;
        }
        float accelerometerInputY = Gdx.input.getAccelerometerY() / (Constants.MOVEMENT_GRAVITY * Constants.MOVEMENT_ACCELEROMETER_SENSITIVITY);
        position.x += - (delta* accelerometerInputY*Constants.PLAYER_SPEED);
        stayInBounds();
    }

    public void stayInBounds(){
        if(position.x - Constants.PLAYER_HEAD_RADIUS< 0){
            position.x = Constants.PLAYER_HEAD_RADIUS;
        }
        if(position.x + Constants.PLAYER_HEAD_RADIUS > viewport.getWorldWidth()){
            position.x = viewport.getWorldWidth() - Constants.PLAYER_HEAD_RADIUS;
        }
    }

    public boolean isHitByIcicles(Icicles icicles){
        boolean isHit = false;

        for (Icicle icicle:icicles.icicleArray) {
            if(icicle.position.dst(position)< Constants.PLAYER_HEAD_RADIUS){
                isHit = true;
                numberOfHits += 1;
            }
        }
        return isHit;
    }

    public void render(ShapeRenderer renderer){
        renderer.setColor(Constants.PLAYER_COLOR);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle(
                position.x,
                position.y+Constants.PLAYER_HEAD_RADIUS,
                Constants.PLAYER_HEAD_RADIUS,
                24
        );
        renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.line(position.x, position.y, position.x, position.y-Constants.PLAYER_TORSO_HEIGHT);
        renderer.line(
                position.x,
                position.y -Constants.PLAYER_TORSO_HEIGHT/3,
                position.x - Constants.PLAYER_ARMS_WIDTH,
                position.y - (Constants.PLAYER_TORSO_HEIGHT/3 + Constants.PLAYER_ARMS_HEIGHT)
        );
        renderer.line(
                position.x,
                position.y - Constants.PLAYER_TORSO_HEIGHT/3,
                position.x + Constants.PLAYER_ARMS_WIDTH,
                position.y - (Constants.PLAYER_TORSO_HEIGHT/3 + Constants.PLAYER_ARMS_HEIGHT)
        );
        renderer.line(
                position.x,
                position.y - Constants.PLAYER_TORSO_HEIGHT,
                position.x - Constants.PLAYER_LEG_WIDTH,
                position.y - (Constants.PLAYER_TORSO_HEIGHT + Constants.PLAYER_LEG_HEIGHT)
        );
        renderer.line(
                position.x,
                position.y - Constants.PLAYER_TORSO_HEIGHT,
                position.x + Constants.PLAYER_LEG_WIDTH,
                position.y - (Constants.PLAYER_TORSO_HEIGHT + Constants.PLAYER_LEG_HEIGHT)
        );
        renderer.end();


    }

}
