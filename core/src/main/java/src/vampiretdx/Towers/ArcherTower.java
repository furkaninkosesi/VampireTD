package src.vampiretdx.Towers;

import com.badlogic.gdx.graphics.Texture;

public class ArcherTower extends Tower {
    public ArcherTower() {
        super("Archer", new Texture("towers/archer_tower.png"), 15, 100, 200, 220, 0.6f);
    }
    public ArcherTower(float x, float y) {
        super("Archer", new Texture("towers/archer_tower.png"), 100, 100, 2000, 2200, 2f, x, y);
    }
}