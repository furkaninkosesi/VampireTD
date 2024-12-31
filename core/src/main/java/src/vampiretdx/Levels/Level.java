package src.vampiretdx.Levels;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {
    protected float spawnX;
    protected float spawnY;
    protected String levelName;
    protected Texture levelTexture;
    protected float[][] PathCoordinate;

    public Level(float spawnX, float spawnY, Texture levelTexture) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.levelTexture = levelTexture;
    }
    public void start(float delta, SpriteBatch batch){}
    public void render(OrthographicCamera cam, SpriteBatch batch) {
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

}
