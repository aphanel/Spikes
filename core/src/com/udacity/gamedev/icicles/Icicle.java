package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by aphanel on 18/10/16.
 */

public class Icicle {

    public static final String TAG = Icicle.class.getName();
    Vector2 position;
    Vector2 velocity;

    Icicle(Vector2 position){
        this.position = position;
        this.velocity = new Vector2();
    }

    public void update(float delta){
        velocity.mulAdd(Constants.ICICLE_ACCELERATION, delta);
        position.mulAdd(velocity, delta);
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.ICICLE_COLOR);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.triangle(
                position.x, position.y,
                position.x - Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT,
                position.x + Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT
        );
        renderer.end();
    }
}
