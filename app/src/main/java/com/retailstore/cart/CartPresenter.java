package com.retailstore.cart;

import com.retailstore.Product;

/**
 * Presenter class for Cart
 * Created by sameer.belsare on 13/2/17.
 */
public interface CartPresenter {
    /**
     * Method to get cart items from DB
     */
    void getItemsInCart();
    /**
     * Method to calculate total amount of all the products in cart
     */
    void getTotalAmount();
    /**
     * Method to handle list item click event
     * @param id
     */
    void onItemClicked(int id);
    /**
     * Method to destroy the view
     */
    void onDestroy();
    /**
     * Method to delete a product from cart
     * @param product
     */
    void deleteProductFromCart(Product product);
}
