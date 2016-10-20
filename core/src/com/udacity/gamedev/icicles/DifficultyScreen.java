package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by aphanel on 18/10/16.
 */

public class DifficultyScreen extends InputAdapter implements Screen {

    public static final String TAG = DifficultyScreen.class.getName();

    ShapeRenderer renderer;
    SpriteBatch spriteBatch;
    FitViewport fitViewport;
    BitmapFont bitmapFont;
    IciclesGame iciclesGame;

    public DifficultyScreen(IciclesGame iciclesGame){
        this.iciclesGame = iciclesGame;
    }

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        fitViewport = new FitViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE);
        Gdx.input.setInputProcessor(this);
        bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {
        fitViewport.apply();

        Gdx.gl.glClearColor(Constants.WORLD_COLOR.r,Constants.WORLD_COLOR.g,Constants.WORLD_COLOR.b,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(fitViewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.EASY_COLOR);
        renderer.circle(Constants.EASY_CENTER.x, Constants.EASY_CENTER.y, Constants.DIFFICULTY_CIRCLE_RADIUS);
        renderer.setColor(Constants.MEDIUM_COLOR);
        renderer.circle(Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y, Constants.DIFFICULTY_CIRCLE_RADIUS);
        renderer.setColor(Constants.HARD_COLOR);
        renderer.circle(Constants.HARD_CENTER.x, Constants.HARD_CENTER.y, Constants.DIFFICULTY_CIRCLE_RADIUS);
        renderer.end();

        spriteBatch.setProjectionMatrix(fitViewport.getCamera().combined);

        spriteBatch.begin();
        final GlyphLayout easyLayout = new GlyphLayout(bitmapFont, Constants.EASY_LABEL);
        bitmapFont.draw(spriteBatch, Constants.EASY_LABEL, Constants.EASY_CENTER.x, Constants.EASY_CENTER.y + easyLayout.height / 2, 0, Align.center, false);
        final GlyphLayout mediumLayout = new GlyphLayout(bitmapFont, Constants.MEDIUM_LABEL);
        bitmapFont.draw(spriteBatch, Constants.MEDIUM_LABEL, Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y + mediumLayout.height / 2, 0, Align.center, false);
        final GlyphLayout hardLayout = new GlyphLayout(bitmapFont, Constants.HARD_LABEL);
        bitmapFont.draw(spriteBatch, Constants.HARD_LABEL, Constants.HARD_CENTER.x, Constants.HARD_CENTER.y + hardLayout.height / 2, 0, Align.center, false);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        spriteBatch.dispose();
        bitmapFont.dispose();
        renderer.dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        Vector2 unprojectedTouch = fitViewport.unproject(new Vector2(screenX, screenY));

        if(unprojectedTouch.dst(Constants.EASY_CENTER)<Constants.DIFFICULTY_CIRCLE_RADIUS){
            iciclesGame.showIciclesScreen(Constants.Difficulty.EASY);
        }
        if(unprojectedTouch.dst(Constants.MEDIUM_CENTER)<Constants.DIFFICULTY_CIRCLE_RADIUS){
            iciclesGame.showIciclesScreen(Constants.Difficulty.MEDIUM);
        }
        if(unprojectedTouch.dst(Constants.HARD_CENTER)<Constants.DIFFICULTY_CIRCLE_RADIUS){
            iciclesGame.showIciclesScreen(Constants.Difficulty.HARD);
        }
        return true;
    }

}
