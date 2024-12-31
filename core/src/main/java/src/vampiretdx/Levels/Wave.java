package src.vampiretdx.Levels;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import src.vampiretdx.Enemies.Enemy;

public class Wave {
    protected ArrayList<Enemy> enemies;
    protected int wave;

    public Wave() {
        enemies = new ArrayList<>();
        wave = 1;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void startWave(float delta, Wave wave, SpriteBatch batch) {
        for (Enemy enemy : enemies) {
            enemy.render(batch);
            enemy.move(delta);
        }

    };
}
