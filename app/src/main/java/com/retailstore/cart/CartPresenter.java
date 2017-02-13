package com.retailstore.cart;

/**
 * Created by sameer.belsare on 13/2/17.
 */

public interface CartPresenter {
    void getItemsInCart();

    void getTotalAmount();

    void onItemClicked(int id);

    void onDestroy();
}
