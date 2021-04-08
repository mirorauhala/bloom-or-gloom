package fi.tuni.tamk.tiko.depressionaut.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.gson.Gson;

import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;
import fi.tuni.tamk.tiko.depressionaut.Shop.Product;
import fi.tuni.tamk.tiko.depressionaut.Shop.Products;

public class ShopScreen implements Screen {
    private final String text;
    MyGdxGame game;
    ScrollPane scrollpane;
    Skin skin;
    Stage stage;
    Table container;
    Texture texture1, texture2, texture3;
    private BitmapFont font;
    private Skin skinButton;
    private TextureAtlas buttonAtlas;
    private TextButton.TextButtonStyle textButtonStyle;

    public ShopScreen(MyGdxGame game){
        this.game = game;

        FileHandle handle = Gdx.files.local("shop/products.json");
        text = handle.readString();

        Gson gson = new Gson();
        Products products = gson.fromJson(text, Products.class);
        for (Product product : products.getProducts()) {
            Gdx.app.log("NAV", product.getName());
        }






        //setup skin
        skin = new Skin(Gdx.files.internal("UI/uiskin.json"));

        texture1 = new Texture(Gdx.files.internal("shop/wip-shirt-1.png"));
        texture2 = new Texture(Gdx.files.internal("shop/wip-shirt-2.png"));
        texture3 = new Texture(Gdx.files.internal("shop/wip-shirt-3.png"));

        // table that holds the scroll pane
        container = new Table();
        container.setFillParent(true);
        container.setDebug(MyGdxGame.DEBUG); // turn on all debug lines (table, cell, and widget)
        container.left();

        int height = Gdx.graphics.getHeight()/4;
        int width = Gdx.graphics.getWidth()/4;

        // inner table that is used as a makeshift list.
        Table innerContainer = new Table();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            Float labelWidth = Gdx.graphics.getWidth() - 20f - width;
            Label label = new Label("What happens if the name of the product is really long like will this actually do some word wrapping or what the hey", skin);
            label.setWrap(true);
            label.setWidth(labelWidth);
            label.setFontScale(1);

            Table table4 = new Table(skin);
            table4.setDebug(MyGdxGame.DEBUG); // turn on all debug lines (table, cell, and widget)
            table4.add(new Label("", skin)).width(10f).expandY().fillY();// a spacer
            table4.add(new Image(texture3)).width(width).height(width);
            table4.add(new Label("", skin)).width(10f).expandY().fillY();// a spacer
            table4.add(label).width(labelWidth).expandY().fillX();
            table4.left().top();

            table4.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Clicked on button: "+ index);
                }
            });

            innerContainer.row();
            innerContainer.add(table4).expand().fill();
        }

        // create the scrollpane
        scrollpane = new ScrollPane(innerContainer);

        //add the scroll pane to the container
        container.add(scrollpane).fill().expand();

        // setup stage
        stage = new Stage();

        // add container to the stage
        stage.addActor(container);

        // setup input processor (gets clicks and stuff)
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);    //sets up the clear color (background color) of the screen.
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
