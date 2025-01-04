package src.vampiretdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.InputMultiplexer;

import src.vampiretdx.Levels.Level;
import src.vampiretdx.Towers.Tower;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private Stage uiStage;
    private Stage barStage;
    private Level level;
    private VampireTD game;

    private OrthographicCamera cam;
    private OrthographicCamera uiCamera;
    private OrthographicCamera barCamera;

    private Viewport viewport;
    private Viewport uiViewport;
    private Viewport barViewport;

    private final float WORLD_WIDTH = 800;
    private final float WORLD_HEIGHT = 600;

    private ControlBar controlBar;
    private Vector3 worldCoords;

    public GameScreen(VampireTD game, Level level) {
        this.game = game;
        this.level = level;
        this.batch = new SpriteBatch();

        // Kameralar
        cam = new OrthographicCamera();
        uiCamera = new OrthographicCamera();
        barCamera = new OrthographicCamera();

        // Viewportlar (StretchViewport kullanıldı)
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, cam);
        uiViewport = new StretchViewport(WORLD_WIDTH * 0.2f, WORLD_HEIGHT, uiCamera);
        barViewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT*0.1f, barCamera);


        stage = new Stage(viewport, batch);
        uiStage = new Stage(uiViewport, batch);
        barStage = new Stage(barViewport, batch);

        uiStage.setDebugAll(true);

        controlBar = new ControlBar(uiStage);
        Gdx.input.setInputProcessor(new InputMultiplexer(uiStage, stage));

        cam.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);
        cam.update();

        uiCamera.position.set(WORLD_WIDTH * 0.1f, WORLD_HEIGHT / 2f, 0);
        uiCamera.update();

        level.setCam(cam);
        level.setupEnemies();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float screenX = Gdx.input.getX();
        float screenY = Gdx.input.getY();
        worldCoords = screenToWorld(screenX, screenY);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            System.out.println("Dünya Koordinatları: " + worldCoords.x + ", " + worldCoords.y);
        }

        controlBar.updateMouseCoordinates(worldCoords.x*1.25f, worldCoords.y);

        viewport.apply();
        batch.setProjectionMatrix(cam.combined);

        batch.begin();

        level.render(batch);
        level.start(delta, batch);

        batch.end();

        controlBar.render(batch);

        for (Tower tower : controlBar.getPlacedTowers()) {
            tower.update(delta, level.getCurrentWave().getEnemies());
        }

        batch.begin();
        for (Tower tower : controlBar.getPlacedTowers()) {
            tower.render(batch);
        }
        batch.end();

        stage.act(delta);
        stage.draw();

        // UI'yi çiz
        uiViewport.apply();
        batch.setProjectionMatrix(uiCamera.combined);

        uiStage.act(delta);
        uiStage.draw();
    }

    private Vector3 screenToWorld(float screenX, float screenY) {
        Vector3 worldCoords = new Vector3(screenX, screenY, 0);
        cam.unproject(worldCoords);
        return worldCoords;
    }

    @Override
    public void resize(int width, int height) {
        int uiWidth = (int) (width * 0.15f);
        int gameWidth = (int) (width - uiWidth);
        int gameHeight = (int)(height*0.85);
        int boundHeight = height - gameHeight;
        
        uiViewport.setScreenBounds(gameWidth, 0, uiWidth, height);
        viewport.setScreenBounds(0, boundHeight, gameWidth, gameHeight);
        barViewport.setScreenBounds(0, 0, gameWidth, boundHeight);

        barCamera.update();
        cam.update();
        uiCamera.update();

        controlBar.resize(uiWidth, height);
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
        uiStage.dispose();
        batch.dispose();
    }
}
