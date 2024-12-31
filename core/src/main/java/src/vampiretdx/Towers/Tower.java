package src.vampiretdx.Towers;

import com.badlogic.gdx.graphics.Texture;

public class Tower {
    String name;
    Texture texture;

    int rank = 0;
    int damage;
    int price;
    int range;
    float bullet_speed;

    public Tower(String name, Texture texture, int damage, int price, int range, float bullet_speed) {
        this.name = name;
        this.texture = texture;
        this.damage = damage;
        this.price = price;
        this.range = range;
        this.bullet_speed = bullet_speed;
    }
}
