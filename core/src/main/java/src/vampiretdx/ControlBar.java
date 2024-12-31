package src.vampiretdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;

public class ControlBar {
    private Table table;
    private Stage stage;
    private TextButton nextWaveButton;
    private Label selectedTowerLabel;

    public ControlBar(Stage stage) {
        this.stage = stage;
    
        // BitmapFont oluşturma (Default font)
        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"));
    
        // LabelStyle tanımlama
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
    
        // TextButtonStyle tanımlama
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font; // Fontu buraya ekle
        textButtonStyle.fontColor = Color.WHITE; // İsteğe bağlı olarak yazı rengi
    
        // Table (Bar) Oluşturma
        table = new Table();
        table.setFillParent(true); // Tam ekranın bir kenarına yapışır
        table.align(Align.bottom);
    
        // Kule Seçimi için Label
        selectedTowerLabel = new Label("Selected Tower: None", labelStyle);
        selectedTowerLabel.setAlignment(Align.center);
    
        // Next Wave Butonu
        nextWaveButton = new TextButton("Start Next Wave", textButtonStyle);
        nextWaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Next Wave Başlıyor!");
            }
        });
    
        // Bar'a Eklemeler
        table.add(selectedTowerLabel).pad(10).expandX().left();
        table.add(nextWaveButton).pad(10).expandX().right();
    
        stage.addActor(table); // Sahneye ekle
    }
    
    public void updateSelectedTower(String towerName) {
        selectedTowerLabel.setText("Selected Tower: " + towerName);
    }

    public Actor getActor() {
        return table;
    }
}
