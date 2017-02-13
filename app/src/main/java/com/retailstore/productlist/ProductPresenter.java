package com.retailstore.productlist;

/**
 * Created by sameer.belsare on 13/2/17.
 */

public interface ProductPresenter {
    void loadAllProductsInDB();

    void getProducts(int category);

    void onItemClicked(int id);

    void onDestroy();
}
