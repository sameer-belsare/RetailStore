package com.retailstore.productdetails;

/**
 * Presenter class for product details
 * Created by sameer.belsare on 13/2/17.
 */
public interface ProductDetailsPresenter {
    void getProductDetails();

    void onDestroy();

    void addToCart(int id);
}
