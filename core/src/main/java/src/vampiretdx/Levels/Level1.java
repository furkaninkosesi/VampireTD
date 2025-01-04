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
        float[][] screenPathCoordinates = {
                { 565f, 32f },
                { 187f, 31f },
                { 106f, 82.0f },
                { 106f, 82.0f },
                { 106f, 82.0f },
                { 106f, 123f },
                { 388f, 138.0f },
                { 436, 387.0f }
        };
        this.PathCoordinate = screenPathCoordinates;
        waweList = new ArrayList<>();
    }

    public void setupEnemies() {

        Wave wave1 = new Wave();
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));        
        wave1.addEnemy(new Vampire(this));
        wave1.addEnemy(new Vampire(this));
        this.addWave(wave1);
        Wave wave2 = new Wave();
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        wave2.addEnemy(new Vampire(this));
        this.addWave(wave2);
    }

    @Override
    public void start(float delta, SpriteBatch batch) {
        Wave wave = waweList.get(currentWave);
        wave.startWave(delta, wave, batch);
    }

    public void addWave(Wave wave) {
        waweList.add(wave);
    }

    public ArrayList<Wave> getWaweList() {
        return waweList;
    }

    @Override
    public Wave getCurrentWave() {
        return waweList.get(currentWave);
    }
}
