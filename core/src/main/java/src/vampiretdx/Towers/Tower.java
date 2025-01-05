package src.vampiretdx.Towers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import src.vampiretdx.Config;
import src.vampiretdx.Enemies.Enemy;

public class Tower {
    public String name;
    public Texture texture;
    public int rank = 0;
    public int damage;
    public int price;
    public int range;
    public float bullet_speed;
    private ArrayList<Projectiles> projectiles = new ArrayList<>();
    private float attackCooldown;; // Saniyede bir atış
    private float timeSinceLastAttack = 0f;
    private float x, y; //

    public Tower(String name, Texture texture, int damage, int price, int range, float bullet_speed,
            float attackCooldown) {
        this.name = name;
        this.texture = texture;
        this.damage = damage;
        this.price = price;
        this.range = range;
        this.bullet_speed = bullet_speed;
        this.attackCooldown = attackCooldown;
    }

    public void update(float deltaTime, ArrayList<Enemy> enemies) {
        timeSinceLastAttack += deltaTime;

        // Atış yap
        if (timeSinceLastAttack >= attackCooldown) {
            Enemy target = findClosestEnemy(enemies);
            if (target != null) {
                shootAt(target);
                timeSinceLastAttack = 0f;
            }

            for (int i = projectiles.size() - 1; i >= 0; i--) {
                Projectiles projectile = projectiles.get(i);
                projectile.update(deltaTime);

                // Eğer ok aktif değilse listeden kaldır
                if (!projectile.isActive()) {
                    projectiles.remove(i);
                }
            }
        }

        ArrayList<Projectiles> projectilesToRemove = new ArrayList<>();
        for (Projectiles projectile : projectiles) {
            projectile.update(deltaTime);

            for (Enemy enemy : enemies) {
                if (projectile.checkCollision(enemy)) {
                    enemy.takeDamage(projectile.getDamage());
                    projectilesToRemove.add(projectile); 
                    break;
                }
            }
        }

        // Çarpışan okları kaldır
        projectiles.removeAll(projectilesToRemove);

        // Ölen düşmanları kaldır
        enemies.removeIf(enemy -> enemy.getHealth() <= 0);
    }

    private Enemy findClosestEnemy(ArrayList<Enemy> enemies) {
        Enemy closest = null;
        float closestDistance = Float.MAX_VALUE;

        for (Enemy enemy : enemies) {
            float dx = enemy.getX() - x;
            float dy = enemy.getY() - y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance < range && distance < closestDistance) {
                closest = enemy;
                closestDistance = distance;
            }
        }
        return closest;
    }

    private void shootAt(Enemy enemy) {
        Projectiles projectile = new Projectiles(x, y, enemy.getX(), enemy.getY(), bullet_speed, damage, new Texture("arrow.png"));
        projectiles.add(projectile);
    }

    public ArrayList<Projectiles> getProjectiles() {
        return projectiles;
    }

    public void render(Batch batch) {
        // Tower'ı çiz
        batch.draw(texture, x, y, Config.TOWER_SIZE_X, Config.TOWER_SIZE_Y);

        // Okları çiz
        for (Projectiles projectile : projectiles) {
            // batch.draw(projectile.getTexture(), projectile.getX(), projectile.getY(), 50, 50);
            batch.draw(
                    projectile.getTexture(),
                    projectile.getX(), projectile.getY(),
                    25, 25,
                    50, 50,
                    1, 1,
                    projectile.getRotation(),
                    0, 0,
                    projectile.getTexture().getWidth(), projectile.getTexture().getHeight(),
                    false, false);
            System.out.println(projectile.getX() + " + " + projectile.getY());
        }
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getBullet_speed() {
        return bullet_speed;
    }

    public void setBullet_speed(float bullet_speed) {
        this.bullet_speed = bullet_speed;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
