package fi.tuni.tamk.tiko.depressionaut.Sky;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import fi.tuni.tamk.tiko.depressionaut.Util;

/**
 * Class for the sky layers clouds and birds.
 *
 * Responsible for the creation, moving and drawing of the objects.
 */
public class SkyObject extends Actor {
    // List of paths for the cloud textures:
    private List<String> cloudTextures = Arrays.asList(
            "cosmetics/clouds/v1.png",
            "cosmetics/clouds/v2.png",
            "cosmetics/clouds/v3.png",
            "cosmetics/clouds/v4.png",
            "cosmetics/clouds/v5.png",
            "cosmetics/clouds/v6.png",
            "cosmetics/clouds/v7.png",
            "cosmetics/clouds/v8.png",
            "cosmetics/clouds/v9.png"
    );
    // Lists for the different layers of objects needed for the parallaxing effect:
    private HashMap<Integer, ArrayList<SkyObject>> layers = new HashMap<>();
    private ArrayList<SkyObject> layer1 = new ArrayList<>();
    private ArrayList<SkyObject> layer2 = new ArrayList<>();
    private ArrayList<SkyObject> layer3 = new ArrayList<>();

    // Values for the objects:
    private Vector2 velocity;
    private float angle = 5.3f;
    private float speed = 0.5f;
    private Texture texture;

    /**
     * The constructor puts the different layers inside a hashMap for easier access.
     */
    public SkyObject() {
        layers.put(0, layer1);
        layers.put(1, layer2);
        layers.put(2, layer3);
    }

    /**
     * Method for creating sky objects.
     *
     * The method gives each sky object a random layer, texture, position and scale.
     * Each objects velocity is determined by it's layer to create a parallaxing effect.
     * The randomization is done using Util.randomFloat().
     */
    public void createSkyObject() {
        SkyObject temp = new SkyObject();
        int randomArray = (int)(Util.randomFloat(0,2)); // set random layer

        temp.texture = getTexture(cloudTextures.get((int)(Util.randomFloat(0,8)))); // set random texture
        // set random position on the left side of the window
        temp.setPosition(
                100 - temp.texture.getWidth(),
                (float) ((Util.randomFloat(0,144)) + (1920-500) - temp.texture.getHeight()));
        // set random velocity depending on the object's layer
        temp.velocity = new Vector2(
                (speed + (randomArray * 0.075f)) * (float)Math.cos(Math.toRadians(angle)),
                (speed + (randomArray * 0.075f)) * (float)Math.sin(Math.toRadians(angle))
        );
        temp.setScale((float) (Util.randomFloat(0, 0.1f) + 1)); // set random scale

        // Sets the temp object and array inside the hashMap:
        ArrayList<SkyObject> tempArray = layers.get(randomArray);
        tempArray.add(temp);
        layers.put(randomArray, tempArray);
    }

    /**
     * Method for moving all the sky objects.
     *
     * The two for-loops go through all the arrays inside the "layers"-hashMap,
     * and moves all the objects inside according to their velocity. If an
     * object's x-coordinate is past the right side of the window, it gets removed.
     */
    public void moveSkyObjects() {
        ArrayList<SkyObject> tempArray;
        SkyObject temp;

        for (int hashMapIndex = 0; hashMapIndex < layers.size(); hashMapIndex++) {
            for (int arrayIndex = 0; arrayIndex < layers.get(hashMapIndex).size(); arrayIndex++) {
                tempArray = layers.get(hashMapIndex);
                temp = layers.get(hashMapIndex).get(arrayIndex);
                temp.moveBy(temp.velocity.x, temp.velocity.y);
                if (temp.getX() > 550) {
                    layers.get(hashMapIndex).remove(arrayIndex);
                } else {
                    tempArray.set(arrayIndex, temp);
                    layers.put(hashMapIndex, tempArray);
                }
            }
        }
    }

    /**
     * Method for drawing all sky objects.
     *
     * The two for-loops go through all the arrays inside the "layers"-hashMap,
     * and draw all the objects inside. Additionally, the method calls the
     * moveSkyObjects() method.
     *
     * @param batch SpriteBath needed for the draw() method.
     */
    public void draw(SpriteBatch batch) {
        moveSkyObjects(); // move all objects

        SkyObject temp;
        for (int hashMapIndex = 0; hashMapIndex < layers.size(); hashMapIndex++) {
            for (int arrayIndex = 0; arrayIndex < layers.get(hashMapIndex).size(); arrayIndex++) {
                temp = layers.get(hashMapIndex).get(arrayIndex);
                batch.draw(temp.texture,
                        temp.getX(),
                        temp.getY(),
                        temp.texture.getWidth()/2f,
                        temp.texture.getHeight()/2f,
                        temp.texture.getWidth(),
                        temp.texture.getHeight(),
                        temp.getScaleX(),
                        temp.getScaleY(),
                        0,
                        0,
                        0,
                        temp.texture.getWidth(),
                        temp.texture.getHeight(),
                        false,
                        false);
            }
        }
    }

    /**
     * Returns a texture according to a filepath.
     *
     * @param path The textures filepath.
     * @return Returns corresponding texture.
     */
    public Texture getTexture(String path) {
        return new Texture(path);
    }
}
