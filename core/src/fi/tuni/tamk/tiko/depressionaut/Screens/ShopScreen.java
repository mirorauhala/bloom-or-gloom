package fi.tuni.tamk.tiko.depressionaut.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.google.gson.Gson;

import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;
import fi.tuni.tamk.tiko.depressionaut.Shop.Product;
import fi.tuni.tamk.tiko.depressionaut.Shop.Products;

public class ShopScreen implements Screen {
    private final OrthographicCamera camera;
    private final StretchViewport viewport;
    MyGdxGame game;
    ScrollPane scrollpane;
    Skin skin;
    Stage stage;
    Table container;

    public ShopScreen(final MyGdxGame game){
        this.game = game;

        camera = new OrthographicCamera(MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        viewport = new StretchViewport(MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT, camera);


        FileHandle handle = Gdx.files.internal("shop/products.json");
        String text = handle.readString();

        Gson gson = new Gson();
        Products products = gson.fromJson(text, Products.class);

        //setup skin
        skin = new Skin(Gdx.files.internal("UI/uiskin.json"));

        // table that holds the scroll pane
        container = new Table();
        container.setFillParent(true);
        container.setHeight(MyGdxGame.SCREEN_HEIGHT);
        container.setWidth(MyGdxGame.SCREEN_WIDTH);
        container.setDebug(MyGdxGame.DEBUG); // turn on all debug lines (table, cell, and widget)
        container.left().top();

        int height = Gdx.graphics.getHeight()/4;
        int width = Gdx.graphics.getWidth()/4;

        // inner table that is used as a makeshift list.
        Table innerContainer = new Table();
        innerContainer.top();

        for (final Product product : products.getProducts()) {
            Gdx.app.debug("NAV", product.getName());

            Texture texture = new Texture(Gdx.files.internal("shop/" + product.getTexture()));

            Float labelWidth = Gdx.graphics.getWidth() - 20f - width;
            Label label = new Label(product.getName(), skin);
            label.setWrap(true);
            label.setWidth(labelWidth);
            label.setFontScale(1);

            Table table = new Table(skin);
            table.setDebug(MyGdxGame.DEBUG); // turn on all debug lines (table, cell, and widget)
            table.add(new Label("", skin)).width(10f).expandY().fillY();// a spacer
            table.add(new Image(texture)).width(texture.getWidth()).height(texture.getHeight());
            table.add(new Label("", skin)).width(10f).expandY().fillY();// a spacer
            table.add(label).width(labelWidth).expandY().fillX();
            table.left().top();

            table.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.inventory.set("wall", 4);
                    System.out.println("Clicked on button: "+ product);
                }
            });

            innerContainer.row();
            innerContainer.add(table).expandX();
        }

        // create the scrollpane
        scrollpane = new ScrollPane(innerContainer);

        Texture shopNav = new Texture(Gdx.files.internal("shop/shop-clothing.png"));

        Table shopTop = new Table(skin);
        shopTop.setDebug(MyGdxGame.DEBUG); // turn on all debug lines (table, cell, and widget)
        shopTop.add(new Image(shopNav));

        shopTop.left().top();
        container.add(shopTop).top().left();
        container.row();

        //add the scroll pane to the container
        container.add(scrollpane).fill().expand();

        // setup stage
        stage = new Stage(viewport);
        stage.setDebugAll(MyGdxGame.DEBUG);

        // add container to the stage
        stage.addActor(container);

        // setup input processor (gets clicks and stuff)
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);    //sets up the clear color (background color) of the screen.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  //instructs openGL to actually clear the screen to the newly set clear color.
        stage.draw();
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {
        //System.out.println("resize");

    }

    @Override
    public void show() {
        //System.out.println("Show");

    }

    @Override
    public void hide() {
        //System.out.println("Hide");

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
        System.out.println("dispose");

    }

}
