package com.retailstore.productdetails;

import com.retailstore.Product;

/**
 * Created by sameer.belsare on 13/2/17.
 */

public interface ProductDetailsPresenter {
    void getProductDetails();

    void onDestroy();

    void addToCart(int id);
}
