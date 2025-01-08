package src.vampiretdx.Towers;

import com.badlogic.gdx.graphics.Texture;

import src.vampiretdx.Enemies.Enemy;

public class Projectiles {
    private float x, y; // Okun pozisyonu
    private float speed; // Okun hızı
    private int damage; // Okun hasarı
    private float targetX, targetY; // Hedef pozisyonu
    private Texture texture; // Okun görseli
    private boolean hitTarget = false; // Hedefe ulaşıp ulaşmadığını kontrol eder
    private boolean active = true;
    private float timeSinceLastMove = 0;
    private float rotation;

    public Projectiles(float x, float y, float targetX, float targetY, float speed, int damage, Texture texture) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.speed = speed;
        this.damage = damage;
        this.texture = texture;
    }
    public float getRotation() {
        return rotation;
    }
    
    public boolean isActive() {
        return active;
    }

    public void update(float deltaTime) {
        // Hedefe doğru hareket et
        rotation = (float) Math.toDegrees(Math.atan2(targetY - y, targetX - x));
        float dx = targetX - x;
        float dy = targetY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        float oldX = x;
        float oldY = y;
        if (distance > speed * deltaTime) {
            x += (dx / distance) * speed * deltaTime;
            y += (dy / distance) * speed * deltaTime;
        } else {
            x = targetX;
            y = targetY;
            hitTarget = true; // Hedefe ulaşıldı
        }
        if (x == oldX && y == oldY) {
            timeSinceLastMove += deltaTime;
        } else {
            timeSinceLastMove = 0; // Hareket varsa sıfırla
        }

        // Eğer 2 saniyeden fazla hareket etmiyorsa devre dışı bırak
        if (timeSinceLastMove > 1f) {
            active = false;
        }
    }

    public boolean checkCollision(Enemy enemy) {
        float dx = enemy.getX() - x;
        float dy = enemy.getY() - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance <= 10;
    }

    public boolean hasHitTarget() {
        return hitTarget;
    }

    public int getDamage() {
        return damage;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getTexture() {
        return texture;
    }
}
