package fi.tuni.tamk.tiko.bloomorgloom2.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import fi.tuni.tamk.tiko.bloomorgloom2.Shop.Resources.Products;
import fi.tuni.tamk.tiko.bloomorgloom2.MyGdxGame;

/**
 * Class for the furniture section of the shop.
 */
public class ShopFurnitureScreen implements Screen {
    private final MyGdxGame game;
    private final Stage stage;
    private final Label wallet;

    /**
     * Constructor.
     *
     * @param game Needed for the functionality of screens in LibGDX.
     */
    public ShopFurnitureScreen(final MyGdxGame game){
        this.game = game;
        String area = "furniture";
        Products products = game.shop.getProductsFor(area);
        wallet = game.shop.getWalletLabel();
        stage = game.shop.getStage(products, area, wallet);
    }

    /**
     * Renders the furniture section of the shop.
     *
     * @param delta Deltatime needed for LibGDX's stages.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1); //sets up the clear color (background color) of the screen.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //instructs openGL to actually clear the screen to the newly set clear color.

        game.shop.updateWallet(wallet);
        game.shop.updateButtons();

        stage.draw();
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {
        //System.out.println("resize");

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
        System.out.println("pause");

    }

    @Override
    public void resume() {
        System.out.println("resume");

    }

    @Override
    public void dispose() {
        game.shop.dispose();
    }
}
