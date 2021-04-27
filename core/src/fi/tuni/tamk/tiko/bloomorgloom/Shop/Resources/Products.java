package fi.tuni.tamk.tiko.bloomorgloom.Shop.Resources;

import java.util.List;

/**
 * A class representing a collection of products. Serialized to a POJO from JSON using google/gson.
 */
public class Products {
    private List<Product> products;
    private List<ProductType> types;

    public List<Product> getProducts() {
        return products;
    }
}
