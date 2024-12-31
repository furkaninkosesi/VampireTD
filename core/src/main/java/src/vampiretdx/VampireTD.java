package src.vampiretdx;
import com.badlogic.gdx.Game;

public class VampireTD extends Game {
    @Override
    public void create() {
        this.setScreen(new LevelSelectScreen(this)); // Seviye seçme ekranını başlat
    }

    @Override
    public void render() {
        super.render(); // Geçerli ekranı render et
    }

    @Override
    public void dispose() {
        super.dispose(); // Geçerli ekranı temizle
    }
}