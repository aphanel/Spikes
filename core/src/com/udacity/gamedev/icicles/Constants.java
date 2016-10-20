package com.udacity.gamedev.icicles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by aphanel on 18/10/16.
 */

public class Constants {
    //World constants
    public static final float WORLD_SIZE = 10.0f;
    public static final Color WORLD_COLOR = Color.BLUE;

    //Icicles constants
    public static final float ICICLE_HEIGHT = 1.0f;
    public static final float ICICLE_WIDTH = 0.5f;
    public static final Color ICICLE_COLOR = Color.WHITE;
    public static final Vector2 ICICLE_ACCELERATION = new Vector2(0, -1.0f);

    //Player constants
    public static final Color PLAYER_COLOR = Color.BLACK;
    public static final float PLAYER_HEAD_RADIUS = 0.5f;
    public static final float PLAYER_TORSO_HEIGHT = 1.2f;
    public static final float PLAYER_ARMS_HEIGHT = 0.5f;
    public static final float PLAYER_ARMS_WIDTH = 0.5f;
    public static final float PLAYER_LEG_HEIGHT = 1.4f;
    public static final float PLAYER_LEG_WIDTH = 0.5f;
    public static final float PLAYER_SPEED = 10.0f;

    //Movements constant
    public static final float MOVEMENT_GRAVITY = 9.8f;
    public static final float MOVEMENT_ACCELEROMETER_SENSITIVITY = 0.5f;

    //HUD constants
    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f;
    public static final float HUD_MARGIN = 20.0f;

    //Difficulty constants
    public static final float EASY_SPAWNS = 5.0f;
    public static final float MEDIUM_SPAWNS = 15.0f;
    public static final float HARD_SPAWNS = 30.0f;
    public static final String EASY_LABEL = "Easy";
    public static final String MEDIUM_LABEL = "Medium";
    public static final String HARD_LABEL = "Hard";
    public static final Color EASY_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final Color MEDIUM_COLOR = new Color(0.5f, 0.5f, 1, 1);
    public static final Color HARD_COLOR = new Color(0.7f, 0.7f, 1, 1);
    public static final float DIFFICULTY_WORLD_SIZE = 480.0f;
    public static final float DIFFICULTY_CIRCLE_RADIUS = DIFFICULTY_WORLD_SIZE / 9;
    public static final float DIFFICULTY_LABEL_SCALE = 1.5f;
    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2);

    public enum Difficulty{
        EASY(EASY_SPAWNS, EASY_LABEL),
        MEDIUM(MEDIUM_SPAWNS, MEDIUM_LABEL),
        HARD(HARD_SPAWNS, HARD_LABEL);

        float spawnRate;
        String label;

        Difficulty(float spawnRate, String label){
            this.spawnRate = spawnRate;
            this.label = label;
        }
    }
}
