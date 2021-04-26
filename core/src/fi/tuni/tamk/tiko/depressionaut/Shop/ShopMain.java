package fi.tuni.tamk.tiko.depressionaut.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import fi.tuni.tamk.tiko.depressionaut.MyGdxGame;
import fi.tuni.tamk.tiko.depressionaut.Screens.ShopClothingScreen;
import fi.tuni.tamk.tiko.depressionaut.Screens.ShopFurnitureScreen;
import fi.tuni.tamk.tiko.depressionaut.Screens.ShopOtherScreen;
import fi.tuni.tamk.tiko.depressionaut.Shop.Resources.Product;
import fi.tuni.tamk.tiko.depressionaut.Shop.Resources.Products;

public class ShopMain {
    private final MyGdxGame game;
    private final HashMap<Product, Button> buttons = new HashMap<>();
    private Texture shopClothingTexture;
    private Texture shopFurnitureTexture;
    private Texture shopOtherTexture;
    private Texture shopHeadingTexture;
    private float walletAmount;
    private Label walletLabel;
    private Skin skin;
    private Table container;
    private Viewport viewport;
    private Stage stage;

    public ShopMain(MyGdxGame game) {
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera(MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        viewport = new StretchViewport(MyGdxGame.SCREEN_WIDTH, MyGdxGame.SCREEN_HEIGHT, camera);

        //setup skin
        skin = new Skin(Gdx.files.internal("UI/uiskin.json"));

        // initialize empty walletLabel
        walletLabel = new Label("", skin);
        walletLabel.setFontScale(1.5f);

    }

    /**
     * Build the main stage of the shop screen.
     * @param products Products
     * @param area String area of the shop view
     * @return Stage
     */
    public Stage getStage(Products products, String area) {
        container = new Table();
        container.setFillParent(true);
        container.setHeight(MyGdxGame.SCREEN_HEIGHT);
        container.setWidth(MyGdxGame.SCREEN_WIDTH);
        container.setDebug(MyGdxGame.DEBUG);
        container.left().top();

        Table productsTable = game.shop.getProductsTable(products);

        Table shopTop = game.shop.createShopTop();

        container.add(shopTop)
                .top()
                .left()
                .padTop(20f)
                .padBottom(20f)
                .fill()
                .expand();

        container.row();

        Table shopNav = game.shop.createShopNav(area);

        container.add(shopNav)
                .top()
                .left()
                .padBottom(20f)
                .fill();

        container.row();

        ScrollPane scrollPane = new ScrollPane(productsTable);

        //add the scroll pane to the container
        container.add(scrollPane).fill().expand().padBottom(200f);

        // setup stage
        stage = new Stage(viewport);
        stage.setDebugAll(MyGdxGame.DEBUG);

        // add container to the stage
        stage.addActor(container);
        return stage;
    }

    /**
     * Return the products for the given area.
     * @param area Area of the shop to give products for.
     * @return Products
     */
    public Products getProductsFor(String area) {
        FileHandle handle = Gdx.files.internal("shop/"+area+".json");
        String text = handle.readString();

        Gson gson = new Gson();
        return gson.fromJson(text, Products.class);
    }

    /**
     * Return the scene2d.ui.Table of the Products.
     * @param products Table
     */
    public Table getProductsTable(Products products) {
        Table productsTable = new Table();
        productsTable.top().left();

        for (final Product product : products.getProducts()) {

            Texture texture = new Texture(Gdx.files.internal(product.getTexture()));

            Label productName = new Label(product.getName(), skin);
            productName.setWrap(true);
            productName.setFontScale(2);
            float productNameSize = 1080f - texture.getWidth() - 40f - 200f;

            Button buyButton = createButton(product.getPrice() + "e");
            buyButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(game.score.decrementWallet(product.getPrice())) {
                        game.inventory.set(product.getType(), product.getId());
                        Gdx.app.debug("SHOP", "Bought: "+ product.getName() + " for " + product.getPrice() );
                        game.sounds.buySoundPlay();

                    } else {
                        Gdx.app.debug("SHOP", "Cannot buy " + product.getName());
                    }
                }
            });

            Table table = new Table(skin);
            table.setDebug(MyGdxGame.DEBUG); // turn on all debug lines (table, cell, and widget)

            // add new product image
            table.add(new Image(texture))
                    .width(texture.getWidth())
                    .height(texture.getHeight())
                    .padBottom(20f)
                    .padLeft(20f)
                    .padRight(20f);

