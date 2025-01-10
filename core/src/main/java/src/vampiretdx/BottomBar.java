package src.vampiretdx;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.Texture;
import src.vampiretdx.Towers.Tower;

public class BottomBar {
    private Stage stage;
    private Table table;

    private Tower selectedTower;
    private Label damageLabel;
    private Label rangeLabel;
    private Label upgradeCostLabel;
    private SelectBox<String> priorityBox;
    private TextButton upgradeButton;

    private ControlBar controlBar;

    public BottomBar(Stage stage, ControlBar controlBar) {
        this.stage = stage;
        this.controlBar = controlBar;
        createUI();
    }

    private void createUI() {
        table = new Table();
        table.setFillParent(true);
        table.bottom();

        damageLabel = new Label("Damage: 0", new Label.LabelStyle());
        rangeLabel = new Label("Range: 0", new Label.LabelStyle());
        upgradeCostLabel = new Label("Upgrade Cost: 0", new Label.LabelStyle());

        priorityBox = new SelectBox<>(new SelectBox.SelectBoxStyle());
        priorityBox.setItems("First", "Strong", "Last", "Weak");
        priorityBox.addListener(event -> {
            if (selectedTower != null) {
                selectedTower.setPriority(priorityBox.getSelected());
            }
            return true;
        });

        // Upgrade butonu
        upgradeButton = new TextButton("Upgrade", new TextButton.TextButtonStyle());
        upgradeButton.addListener(event -> {
            if (selectedTower != null) {
                int upgradeCost = selectedTower.getUpgradeCost();
                if (controlBar.spendMoney(upgradeCost)) {
                    selectedTower.upgrade();
                    updateTowerInfo(selectedTower);
                } else {
                    System.out.println("Not enough money to upgrade!");
                }
            }
            return true;
        });

        // Tabloya ekle
        table.add(damageLabel).pad(10);
        table.row();
        table.add(rangeLabel).pad(10);
        table.row();
        table.add(upgradeCostLabel).pad(10);
        table.row();
        table.add(priorityBox).pad(10);
        table.row();
        table.add(upgradeButton).pad(10);

        stage.addActor(table);
    }

    // Kuleye tıklanınca bilgiler güncellenir
    public void TowerClicked(Tower tower) {
        this.selectedTower = tower;
        updateTowerInfo(tower);
    }

    // Kulenin bilgilerini günceller
    private void updateTowerInfo(Tower tower) {
        damageLabel.setText("Damage: " + tower.getDamage());
        rangeLabel.setText("Range: " + tower.getRange());
        upgradeCostLabel.setText("Upgrade Cost: " + tower.getUpgradeCost());

        // Öncelik ayarını güncelle // Kulenin bilgilerini günceller

        priorityBox.setSelected(tower.getPriority());
    }
}
// Kulenin bilgilerini günceller
