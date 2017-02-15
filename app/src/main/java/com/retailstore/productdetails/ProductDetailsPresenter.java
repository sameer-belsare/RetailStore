package com.retailstore.productdetails;

/**
 * Presenter class for product details
 * Created by sameer.belsare on 13/2/17.
 */
public interface ProductDetailsPresenter {
    /**
     * Method to get product details from DB
     */
    void getProductDetails();
    /**
     * Method to destroy the view
     */
    void onDestroy();
    /**
     * Method to add product in cart
     * @param id
     */
    void addToCart(int id);
    /**
     * Method to refresh count of products in cart
     */
    void refreshCartCount();
}
