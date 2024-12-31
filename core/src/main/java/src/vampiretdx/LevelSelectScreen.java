package src.vampiretdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import src.vampiretdx.Levels.Level;
import src.vampiretdx.Levels.Level1;

public class LevelSelectScreen implements Screen{
    private VampireTD game;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;
    private Texture buttonUpTexture, buttonDownTexture, buttonCheckedTexture;
    public LevelSelectScreen(VampireTD game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin();

        BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"));
        skin.add("default-font", font); 

        buttonUpTexture = new Texture(Gdx.files.internal("buttons/button-up.png"));
        buttonDownTexture = new Texture(Gdx.files.internal("buttons/button-down.png"));
        buttonCheckedTexture = new Texture(Gdx.files.internal("buttons/button-checked.png"));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonUpTexture));
        textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(buttonDownTexture));
        textButtonStyle.checked = new TextureRegionDrawable(new TextureRegion(buttonCheckedTexture));




        TextButton button = createTextButton(textButtonStyle, new Level1());
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        button.setPosition(screenWidth/2,screenHeight/2); // Butonun konumu
        button.setSize(150, 60); 

    
    }
    public TextButton createTextButton(TextButtonStyle textButtonStyle, Level level){
        TextButton button = new TextButton("Click Me!", textButtonStyle);
        stage.addActor(button);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Butona tıklandı!");
                // Örneğin, sahneyi değiştirme
                game.setScreen(new GameScreen(game, level));
            }
        });
        return button;

    }
        @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }
        public void show() {}

        @Override
        public void resize(int width, int height) {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {
            batch.dispose();
            stage.dispose();
            skin.dispose();
        }
}
