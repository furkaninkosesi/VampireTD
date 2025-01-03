package src.vampiretdx;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import src.vampiretdx.Towers.ArcherTower;
import src.vampiretdx.Towers.Tower;

public class ControlBar {
    private Stage stage;
    private Table mainTable;
    private Label moneyLabel;
    private Label healthLabel;
    private boolean isPlacingTower = false;
    private float mouseX, mouseY;
    private Tower selectedTower;
    private ArrayList<Tower> placedTowers = new ArrayList<>();

    public ControlBar(Stage stage) {
        this.stage = stage;

        // Main table setup
        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().right();
        stage.addActor(mainTable);

        // Background
        Texture backgroundTexture = new Texture(Gdx.files.internal("controlbar.png"));
        mainTable.setBackground(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));

        // Font and Style
        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, null);

        // Money and Health labels
        moneyLabel = new Label("Money: 1000", labelStyle);
        healthLabel = new Label("Health: 100", labelStyle);

        // Top section (Money and Health)
        Table topSection = new Table();
        topSection.add(moneyLabel).expandX().padBottom(10);
        topSection.row();
        topSection.add(healthLabel).expandX().padBottom(10);

        mainTable.add(topSection).expandX().pad(45, 0, 0, 0).top().row();

        // Middle section (Tower icons)
        Table middleSection = new Table();
        TextureRegionDrawable boxBackground = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal("box.png"))));

        Tower[] towers = {
                new ArcherTower(),
                new ArcherTower(),
                new ArcherTower(),
                new ArcherTower(),
                new ArcherTower(),
                new ArcherTower(),
                new ArcherTower(),
                new ArcherTower()

        };

        for (int i = 0; i < towers.length; i++) {
            Tower tower = towers[i];

            Table cellTable = new Table();
            cellTable.setBackground(boxBackground);

            TextureRegion towerRegion = new TextureRegion(tower.getTexture());
            ImageButton towerButton = new ImageButton(new TextureRegionDrawable(towerRegion));
            cellTable.add(towerButton).size(60, 60);
            middleSection.add(cellTable).size(70, 70).pad(10, 5, 25, 5).expand();

            if ((i + 1) % 2 == 0) {
                middleSection.row();
            }

            // Handle tower button interaction
            Tower currentTower = tower;
            towerButton.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    isPlacingTower = true;
                    selectedTower = createNewTower(currentTower);
                    System.out.println("Tower placement mode activated: " + selectedTower.name);
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    isPlacingTower = false;
                    if (selectedTower != null) {
                        // Yerleştirme işlemi
                        placeTower(mouseX, mouseY);
                    }
                    selectedTower = null;
                    System.out.println("Touch released!");
                }
            });
        }

        mainTable.add(middleSection).expandX().pad(10).row();
    }

    private void placeTower(float x, float y) {
        if (selectedTower != null) {
            Tower newTower = createTowerInstance(selectedTower, x, y);
            placedTowers.add(newTower);
            System.out.println("Tower placed at: " + x + ", " + y);
        }
    }

    public ArrayList<Tower> getPlacedTowers() {
        return placedTowers;
    }

    private Tower createTowerInstance(Tower tower, float x, float y) {
        Tower newTower = null;
        if (tower instanceof ArcherTower) {
            newTower = new ArcherTower();
        }
        newTower.setPosition(x, y);
        return newTower;
    }

    private Tower createNewTower(Tower tower) {
        if (tower instanceof ArcherTower) {
            return new ArcherTower();
        } else if (tower instanceof ArcherTower) {
            return new ArcherTower();
        }
        return null;
    }

    public void updateMouseCoordinates(float mouseX, float mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public void render(Batch batch) {
        if (isPlacingTower && selectedTower != null) {
            batch.begin();
            batch.draw(selectedTower.texture, mouseX, mouseY, 50,
                50);
            batch.end();
        }
    }

    public void updateMoney(int newMoney) {
        moneyLabel.setText("Money: " + newMoney);
    }

    public void updateHealth(int newHealth) {
        healthLabel.setText("Health: " + newHealth);
    }

    public void resize(int width, int height) {
        mainTable.setSize(width * 0.2f, height);
    }
}