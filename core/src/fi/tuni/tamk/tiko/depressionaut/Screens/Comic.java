package fi.tuni.tamk.tiko.depressionaut.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;
import fi.tuni.tamk.tiko.depressionaut.Navigation;

public class Comic implements Screen {
    MyGdxGame game;

    // Textures:
    private Texture[] pages;
    private Texture currentPage;

    private int pageNum = 1;

    SpriteBatch batch;
    private OrthographicCamera camera;

    public Comic(MyGdxGame game) {
        this.game = game;
        batch = game.gameBatch;
        camera = game.camera;

        pages = new Texture[] {new Texture("comic/page1.jpg"),
                new Texture("comic/page2.jpg"),
                new Texture("comic/page3.jpg")};

        currentPage = pages[0];
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0, 0, 0, 0);

        // Turn page when screen is touched. Move to GameScreen when comic ends.
        if (Gdx.input.justTouched()) {
            if (pageNum == pages.length) {
                game.navigation.setActive(Navigation.Screen.GAME);
                game.setScreen(new GameScreen(game));
            } else {
                nextPage();
            }
        }

        batch.begin();
        batch.draw(currentPage, 0, 0);
        batch.end();
    }

    // Turns the page.
    public void nextPage() {
        pageNum++;
        currentPage = pages[pageNum - 1];
    }

    @Override
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
    public void dispose() {}
}
