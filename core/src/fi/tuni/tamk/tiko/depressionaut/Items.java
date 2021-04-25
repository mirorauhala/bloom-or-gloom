package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.graphics.Texture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Stores the in-game textures for the GameScreen. Handles texture caching for faster screen
 * switching.
 */
public class Items {
    private final HashMap<String, List<String>> items = new HashMap<>();
    private final HashMap<String, Texture> currentTextures = new HashMap<>();
    private final HashMap<String, Integer> currentIndex = new HashMap<>();

    /**
     * Store the current items.
     */
    public Items() {

        items.put("wall", Arrays.asList(
            "walls/t1v1.png",
            "walls/t1v2.png",
            "walls/t1v3.png",
            "walls/t2v4.png",
            "walls/t2v5.png",
            "walls/t2v6.png",
            "walls/t3v7.png",
            "walls/t3v8.png",
            "walls/t3v9.png",
            "walls/t4v10.png",
            "walls/t4v11.png",
            "walls/t4v12.png",
            "walls/t5v13.png",
            "walls/t5v14.png",
            "walls/t5v15.png"
        ));

        items.put("floor", Arrays.asList(
            "floors/t1v1.png",
            "floors/t1v2.png",
            "floors/t1v3.png",
            "floors/t2v4.png",
            "floors/t2v5.png",
            "floors/t2v6.png",
            "floors/t3v7.png",
            "floors/t3v8.png",
            "floors/t3v9.png",
            "floors/t4v10.png",
            "floors/t4v11.png",
            "floors/t4v12.png",
            "floors/t5v13.png",
            "floors/t5v14.png",
            "floors/t5v15.png"
        ));

        items.put("bed", Arrays.asList(
            "furniture/bed/t1v1.png",
            "furniture/bed/t1v2.png",
            "furniture/bed/t1v3.png",
            "furniture/bed/t2v4.png",
            "furniture/bed/t2v5.png",
            "furniture/bed/t2v6.png",
            "furniture/bed/t3v7.png",
            "furniture/bed/t3v8.png",
            "furniture/bed/t3v9.png",
            "furniture/bed/t4v10.png",
            "furniture/bed/t4v11.png",
            "furniture/bed/t4v12.png",
            "furniture/bed/t5v13.png",
            "furniture/bed/t5v14.png",
            "furniture/bed/t5v15.png"
        ));

        items.put("stuff", Arrays.asList(
            "barbells.png",
            "plant.png",
            "moreStuff.png"
        ));

        items.put("chair", Arrays.asList(
            "furniture/chair/t1v1.png",
            "furniture/chair/t1v2.png",
            "furniture/chair/t1v3.png",
            "furniture/chair/t2v4.png",
            "furniture/chair/t2v5.png",
            "furniture/chair/t2v6.png",
            "furniture/chair/t3v7.png",
            "furniture/chair/t3v8.png",
            "furniture/chair/t3v9.png",
            "furniture/chair/t4v10.png",
            "furniture/chair/t4v11.png",
            "furniture/chair/t4v12.png"
        ));

        items.put("desk", Arrays.asList(
            "furniture/desk/t1v1.png",
            "furniture/desk/t1v2.png",
            "furniture/desk/t1v3.png",
            "furniture/desk/t2v4.png",
            "furniture/desk/t2v5.png",
            "furniture/desk/t2v6.png",
            "furniture/desk/t3v7.png",
            "furniture/desk/t3v8.png",
            "furniture/desk/t3v9.png",
            "furniture/desk/t4v10.png",
            "furniture/desk/t4v11.png",
            "furniture/desk/t4v12.png",
            "furniture/desk/t5v13.png",
            "furniture/desk/t5v14.png",
            "furniture/desk/t5v15.png"
        ));
    }

    /**
     * Get current item.
     *
     * Caches the texture for later use.
     *
     * @param item  String name of the item
     * @param tier int tier of the item
     * @return Texture
     */
    public Texture getItem(String item, int tier) {
        Integer currentItemIndex = currentIndex.get(item);
        if(currentItemIndex == null || currentItemIndex != tier) {
            currentIndex.put(item, tier);
            currentTextures.put(item, new Texture(items.get(item).get(tier)));
        }

        return currentTextures.get(item);
    }

    public void preloadTextures(Inventory inventory) {
        List<String> items = Arrays.asList(
                "wall",
                "floor",
                "bed",
                "chair",
                "desk"
        );

        for (String item : items) {
            getItem(item, inventory.get(item));
        }
    }

}
