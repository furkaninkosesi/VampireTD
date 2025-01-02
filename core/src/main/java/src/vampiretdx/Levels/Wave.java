package src.vampiretdx.Levels;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import src.vampiretdx.Enemies.Enemy;

public class Wave {
    protected ArrayList<Enemy> enemies;
    protected int wave;
    private int nextEnemyIndex = 0; // Bir sonraki spawnlanacak düşman indeksi
    private float spawnTimer = 0;

    public Wave() {
        enemies = new ArrayList<>();
        wave = 1;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    public void startWave(float delta, Wave wave, SpriteBatch batch) {
        // Spawn zamanlayıcısını güncelle
        spawnTimer += delta;

        // Eğer 0.5 saniye geçtiyse sıradaki düşmanı spawnla
        if (nextEnemyIndex < enemies.size() && spawnTimer >= 0.3f) {
            // Yeni düşmanı spawnla
            System.out.println("Spawnlanan düşman: " + nextEnemyIndex); // Kontrol için log
            spawnTimer = 0; // Zamanlayıcı sıfırla
            nextEnemyIndex++; // Bir sonraki düşmana geç
        }
        enemies.removeIf(enemy -> !enemy.isActive());
        // Zaten spawnlanmış düşmanları render et ve hareket ettir
        for (int i = 0; i < nextEnemyIndex && i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.isActive()) { // Sadece aktif düşmanları işliyoruz
                enemy.move(delta);
                enemy.render(batch);
            }
        }
    }
}
