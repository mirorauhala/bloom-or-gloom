package fi.tuni.tamk.tiko.depressionaut.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.gson.Gson;

import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;
import fi.tuni.tamk.tiko.depressionaut.Shop.BaseProduct;
import fi.tuni.tamk.tiko.depressionaut.Shop.Products;
import fi.tuni.tamk.tiko.depressionaut.Shop.RawProducts.BlueShirt;
import fi.tuni.tamk.tiko.depressionaut.Shop.Shop;

public class ShopScreen implements Screen {
    private final Shop shop;
    private final BaseProduct currentProduct;
    MyGdxGame game;
    SpriteBatch batch;
    private OrthographicCamera camera;
    private Rectangle rect;
    private String text;

    public ShopScreen(MyGdxGame game) {
        this.game = game;
        batch = game.gameBatch;
        camera = game.camera;

        shop = new Shop(game);
        //shop.setCategory(Shop.Category.CLOTHES);
        //categories.setCategory(Shop.Category.FURNITURE);
        //categories.setCategory(Shop.Category.MISC);
        //products = shop.getProducts();

        FileHandle handle = Gdx.files.local("shop/products.json");
        text = handle.readString();

        Gson gson = new Gson();
        Products products = gson.fromJson(text, Products.class);
        System.out.println(products.getTypes().get(0).getName());


        currentProduct = new BlueShirt();
        rect = new Rectangle(100, 1700, currentProduct.getTexture().getWidth(),  currentProduct.getTexture().getHeight());
    }

    @Override
    public void render(float delta) {

        // set background to white
        ScreenUtils.clear(1, 1, 1, 1);

        // rectangle
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(currentProduct.getTexture(), rect.getX(), rect.getY());
        batch.end();

        if(Gdx.input.justTouched()){
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(rect.contains(touch.x, touch.y)){
                //game.navigation.setActive(Navigation.Screen.GAME);
                //game.setScreen(new GameScreen(game));

                //Gdx.app.debug("WHAT", products);

                //currentProduct.buy();
            }
        }
    }

    @Override
    public void show() {

    }



    @Override
    public void resize(int width, int height) {

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
