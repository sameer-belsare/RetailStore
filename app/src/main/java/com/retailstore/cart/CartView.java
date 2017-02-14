package com.retailstore.cart;

import com.retailstore.Product;

import java.util.List;

/**
 * View class for cart
 * Created by sameer.belsare on 13/2/17.
 */
public interface CartView {
    /**
     * Method to load cart data from DB
     * @param cartProducts
     */
    void loadCartData(List<Product> cartProducts);
    /**
     * Method to show product details from cart
     * @param id
     */
    void showProductDetails(int id);
    /**
     * Method to set total amount to pay for all cart items
     * @param totalItems
     * @param amount
     */
    void setTotalAmount(int totalItems, int amount);
    /**
     * Method to show product removed from cart message
     */
    void productDeletedSuccess();
}
