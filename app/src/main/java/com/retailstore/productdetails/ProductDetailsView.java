package com.retailstore.productdetails;

import com.retailstore.Product;

/**
 * View class for product details
 * Created by sameer.belsare on 13/2/17.
 */
public interface ProductDetailsView {
    /**
     * Method to show product details
     * @param product
     */
    void showProductDetails(Product product);
    /**
     * Method to show message
     * @param appErrorMessage
     */
    void showMessage(String appErrorMessage);
    /**
     * MEthod to set added to cart flag
     * @param success
     */
    void addedToCart(boolean success);
    /**
     * Method to set count of producst in cart
     * @param count
     */
    void setCartCount(int count);
}
