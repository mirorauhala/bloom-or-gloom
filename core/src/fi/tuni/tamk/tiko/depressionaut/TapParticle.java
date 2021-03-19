package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class TapParticle {
    private Texture particleTexture = new Texture("visuals.png");;
    private ArrayList<TapParticle> Particles = new ArrayList<>();

    Random random = new SecureRandom();

    private float x;
    private float y;
    private Vector2 vector;
    private float speed = 5;

    private float scale;
    private float rotation;

    public TapParticle() {}

    public void createParticle() {
        Particles.add(new TapParticle());

        // Set temp particle:
        TapParticle tempParticle = Particles.get(Particles.size()-1);

        tempParticle.scale = randomFloat(0.75f, 1.25f);
        tempParticle.rotation = randomFloat(-30, 150);

        tempParticle.vector = new Vector2((float)Math.cos(Math.toRadians(Particles.get(Particles.size()-1).rotation + 90)) * speed,
                (float)Math.sin(Math.toRadians(Particles.get(Particles.size()-1).rotation + 90)) * speed);

        tempParticle.setX(getHeadX() - (particleTexture.getWidth() / 2f) + tempParticle.vector.x * 50);
        tempParticle.setY(getHeadY() - (particleTexture.getHeight() / 2f) + tempParticle.vector.y * 50);

        // Set real particle:
        Particles.set(Particles.size()-1, tempParticle);
    }

    public void renderParticles(SpriteBatch batch) {
        for (TapParticle p : Particles) {
            moveParticle(p);

            batch.draw(particleTexture,
                    p.getX(),
                    p.getY(),
                    particleTexture.getWidth() / 2f,
                    particleTexture.getHeight() / 2f,
                    particleTexture.getWidth(),
                    particleTexture.getHeight(),
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

    public void moveParticle(TapParticle p) {
        p.setX(p.getX() + p.vector.x);
        p.setY(p.getY() + p.vector.y);
    }

    // TODO: Move to character's own class:
    public float getHeadX() {
        return 740f;
    }
    public float getHeadY() {
        return 1920 - 1070f;
    }

    public float randomFloat(float min, float max) {
        return min + random.nextFloat() * (max - min);
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
