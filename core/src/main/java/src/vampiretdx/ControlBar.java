package src.vampiretdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ControlBar{
    private Stage stage;
    private Table table;

    // UI Elemanları
    private Label moneyLabel;
    private Label healthLabel;

    // Kule Yerleştirme
    private Texture selectedTowerTexture; // Seçilen kule ikonu
    private boolean isPlacingTower = false; // Kule yerleştirme aktif mi?
    private float mouseX, mouseY; // Fare pozisyonu

    public ControlBar(Stage stage)  {
        this.stage = stage;

        // Table ayarları
        table = new Table();
        table.top().right();
        table.pad(10); // Kenarlık

        // Font ve Skin ayarları
        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"));
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, null);
        // Para ve Can göstergeleri
        moneyLabel = new Label("Money: 1000", labelStyle);
        healthLabel = new Label("Health: 100", labelStyle);
        moneyLabel.setSize(50, 20);
        table.add(moneyLabel).padBottom(20).row();
        table.add(healthLabel).padBottom(20).row();

        // Kule simgeleri (örnek)
        for (int i = 0; i < 10; i++) {
            ImageButton towerButton = createButton("towers/archer_tower.png");
            table.add(towerButton).size(50, 50).padBottom(10).row();

            int finalI = i; // Lambda içi için gerekli
            towerButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedTowerTexture = new Texture(Gdx.files.internal("towers/archer_tower.png"));
                    isPlacingTower = true;
                    System.out.println("Tower " + finalI + " selected for placement!");
                }
            });
        }

        // ScrollPane oluşturma
        ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setFillParent(true);
        scrollPane.setScrollingDisabled(true, false); // Yatay kaydırma devre dışı, dikey etkin

        // Özel kontroller (örnek)
        ImageButton endGameButton = createButton("buttons/go_button.png");
        table.add(endGameButton).size(100, 50).padTop(20);

        endGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("End Game clicked!");
            }
        });

        stage.addActor(scrollPane);

        // Fare hareketlerini takip eden InputProcessor ekleme
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (isPlacingTower) {
                    System.out.println("Tower placed at: " + mouseX + ", " + mouseY);
                    placeTower(mouseX, mouseY);
                    isPlacingTower = false;
                    selectedTowerTexture = null;
                }
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                mouseX = screenX;
                mouseY = Gdx.graphics.getHeight() - screenY; // Y ekseni ters olduğu için
                return true;
            }
        });
    }

    private ImageButton createButton(String texturePath) {
        Texture texture = new Texture(Gdx.files.internal(texturePath));
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        return new ImageButton(drawable);
    }

    public void updateMoney(int newMoney) {
        moneyLabel.setText("Money: " + newMoney);
    }

    public void updateHealth(int newHealth) {
        healthLabel.setText("Health: " + newHealth);
    }

    private void placeTower(float x, float y) {
        System.out.println("Placing tower at: " + x + ", " + y);
        // Buraya kule yerleştirme mantığı eklenebilir (örneğin, bir sahne objesi ekleme)
    }

    public void render(Batch batch) {
        if (isPlacingTower && selectedTowerTexture != null) {
            batch.begin();
            batch.draw(selectedTowerTexture, mouseX - selectedTowerTexture.getWidth() / 2, mouseY - selectedTowerTexture.getHeight() / 2);
            batch.end();
        }
    }


}
