package fi.tuni.tamk.tiko.bloomorgloom.Shop.Resources;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A class representing a single product. Serialized to a POJO from JSON using google/gson.
 */
public class Product {
    private int id;
    @SerializedName("happiness_level")
    private int happinessLevel;
    @SerializedName("name_en")
    private String nameEn;
    @SerializedName("name_fi")
    private String nameFi;
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
     * Returns the product's tier.
     * @return int
     */
    public int getHappinessLevel() {
        return happinessLevel;
    }

    /**
     * Returns the product's name in English.
     * @return String
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * Returns the product's name in Finnish.
     * @return String
     */
    public String getNameFi() {
        return nameFi;
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
