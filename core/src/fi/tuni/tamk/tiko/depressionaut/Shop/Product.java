package fi.tuni.tamk.tiko.depressionaut.Shop;

import java.util.List;

/**
 * A class representing a single product. Serialized to a POJO from JSON using google/gson.
 */
public class Product {
    private int id;
    private String name;
    private String type;
    private int level;
    private int price;
    private String texture;
    private List<ProductEffect> effects;

    /**
     * Returns the product's id.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the product's name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Return the product's name.
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the product's level.
     * @return int
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the product's texture.
     * @return int
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns the product's texture.
     * @return String product texture
     */
    public String getTexture() {
        return texture;
    }

    /**
     * Returns the product's effects.
     * @return List
     */
    public List<ProductEffect> getEffects() {
        return effects;
    }
}
