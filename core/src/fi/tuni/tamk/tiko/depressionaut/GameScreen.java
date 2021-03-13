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
        x = xPos;
    }
    public void setY(float yPos) {
        y = yPos;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setWall(Texture wallTexture) {
        wall = wallTexture;
    }

    public Texture getWall() {
        return wall;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(getWall(), getX(), getY());
    }
}