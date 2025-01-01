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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.InputMultiplexer;

import src.vampiretdx.Levels.Level;

public class GameScreen implements Screen {
    protected SpriteBatch batch;
    protected Texture mapTexture;
    protected Stage stage;
    protected Level level;
    protected VampireTD game;
    protected OrthographicCamera cam;
    protected OrthographicCamera uiCamera;
    protected Viewport viewport, uiViewport;
    private final float worldWidth = 800;
    private final float worldHeight = 600;
    private ControlBar controlBar;
    private Stage uiStage;

    public GameScreen(VampireTD game, Level level) {
        this.game = game;
        this.level = level;
        this.batch = new SpriteBatch();

        cam = new OrthographicCamera();
        uiCamera = new OrthographicCamera();

        // Oyun ve UI için viewport'ları tanımla
        viewport = new FitViewport(worldWidth, worldHeight, cam);
        uiViewport = new FitViewport(worldWidth * 0.2f, worldHeight, uiCamera);

        // Stage'leri oluştur
        stage = new Stage(viewport, batch);
        uiStage = new Stage(uiViewport, batch);

        // Kamera ayarları
        cam.position.set(worldWidth / 2f, worldHeight / 2f, 0);
        cam.update();
        uiCamera.update();

        // ControlBar
        controlBar = new ControlBar(uiStage);

        // InputMultiplexer ile input işlemlerini iki sahneye yönlendir
        Gdx.input.setInputProcessor(new InputMultiplexer(uiStage, stage));

        // Level ayarları
        level.setCam(cam);
        level.setupEnemies();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Oyun alanını çiz
        viewport.apply();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        level.render(batch);
        level.start(delta, batch);
        batch.end();

        stage.act(delta);
        stage.draw();

        // UI'yi çiz
        uiViewport.apply();
        batch.setProjectionMatrix(uiCamera.combined);
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Ekran boyutlarını böl
        int gameWidth = (int) (width * 0.8f);
        int uiWidth = width - gameWidth;

        // Viewport'ları güncelle
        viewport.update(gameWidth, height, true);
        viewport.setScreenBounds(0, 0, gameWidth, height);

        uiViewport.update(uiWidth, height, true);
        uiViewport.setScreenBounds(gameWidth, 0, uiWidth, height);

        // Kameraları yeniden yapılandır
        cam.setToOrtho(false, worldWidth, worldHeight);
        cam.update();
        uiCamera.setToOrtho(false, worldWidth * 0.2f, worldHeight);
        uiCamera.update();
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        uiStage.dispose();
        batch.dispose();
    }
}
