package com.retailstore.cart;

import com.retailstore.Product;

/**
 * Presenter class for Cart
 * Created by sameer.belsare on 13/2/17.
 */
public interface CartPresenter {
    void getItemsInCart();

    void getTotalAmount();

    void onItemClicked(int id);

    void onDestroy();

    void deleteProductFromCart(Product product);
}
