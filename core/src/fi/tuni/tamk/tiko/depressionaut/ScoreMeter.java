package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreMeter {

    private OrthographicCamera camera;
    private MyGdxGame game;

    public Texture meterBase = new Texture("UI/UpperHUDbase.png");
    public Texture meterTexture = new Texture("UI/UpperHUDmeter.png");

    public ScoreMeter(MyGdxGame game) {
        this.game = game;
        this.camera = game.camera;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(meterBase, 0, 0);
        batch.draw(meterTexture, 0, 0);
    }
}
