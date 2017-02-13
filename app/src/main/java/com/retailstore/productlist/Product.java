package com.retailstore.productlist;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sameer.belsare on 13/2/17.
 */
public class Product extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private int price;
    private String details;
    private String image;
    private int category;

    public Product(){

    }

    public Product(int id, String name, int price, String details, String image, int category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getDetails() {
        return details;
    }

    public int getCategory() {
        return category;
    }
}