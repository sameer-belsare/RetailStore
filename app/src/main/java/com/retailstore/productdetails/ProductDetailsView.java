package com.retailstore.productdetails;

import com.retailstore.Product;

/**
 * Created by sameer.belsare on 13/2/17.
 */
public interface ProductDetailsView {
    void showProductDetails(Product product);

    void showErrorMessage(String appErrorMessage);
}
