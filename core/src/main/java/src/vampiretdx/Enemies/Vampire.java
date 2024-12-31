package src.vampiretdx.Enemies;

import src.vampiretdx.Levels.Level;

import com.badlogic.gdx.graphics.Texture;

public class Vampire extends Enemy {
    public Vampire(Level level) {
        super("vampire", 100f, 40, new Texture("enemies/vampire.png"), level);
    }
}
