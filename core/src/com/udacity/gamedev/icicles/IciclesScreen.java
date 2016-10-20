package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by aphanel on 18/10/16.
 */

public class IciclesScreen implements Screen {

    ShapeRenderer renderer;
    ExtendViewport extendViewport;
    ScreenViewport hudViewport;
    SpriteBatch spriteBatch;
    BitmapFont bitmapFont;
    Icicles icicles;
    Player player;
    public int highscore;
    Constants.Difficulty difficulty;
    IciclesGame iciclesGame;

    public IciclesScreen(IciclesGame iciclesGame, Constants.Difficulty difficulty){
        this.difficulty = difficulty;
        this.iciclesGame = iciclesGame;
    }

    @Override
    public void show() {
        extendViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        hudViewport = new ScreenViewport();
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        icicles = new Icicles(extendViewport, difficulty);
        player = new Player(extendViewport);
        highscore = 0;
    }

    @Override
    public void render(float delta) {
        player.update(delta);
        icicles.update(delta);
        if(player.isHitByIcicles(icicles)){
            if(icicles.dodgedIcicles>highscore){
                highscore = icicles.dodgedIcicles;
            }
            icicles.init();
        }

        extendViewport.apply(true);
        hudViewport.apply();
        Gdx.gl.glClearColor(Constants.WORLD_COLOR.r, Constants.WORLD_COLOR.g, Constants.WORLD_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(extendViewport.getCamera().combined);
        spriteBatch.setProjectionMatrix(hudViewport.getCamera().combined);

        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, "Hits: " + player.numberOfHits,
                Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN);
        bitmapFont.draw(spriteBatch, "Score: " + icicles.dodgedIcicles + "\nHighscore: " + highscore,
                hudViewport.getWorldWidth() - Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN,
                0, Align.right, false);
        spriteBatch.end();

        icicles.render(renderer);
        player.render(renderer);

    }

    @Override
    public void resize(int width, int height) {
        extendViewport.update(width,height,true);
        hudViewport.update(width, height, true);
        bitmapFont.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);
        player.init();
        icicles.init();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        bitmapFont.dispose();
        spriteBatch.dispose();
    }
}
