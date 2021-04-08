package fi.tuni.tamk.tiko.depressionaut.Shop;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private int type;
    private int cat;
    private int level;
    private String texture;
    private List<ProductEffect> effects;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getCat() {
        return cat;
    }

    public int getLevel() {
        return level;
    }

    public String getTexture() {
        return texture;
    }

    public List<ProductEffect> getEffects() {
        return effects;
    }
}
