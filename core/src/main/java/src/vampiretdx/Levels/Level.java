package src.vampiretdx.Levels;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import src.vampiretdx.ControlBar;

public class Level {
    protected float spawnX;
    protected float spawnY;
    protected String levelName;
    protected Texture levelTexture;
    protected float[][] PathCoordinate;
    protected OrthographicCamera cam;
    protected ControlBar controlBar;

    public Level(float spawnX, float spawnY, Texture levelTexture) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.levelTexture = levelTexture;
    }

    public void start(float delta, SpriteBatch batch) {
    }

    public void render(SpriteBatch batch) {
        batch.draw(levelTexture, 0, 0, cam.viewportWidth, cam.viewportHeight);
    }

    public Float getSpawnX() {
        return spawnX;
    }

    public Float getSpawnY() {
        return spawnY;
    }

    public String getLevelName() {
        return levelName;
    }

    public Texture getLevelTexture() {
        return levelTexture;
    }

    public float[][] getPathCoordinate() {
        return PathCoordinate;
    }

    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }

    public Wave getCurrentWave() {
        return new Wave();
    };

    public ArrayList<Wave> getWaweList() {
        return null;
    }

    public void setControlBar(ControlBar controlBar){
        this.controlBar = controlBar;
    }

    public ControlBar getControlBar(){
        return this.controlBar;
    }

    public void setupEnemies() {
    }
}
