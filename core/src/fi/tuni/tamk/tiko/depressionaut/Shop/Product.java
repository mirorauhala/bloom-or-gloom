package fi.tuni.tamk.tiko.depressionaut.Shop;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String type;
    private int level;
    private int price;
    private String texture;
    private List<ProductEffect> effects;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getPrice() {
        return price;
    }

    public String getTexture() {
        return texture;
    }

    public List<ProductEffect> getEffects() {
        return effects;
    }
}
