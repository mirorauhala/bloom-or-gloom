package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class TapParticle {
    private Texture particleTexture = new Texture("visuals.png");;
    private ArrayList<TapParticle> Particles = new ArrayList<>();

    private float x;
    private float y;

    private float width;
    private float height;
    private float scale;
    private float rotation;

    public TapParticle() {

    }

    public void createParticle() {
        Particles.add(new TapParticle());

        Particles.get(Particles.size()-1).setX(50);
        Particles.get(Particles.size()-1).setY(50);
        Particles.get(Particles.size()-1).width = 500;
        Particles.get(Particles.size()-1).height = 500;
        Particles.get(Particles.size()-1).scale = 1;
        Particles.get(Particles.size()-1).rotation = 0;
    }

    public void renderParticles(SpriteBatch batch) {
        for (TapParticle p : Particles) {
            batch.draw(particleTexture,
                    p.getX(),
                    p.getY(),
                    p.width / 2,
                    p.height / 2,
                    p.width,
                    p.height,
                    p.scale,
                    p.scale,
                    p.rotation,
                    0,
                    0,
                    particleTexture.getWidth(),
                    particleTexture.getHeight(),
                    false,
                    false);
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return this.x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return this.y;
    }

    public void dispose() {
        particleTexture.dispose();
    }
}
