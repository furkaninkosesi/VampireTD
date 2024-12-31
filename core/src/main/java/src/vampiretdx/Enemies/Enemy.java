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
    protected float relativeX, relativeY;
    private boolean isActive = true;

    public Enemy(String type, float health, float speed, Texture texture, Level level) {
        this.type = type;
        this.health = health;
        this.speed = speed;
        this.texture = texture;
        this.slowed = false;
        this.level = level;
        this.pathCoordinates = level.getPathCoordinate();

        this.x = pathCoordinates[0][0]; // Başlangıç noktası
        this.y = pathCoordinates[0][1];

    }

    public void move(float delta) {
        if (!isActive)
            return;
        if (currentPathIndex >= pathCoordinates.length - 1) {
            isActive = false;
            return; // Son koordinata ulaştı
        }

        // Hedef koordinat
        float targetX = pathCoordinates[currentPathIndex + 1][0];
        float targetY = pathCoordinates[currentPathIndex + 1][1];

        // Hedefe olan mesafeyi hesapla
        float dx = targetX - x;
        float dy = targetY - y;
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        // Eğer hedefe yaklaştıysak, bir sonraki koordinata geç
        if (distance < speed * delta) {
            x = targetX;
            y = targetY;
            currentPathIndex++;
        } else {
            // Hedefe doğru hareket et
            float moveX = dx / distance * speed * delta;
            float moveY = dy / distance * speed * delta;

            x += moveX;
            y += moveY;
        }
    }

    public void render(SpriteBatch batch) {
        if (isActive) {
            batch.draw(texture, x, y, 25, 25);
        }
    }

    public void dispose() {
        texture.dispose();
    }

    public boolean isActive() {
        return isActive;
    }
}
