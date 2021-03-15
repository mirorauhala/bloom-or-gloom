package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class BottomHud {
    private Texture testhud = new Texture("HUD/bottomhudTest.png");
    private float width = 1080;
    private float height = 1920;

    public void draw(SpriteBatch batch) {
        batch.draw(testhud, 0, 0, width, height);
    }
}
