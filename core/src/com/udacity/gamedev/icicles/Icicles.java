package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by aphanel on 18/10/16.
 */

public class Icicles {
    public static final String TAG = Icicles.class.getName();

    Constants.Difficulty difficulty;

    DelayedRemovalArray<Icicle> icicleArray;
    Viewport viewport;
    int dodgedIcicles;

    public Icicles(Viewport viewport, Constants.Difficulty difficulty){
        this.difficulty = difficulty;
        this.viewport = viewport;
        init();
    }

    public void init(){
        icicleArray = new DelayedRemovalArray<Icicle>(false,100);
        dodgedIcicles = 0;
    }

    public void update(float delta){
        if(MathUtils.random() < delta * difficulty.spawnRate){
            Vector2 iciclePosition = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight()
            );
            Icicle icicle = new Icicle(iciclePosition);
            icicleArray.add(icicle);
        }

        for ( Icicle icicle : icicleArray){
            icicle.update(delta);
        }

        icicleArray.begin();
        for (int i = 0; i < icicleArray.size; i++) {
            if(icicleArray.get(i).position.y < - Constants.ICICLE_HEIGHT){
                dodgedIcicles +=1;
                icicleArray.removeIndex(i);
            }
        }
        icicleArray.end();
    }

    public void render(ShapeRenderer renderer){
        for (Icicle icicle : icicleArray) {
            icicle.render(renderer);
        }
    }

}
