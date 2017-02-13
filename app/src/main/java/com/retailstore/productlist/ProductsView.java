package com.retailstore.productlist;

import com.retailstore.Product;

import java.util.List;

/**
 * Created by sameer.belsare on 13/2/17.
 */
public interface ProductsView {
    void showProgress();

    void hideProgress();

    void showErrorMessage(String message);

    void showProducts(List<Product> products);

    void showProductDetails(int id);
}
