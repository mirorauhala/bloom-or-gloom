package fi.tuni.tamk.tiko.bloomorgloom.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fi.tuni.tamk.tiko.bloomorgloom.MyGdxGame;
import fi.tuni.tamk.tiko.bloomorgloom.Screens.ShopClothingScreen;
import fi.tuni.tamk.tiko.bloomorgloom.Screens.ShopFurnitureScreen;
import fi.tuni.tamk.tiko.bloomorgloom.Screens.ShopOtherScreen;
import fi.tuni.tamk.tiko.bloomorgloom.Shop.Resources.OwnedProducts;
import fi.tuni.tamk.tiko.bloomorgloom.Shop.Resources.Product;
import fi.tuni.tamk.tiko.bloomorgloom.Shop.Resources.ProductEffect;
import fi.tuni.tamk.tiko.bloomorgloom.Shop.Resources.Products;

public class ShopMain {
    private final MyGdxGame game;
    private final HashMap<Product, Button> buttons = new HashMap<>();
    private Texture shopClothingTexture;
    private Texture shopFurnitureTexture;
    private Texture shopOtherTexture;
    private Texture shopHeadingTexture;
    private float walletAmount;
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
    }

    /**
     * Return the wallet label for shop screen for housekeeping.
     * @return Label
     */
    public Label getWalletLabel() {
        Label walletLabel = new Label("", skin);
        walletLabel.setFontScale(1.5f);

        return walletLabel;
    }

    /**
     * Build the main stage of the shop screen.
     * @param products Products
     * @param area String area of the shop view
     * @param walletLabel Label
     * @return Stage
     */
    public Stage getStage(Products products, String area, Label walletLabel) {
        container = new Table();
        container.setFillParent(true);
        container.setHeight(MyGdxGame.SCREEN_HEIGHT);
        container.setWidth(MyGdxGame.SCREEN_WIDTH);
        container.setDebug(MyGdxGame.DEBUG);
        container.left().top();

        Table productsTable = game.shop.getProductsTable(products);

        Table shopTop = game.shop.createShopTop(walletLabel);

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
    public Table getProductsTable(final Products products) {
        Table productsTable = new Table();
        productsTable.top().left();

        final String ownedProductsJson = game.inventory.getOwnedProducts();
        Gson gson = new Gson();
        final OwnedProducts ownedProducts = gson.fromJson(ownedProductsJson, OwnedProducts.class);

        for (final Product product : products.getProducts()) {

            Texture texture = new Texture(Gdx.files.internal(product.getTexture()));

            final String productNameStr = game.settings.getLang().equals("fi") ? product.getNameFi() : product.getNameEn();
            final Label productName = new Label(productNameStr, skin);
            productName.setWrap(true);
            productName.setFontScale(2);
            float productNameSize = 1080f - texture.getWidth() - 40f - 200f;

            Button buyButton = createButton(game.score.getRationalizedValue(product.getPrice(), 2));
            buyButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(game.score.decrementWallet(product.getPrice())) {
                        Gdx.app.debug("SHOP", "Bought: "+ productNameStr + " for " + product.getPrice());

                        game.sounds.buySoundPlay();
                        game.inventory.set(product.getType(), product.getId());

                        // Create new empty object
                        OwnedProducts emptyOwnedProduct = new OwnedProducts();
                        emptyOwnedProduct.setProducts(ownedProducts.getProducts());

                        // get all current products
                        HashMap<String, ArrayList<Integer>> currentOwnedProducts = emptyOwnedProduct.getProducts();

                        // get all of current type, e.g. shirt
                        if(currentOwnedProducts.containsKey(product.getType())) {
                            ArrayList<Integer> ownedOfCurrentType = currentOwnedProducts.get(product.getType());
                            ownedOfCurrentType.add(product.getId());
                        } else {
                            currentOwnedProducts.put(product.getType(), new ArrayList<Integer>(
                                    Collections.singletonList(product.getId())
                            ));
                        }

                        // Save
                        Gson newGsonThingy = new Gson();
                        game.inventory.setOwnedProducts(newGsonThingy.toJson(emptyOwnedProduct));

                        String method = product.getEffectMethod();
                        int amount = product.getEffectAmount();

                        switch (method) {
                            case "click-power":
                                game.score.incrementClickPower(amount);
                                break;
                            case "passive-income":
                                game.score.incrementPassiveIncome(amount);
                                break;
                            case "multiplier":
                                game.score.incrementMultiplier(amount);
                                break;
                        }

                    } else {
                        Gdx.app.debug("SHOP", "Cannot buy " + productNameStr);
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
    public Table createShopTop(Label walletLabel) {
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
        style.font = new BitmapFont(Gdx.files.internal("UI/QuicksandASCII.fnt"));

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
                game.sounds.menuClicksoudPlay();
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
                game.sounds.menuClicksoudPlay();
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
                game.sounds.menuClicksoudPlay();
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
        final OwnedProducts owned = new Gson().fromJson(game.inventory.getOwnedProducts(), OwnedProducts.class);

        for (Map.Entry<Product, Button> entry : buttons.entrySet()) {
            Product p = entry.getKey();
            Button b = entry.getValue();

            boolean isProductOwned = false;

            // check if the player owns current type of product
            if(owned.getProducts().containsKey(p.getType())) {

                // get a list of owned products of the current type
                ArrayList<Integer> ownedTypes = owned.getProducts().get(p.getType());
                isProductOwned = ownedTypes.contains(p.getId());
            }

            boolean isDisabled = p.getHappinessLevel() > game.score.getHappinessLevel() || p.getPrice() > walletAmount || isProductOwned;

            // set disabled
            b.setDisabled(isDisabled);
            b.setVisible(!isDisabled);
            b.setTouchable(isDisabled ? Touchable.disabled : Touchable.enabled);
        }
    }

    /**
     * Get the current wallet amount and set it to the label.
     */
    public void updateWallet(Label walletLabel) {
        walletAmount = game.score.getWallet();
        walletLabel.setText(game.score.getRationalizedValue(game.score.getWallet(), 2));
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
