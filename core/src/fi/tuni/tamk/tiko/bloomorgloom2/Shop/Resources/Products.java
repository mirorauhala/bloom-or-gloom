package fi.tuni.tamk.tiko.bloomorgloom2.Shop.Resources;

import java.util.List;

/**
 * A class representing a collection of products. Serialized to a POJO from JSON using google/gson.
 */
public class Products {
    private List<fi.tuni.tamk.tiko.bloomorgloom2.Shop.Resources.Product> products;
    private List<ProductType> types;

    public List<Product> getProducts() {
        return products;
    }
}
