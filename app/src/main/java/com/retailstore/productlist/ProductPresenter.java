package com.retailstore.productlist;

/**
 * Presenter class for Product list
 * Created by sameer.belsare on 13/2/17.
 */
public interface ProductPresenter {
    /**
     * Method to load all products(dummy data) in DB
     */
    void loadAllProductsInDB();
    /**
     * Method to get Product list based on category
     * @param category
     */
    void getProducts(int category);
    /**
     * Method to handle list item click event
     * @param id
     */
    void onItemClicked(int id);
    /**
     * Method to destroy the view
     */
    void onDestroy();
}
