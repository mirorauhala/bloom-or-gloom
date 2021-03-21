package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

// TODO: Refactor the fadeParticle() method to lessen the amount of code.
// TODO: Move getHeadX() and getHeadY() methods to character's own class:
/**
 * The TapParticle class creates particles when the screen is tapped.
 *
 * The particles are created around players head and utilize LibGDX's Actor
 * class to fade the textures in and out.
 *
 * @author Jere Joensuu
 */

public class TapParticle extends Actor {
    // Particle texture:
    private final Texture texture = new Texture("visuals.png");

    // The list containing the particles:
    private ArrayList<TapParticle> Particles = new ArrayList<>();

    // Object for creating random values:
    Random random = new SecureRandom();

    // Related to moving the particles:
    private Vector2 vector;
    private final float SPEED = 5;

    // A particle starts to fade out when its timer reaches zero.
    private float particleTimer = 60;

    /**
     * Creates a new particle.
     */
    public void createParticle() {
        // Adds a new TapParticle object to the list.
        Particles.add(new TapParticle());

        // Set newest object (ie. the newly created particle) in the list as temp particle:
        TapParticle tempParticle = Particles.get(Particles.size()-1);

        // Give the particle a random scale:
        tempParticle.setScale(randomFloat(0.75f, 1.25f));
        // Determines the size of the sector the particles can move in.
        tempParticle.setRotation(randomFloat(-20, 100));

        // Give the particle a vector according to its rotation. The vector is used to move the particle
        tempParticle.vector = new Vector2((float)Math.cos(Math.toRadians(tempParticle.getRotation() + 90)) * SPEED,
                (float)Math.sin(Math.toRadians(tempParticle.getRotation() + 90)) * SPEED);

        // Set the origin coordinates of the particle according to the character's head and the push
        // it outwards according to the particle's vector.
        tempParticle.setX(getHeadX() - (texture.getWidth() / 2f) + tempParticle.vector.x * 55);
        tempParticle.setY(getHeadY() - (texture.getHeight() / 2f) + tempParticle.vector.y * 55);

        // Set the particle's opacity to 0:
        tempParticle.getColor().a = 0;

        // Set temp particle as real particle:
        Particles.set(Particles.size()-1, tempParticle);
    }

    /**
     * Manages each particle's attributes and draws them.
     *
     * Goes through the list containing the particles, calls supporting methods to move, fade and
     * destroy particles and finally draws them.
     *
     * @param batch SpriteBatch needed for drawing the particles.
     * @param delta Delta time used by the act() method of the Actor class.
     */
    public void renderParticles(SpriteBatch batch, float delta) {
        for (TapParticle p : Particles) {
            p.fadeParticle();
            p.moveParticle();
            p.destroyParticle();
            p.act(delta);

            // Sets the opacity of the SpriteBatch according the the alpha value of a given
            // particle to allow particles to fade in and out.
            batch.setColor(1,1,1,p.getColor().a);

            batch.draw(texture,
                    p.getX(),
                    p.getY(),
                    texture.getWidth() / 2f,
                    texture.getHeight() / 2f,
                    texture.getWidth(),
                    texture.getHeight(),
                    p.getScaleX(),
                    p.getScaleY(),
                    p.getRotation(),
                    0,
                    0,
                    texture.getWidth(),
                    texture.getHeight(),
                    false,
                    false);
        }
        // Set the alpha channel of the SpriteBatch back to 1.
        batch.setColor(1,1,1,1);
    }

    // TODO: Refactor to lessen the amount of code.
    /**
     * Handles the fading in and out of particles.
     */
    public void fadeParticle() {
        if (this.particleTimer >= 0) {
            this.addAction(Actions.fadeIn(0.25f));
        } else {
            this.clearActions();
            this.addAction(Actions.fadeOut(0.1f));
        }
        this.particleTimer--;
    }

    /**
     * Moves particles according to its vector.
     */
    public void moveParticle() {
        this.moveBy(this.vector.x, this.vector.y);
    }

    /**
     * Removes a particle from the Particles list once its timer and opacity reach zero.
     */
    public void destroyParticle() {
        if (this.particleTimer <=0 && this.getColor().a == 0) {
            Particles.remove(this);
        }
    }

    // TODO: Move to character's own class:
    public float getHeadX() {
        return 740f;
    }
    public float getHeadY() {
        return 1920 - 1070f;
    }

    /**
     * Randomizes a float between given values.
     * @param min Minimum value of randomized number.
     * @param max Maximum value of randomized number.
     * @return Returns the randomized float.
     */
    public float randomFloat(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }
}
