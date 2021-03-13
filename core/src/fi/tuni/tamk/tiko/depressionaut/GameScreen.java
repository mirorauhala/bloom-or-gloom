package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameScreen {
    private Texture wall;
    private float x;
    private float y;

    public GameScreen(Texture wallTexture, float x, float y) {
        setWall(wallTexture);
        setX(x);
        setY(y);
    }

    public void setX(float xPos) {
        xPos = x;
    }
    public void setY(float yPos) {
        yPos = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setWall(Texture wallTexture) {
        wallTexture = wall;
    }

    public Texture getWall() {
        return wall;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getWall(), getX(), getY());
    }
}
