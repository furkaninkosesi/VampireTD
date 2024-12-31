package src.vampiretdx.Levels;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import src.vampiretdx.Enemies.*;

public class Level1 extends Level {
    ArrayList<Wave> waweList;
    int currentWave = 0;

    public Level1() {
        super(0, 0, new Texture("level1_map.png"));
        float[][] PathCoordinates = {
                { 600.99994f, 203.0f },
                { 319.99997f, 185.99998f },
                { 303.99997f, 32.0f }
        };
        this.PathCoordinate = PathCoordinates;
        waweList = new ArrayList<>();
        setupEnemies();
    }

    public void setupEnemies() {
        Wave wave1 = new Wave();
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        this.addWave(wave1);
    }

    @Override
    public void start(float delta, SpriteBatch batch) {
        Wave wave = waweList.get(currentWave);
        wave.startWave(delta, wave, batch);
    }

    public void addWave(Wave wave) {
        waweList.add(wave);
    }
}
