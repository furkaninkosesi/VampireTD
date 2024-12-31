package src.vampiretdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import src.vampiretdx.Levels.Level;

public class GameScreen implements Screen {
    protected SpriteBatch batch;
    protected Texture mapTexture;
    protected Stage stage;
    protected Level level;
    protected VampireTD game;
    protected OrthographicCamera cam = new OrthographicCamera();

    public GameScreen(VampireTD game, Level level) {
        this.game = game;
        this.level = level;
        this.batch = new SpriteBatch();
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Ekran boyutlarını ayarlamak
        System.out.println(Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            // Ekrandaki tıklama koordinatlarını al
            float screenX = Gdx.input.getX();
            float screenY = Gdx.input.getY();

            // Kamera ile dünya koordinatlarına dönüştür
            cam.unproject(screenToWorld(screenX, screenY));

            System.out.println("Maus Tıklanan Koordinat: " + screenX + ", " + screenY);
            System.out.println("Dünya Koordinatları: " + cam.position.x + ", " + cam.position.y);
            Vector3 worldCoords = cam.unproject(new Vector3(screenX, screenY, 0));
            System.out.println("Dünya Koordinatları: " + worldCoords.x + ", " + worldCoords.y);


        }
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        
        level.render(cam, batch); // Aktif seviyeyi çiz
        level.start(delta, batch);
        batch.end();
    }

    private com.badlogic.gdx.math.Vector3 screenToWorld(float screenX, float screenY) {
        // Ekrandaki tıklamayı kamera ile dünya koordinatlarına dönüştür
        com.badlogic.gdx.math.Vector3 worldCoords = new com.badlogic.gdx.math.Vector3(screenX, screenY, 0);
        cam.unproject(worldCoords); // Koordinatları kameranın perspektifine göre dönüştür
        return worldCoords;
    }

    @Override
    public void resize(int width, int height) {
        cam.setToOrtho(false, width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
