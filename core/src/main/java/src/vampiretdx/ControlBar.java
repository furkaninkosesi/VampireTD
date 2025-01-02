package src.vampiretdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
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

import src.vampiretdx.Towers.Tower;

public class ControlBar {
    private Stage stage;
    private Table mainTable;
    private Label moneyLabel;
    private Label healthLabel;
    private boolean isPlacingTower = false;
    private float mouseX, mouseY;
    private Tower selectedTower;

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
        mainTable.add(topSection).expandX().pad(10).top().row();

        // Middle section (Tower icons)
        Table middleSection = new Table();
        TextureRegionDrawable boxBackground = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal("box.png"))));

        // Create tower buttons
        Tower[] towers = {
                new Tower("Archer Tower", new Texture(Gdx.files.internal("towers/archer_tower.png")), 10, 100, 150,
                        1.0f),
                new Tower("Archer Tower", new Texture(Gdx.files.internal("towers/archer_tower.png")), 10, 100, 150,
                        1.0f),
                new Tower("Archer Tower", new Texture(Gdx.files.internal("towers/archer_tower.png")), 10, 100, 150,
                        1.0f),
                new Tower("Archer Tower", new Texture(Gdx.files.internal("towers/archer_tower.png")), 10, 100, 150,
                        1.0f),
                new Tower("Archer Tower", new Texture(Gdx.files.internal("towers/archer_tower.png")), 10, 100, 150,
                        1.0f),
                new Tower("Archer Tower", new Texture(Gdx.files.internal("towers/archer_tower.png")), 10, 100, 150,
                        1.0f),
                new Tower("Archer Tower", new Texture(Gdx.files.internal("towers/archer_tower.png")), 10, 100, 150,
                        1.0f),
                new Tower("Archer Tower", new Texture(Gdx.files.internal("towers/archer_tower.png")), 10, 100, 150,
                        1.0f)
                // Add more towers here as needed
        };

        // Add towers in two columns per row
        for (int i = 0; i < towers.length; i++) {
            Tower tower = towers[i];
            Table cellTable = new Table();
            cellTable.setBackground(boxBackground);

            ImageButton towerButton = createButton(tower.texture);
            cellTable.add(towerButton).size(40, 40).center().pad(5);
            middleSection.add(cellTable).size(50, 50).pad(5);

            // Create a new row after two towers
            if ((i + 1) % 2 == 0) {
                middleSection.row();
            }

            // Handle tower button interaction
            final Tower currentTower = tower;
            towerButton.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    isPlacingTower = true;
                    selectedTower = currentTower;

                    System.out.println("Tower placement mode activated: " + selectedTower.name);
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    isPlacingTower = false;
                    selectedTower = null;
                    System.out.println("Touch released!");
                }
            });
        }

        mainTable.add(middleSection).expandX().pad(10).row();

        // Bottom section (End game button)
        // ... (Same as before)
    }

    private ImageButton createButton(Texture texture) {
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        return new ImageButton(drawable);
    }

    public void updateMouseCoordinates(float mouseX, float mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public void render(Batch batch) {
        if (isPlacingTower && selectedTower != null) {
            batch.begin();
            batch.draw(selectedTower.texture, mouseX, mouseY, selectedTower.getTexture().getWidth() * 0.1f,
            selectedTower.getTexture().getHeight()* 0.1f);
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
        mainTable.setSize(width * 0.2f, height); // Control bar takes 20% of the screen width
    }
}
