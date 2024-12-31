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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import src.vampiretdx.Levels.Level;

public class GameScreen implements Screen {
    protected SpriteBatch batch;
    protected Texture mapTexture;
    protected Stage stage;
    protected Level level;
    protected VampireTD game;
    protected OrthographicCamera cam;
    protected Viewport viewport;
    private final float worldWidth = 800; // Dünya genişliği
    private final float worldHeight = 600;
    private ControlBar controlBar;

    public GameScreen(VampireTD game, Level level) {
        this.game = game;
        this.level = level;
        this.batch = new SpriteBatch();
        this.stage = new Stage();

        cam = new OrthographicCamera();
        cam.position.set(worldWidth / 2, worldHeight / 2, 0);
        cam.update();

        viewport = new ExtendViewport(worldWidth, worldHeight, cam); // Oranlı görünüm
        viewport.apply(); // Viewport'u uygula

        Gdx.input.setInputProcessor(stage); // Input ayarları
        level.setCam(cam); // Kamerayı seviyeye ilet
        level.setupEnemies();

        controlBar = new ControlBar(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // -----------------------------------------------------------------------------
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float screenX = Gdx.input.getX();
            float screenY = Gdx.input.getY();
            Vector3 worldCoords = screenToWorld(screenX, screenY);
            System.out.println("Dünya Koordinatları: " + worldCoords.x + ", " + worldCoords.y);
        }
        // -----------------------------------------------------------------------------

        batch.setProjectionMatrix(cam.combined);

        batch.begin();//---------------------------------

        level.render(batch); 
        level.start(delta, batch);
        
        batch.end();//---------------------------------


        stage.act(delta); // Sahne animasyonlarını ve güncellemeleri çalıştırır
        stage.draw();
    }

    private com.badlogic.gdx.math.Vector3 screenToWorld(float screenX, float screenY) {
        // Ekrandaki tıklamayı kamera ile dünya koordinatlarına dönüştür
        com.badlogic.gdx.math.Vector3 worldCoords = new com.badlogic.gdx.math.Vector3(screenX, screenY, 0);
        cam.unproject(worldCoords); // Koordinatları kameranın perspektifine göre dönüştür
        return worldCoords;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        cam.setToOrtho(false, 640, 480); // Kamera merkezini ayarla
        cam.update();
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
        stage.dispose();
        batch.dispose();
    }

}
