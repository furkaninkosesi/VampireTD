package src.vampiretdx.Enemies;

import src.vampiretdx.Levels.Level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy {
    protected String type;
    protected float health;
    protected float speed;
    protected float x, y;
    protected boolean slowed;
    protected Texture texture;
    protected Level level;
    private int currentPathIndex = 0;
    private float[][] pathCoordinates;
    public Enemy(String type, float health, float speed, Texture texture, Level level) {
        this.type = type;
        this.health = health;
        this.speed = speed;
        this.texture = texture;
        this.slowed = false;
        this.pathCoordinates = level.getPathCoordinate();
    }

    public void move(float delta) {
        // Eğer yol bitti ise, hareket etmeyi durdurabilirsin
        if (currentPathIndex >= pathCoordinates.length - 1) {
            return; // Son koordinata ulaştı
        }

        // Şu anki hedef koordinat
        float targetX = pathCoordinates[currentPathIndex + 1][0];
        float targetY = pathCoordinates[currentPathIndex + 1][1];

        // X ve Y farkları (hedef ile şu anki koordinat arasındaki mesafe)
        float dx = targetX - x;
        float dy = targetY - y;

        // Mesafenin uzunluğunu bul (Pythagoras teoremi ile)
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        // Eğer mesafe yeterince küçükse, bir sonraki koordinata geç
        if (distance < speed * delta) {
            x = targetX;
            y = targetY;
            currentPathIndex++; // Bir sonraki noktaya geç
        } else {
            // Hedefe doğru hareket et
            float moveX = dx / distance * speed * delta;
            float moveY = dy / distance * speed * delta;

            // Yeni pozisyona ilerle
            x += moveX;
            y += moveY;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
