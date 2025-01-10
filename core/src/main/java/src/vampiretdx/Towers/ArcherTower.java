package src.vampiretdx.Towers;

import com.badlogic.gdx.graphics.Texture;

public class ArcherTower extends Tower {
    private int cost = 200;
    private static final int[] UPGRADED_COST = { 100, 200, 300 };

    public ArcherTower() {
        super("Archer", new Texture("towers/archer_tower.png"), 15, 100, 200, 220, 0.6f);
    }

    public ArcherTower(float x, float y) {
        super("Archer", new Texture("towers/archer_tower.png"), 100, 100, 2000, 200, 1f, x, y, UPGRADED_COST);
    }

    public int getCost() {
        return cost;
    }
}