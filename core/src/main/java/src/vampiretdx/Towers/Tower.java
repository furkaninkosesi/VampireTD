package src.vampiretdx.Towers;

import com.badlogic.gdx.graphics.Texture;

public class Tower {
    public String name;
    public Texture texture;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public float getBullet_speed() {
        return bullet_speed;
    }

    public void setBullet_speed(float bullet_speed) {
        this.bullet_speed = bullet_speed;
    }
}
