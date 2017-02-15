package com.retailstore.productlist;

import com.retailstore.Product;

import java.util.List;

/**
 * View class for product list
 * Created by sameer.belsare on 13/2/17.
 */
public interface ProductsView {
    /**
     * Method to show progress bar
     */
    void showProgress();
    /**
     * Method to hide progress bar
     */
    void hideProgress();
    /**
     * Method to show error message
     * @param message
     */
    void showErrorMessage(String message);
    /**
     * Method to show list of products
     * @param products
     */
    void showProducts(List<Product> products);
    /**
     * Method to show product details
     * @param id
     */
    void showProductDetails(int id);
    /**
     * Method to set count of producst in cart
     * @param count
     */
    void setCartCount(int count);
}
