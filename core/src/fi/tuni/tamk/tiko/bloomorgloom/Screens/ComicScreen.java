package fi.tuni.tamk.tiko.bloomorgloom.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import fi.tuni.tamk.tiko.bloomorgloom.MyGdxGame;
import fi.tuni.tamk.tiko.bloomorgloom.Navigation;

/**
 * ComicScreen is a screen class that shows the comic of the story of the game
 */
public class ComicScreen implements Screen {
    MyGdxGame game;

    // Textures:
    private Texture[] pages;
    private Texture currentPage;

    private int pageNum = 1;

    SpriteBatch batch;
    private OrthographicCamera camera;

    /**
     * The constructor puts the textures into the pages array and sets the
     * first page as the currentPage.
     *
     * @param game Related to the functionality of the Screen interface.
     */
    public ComicScreen(MyGdxGame game) {
        this.game = game;
        batch = game.gameBatch;
        camera = game.camera;

        pages = new Texture[] {new Texture("comic/page1.jpg"),
                new Texture("comic/page2.jpg"),
                new Texture("comic/page3.jpg"),
                new Texture("comic/guidePage.png")};

        currentPage = pages[0];
    }

    /**
     * The method that draws the textures and calls other supporting methods.
     *
     * @param delta Related to the functionality of the Screen interface.
     */
    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0, 0, 0, 0);

        // Turn page when screen is touched. Move to GameScreen when comic ends.
        if (Gdx.input.justTouched()) {
            if (pageNum == pages.length) {
                game.settings.setHasSeenComic(true);
                game.navigation.setActive(Navigation.Screen.GAME);
                game.navigation.setScreen(Navigation.Screen.GAME);
            } else {
                nextPage();
            }
        }

        batch.begin();
        batch.draw(currentPage, 0, 0);
        batch.end();
    }

    /**
     * Turns the comic to the next page.
     */
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
