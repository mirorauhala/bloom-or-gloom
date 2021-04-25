package fi.tuni.tamk.tiko.depressionaut.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Contains common store functionality.
 */
public class Shop {

    /**
     * Create a button with predefined styles.
     * @param text String Text to display on the button.
     * @return Button
     */
    public static Button createButton(String text) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("shop/ui/button-default.png"))));
        style.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("shop/ui/button-active.png"))));
        style.disabled = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("shop/ui/button-disabled.png"))));
        style.font = new BitmapFont(Gdx.files.internal("UI/Quicksand.fnt"));

        Button b = new TextButton(text, style);
        b.setSize(200f,50f);
        return b;
    }

    public static void addVerticalSpacer(Table table, float width, Skin skin) {
        table.add(new Label("", skin)).width(width).expandY().fillY();
    }

}
