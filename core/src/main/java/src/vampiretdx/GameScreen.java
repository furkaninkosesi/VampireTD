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
import src.vampiretdx.Towers.Tower;

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
    Vector3 worldCoords;
    public GameScreen(VampireTD game, Level level) {
        this.game = game;
        this.level = level;
        this.batch = new SpriteBatch();

        cam = new OrthographicCamera();
        uiCamera = new OrthographicCamera();

        viewport = new FitViewport(worldWidth, worldHeight, cam);
        uiViewport = new FitViewport(worldWidth * 0.2f, worldHeight, uiCamera);

        stage = new Stage(viewport, batch);
        uiStage = new Stage(uiViewport, batch);

        cam.position.set(worldWidth / 2f, worldHeight / 2f, 0);
        cam.update();
        uiCamera.update();

        controlBar = new ControlBar(uiStage);
        
        Gdx.input.setInputProcessor(new InputMultiplexer(uiStage, stage));

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
        // -----------------------------------------------------------------------------
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
   
            System.out.println("Dünya Koordinatları: " + worldCoords.x + ", " + worldCoords.y);
        }

        // -----------------------------------------------------------------------------
        controlBar.updateMouseCoordinates(worldCoords.x, worldCoords.y);
        // Oyun alanını çiz
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
    private com.badlogic.gdx.math.Vector3 screenToWorld(float screenX, float screenY) {
        com.badlogic.gdx.math.Vector3 worldCoords = new com.badlogic.gdx.math.Vector3(screenX, screenY, 0);
        cam.unproject(worldCoords);
        return worldCoords;
    }
    @Override
    public void resize(int width, int height) {
        int gameWidth = (int) (width * 0.8f);
        int uiWidth = width - gameWidth;

        viewport.update(gameWidth, height, true);
        viewport.setScreenBounds(0, 0, gameWidth, height);

        uiViewport.update(uiWidth, height, true);
        uiViewport.setScreenBounds(gameWidth, 0, uiWidth, height);

        cam.setToOrtho(false, worldWidth, worldHeight);
        cam.update();
        uiCamera.setToOrtho(false, worldWidth * 0.2f, worldHeight);
        uiCamera.update();

        controlBar.resize((int)worldWidth, (int)worldHeight);
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