            // add product title
            table.add(productName).width(productNameSize);

            // add product buy button
            table.add(buyButton).width(180f).padRight(20f);

            // alignment
            table.left().top();


            buttons.put(product, buyButton);

            productsTable.row();
            productsTable.add(table).expandX();
        }

        return productsTable;
    }

    /**
     * Create the top of the shop.
     * @return Table
     */
    public Table createShopTop() {
        shopHeadingTexture = new Texture(Gdx.files.internal("shop/ui/" + game.settings.getLang() + "/shop.png"));

        Table table = new Table(skin);
        table.setDebug(MyGdxGame.DEBUG);

        table.add(new Image(shopHeadingTexture))
                .pad(20f)
                .width(shopHeadingTexture.getWidth())
                .height(shopHeadingTexture.getHeight())
                .expandY()
                .left();

        table.add(walletLabel).padRight(20f).expand().right();

        return table;
    }

    /**
     * Create a button with predefined styles.
     * @param text String Text to display on the button.
     * @return Button
     */
    private static Button createButton(String text) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("shop/ui/button-default.png"))));
        style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("shop/ui/button-active.png"))));
        style.disabled = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("shop/ui/button-disabled.png"))));
        style.font = new BitmapFont(Gdx.files.internal("UI/Quicksand.fnt"));

        Button b = new TextButton(text, style);
        b.setSize(200f,50f);
        return b;
    }

    /**
     * Create the navigation for the shop.
     * @return Table
     * @param area String area of the shop view
     */
    public Table createShopNav(String area) {
        String clothingFile = area.equals("clothing") ? "clothing-button.png" : "clothing-text.png";
        String furnitureFile = area.equals("furniture") ? "furniture-button.png" : "furniture-text.png";
        String otherFile = area.equals("other") ? "other-button.png" : "other-text.png";

        shopClothingTexture = new Texture(Gdx.files.internal("shop/ui/" + game.settings.getLang() + "/" + clothingFile));
        shopFurnitureTexture = new Texture(Gdx.files.internal("shop/ui/" + game.settings.getLang() + "/"+ furnitureFile));
        shopOtherTexture = new Texture(Gdx.files.internal("shop/ui/" + game.settings.getLang() + "/" + otherFile));

        Table table = new Table(skin);
        table.setDebug(MyGdxGame.DEBUG);

        Table clothingButton = new Table();
        clothingButton.add(new Image(shopClothingTexture))
                .pad(20f)
                .width(shopClothingTexture.getWidth())
                .height(shopClothingTexture.getHeight())
                .expandY()
                .left();

        clothingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new ShopClothingScreen(game));
            }
        });

        Table furnitureButton = new Table();
        furnitureButton.add(new Image(shopFurnitureTexture))
                .pad(20f)
                .width(shopFurnitureTexture.getWidth())
                .height(shopFurnitureTexture.getHeight())
                .expandY()
                .left();

        furnitureButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.debug("SHOP", "clicked on furniture");
                game.setScreen(new ShopFurnitureScreen(game));

            }
        });

        Table otherButton = new Table();
        otherButton.add(new Image(shopOtherTexture))
                .pad(20f)
                .width(shopOtherTexture.getWidth())
                .height(shopOtherTexture.getHeight())
                .expandY()
                .left();

        otherButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.debug("SHOP", "clicked on other");
                game.setScreen(new ShopOtherScreen(game));

            }
        });

        table.add(clothingButton);
        table.add(furnitureButton);
        table.add(otherButton);

        return table;
    }

    /**
     * Update status of the buy buttons.
     *
     * If the player doesn't have enough funds, set the buttons to a disabled state.
     * Otherwise, enable the buttons back.
     */
    public void updateButtons() {
        for (Map.Entry<Product, Button> entry : buttons.entrySet()) {
            Product p = entry.getKey();
            Button b = entry.getValue();

            b.setDisabled(p.getPrice() > walletAmount);
        }
    }

    /**
     * Get the current wallet amount and set it to the label.
     */
    public void updateWallet() {
        walletAmount = game.score.getWallet();
        walletLabel.setText(walletAmount + ""); // hack: cast to string
    }

    /**
     * Disposes assets.
     */
    public void dispose() {
        stage.dispose();
        skin.dispose();
        shopClothingTexture.dispose();
        shopFurnitureTexture.dispose();
        shopOtherTexture.dispose();
    }
}
