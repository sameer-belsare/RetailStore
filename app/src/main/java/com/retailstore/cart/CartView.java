package com.retailstore.cart;

import com.retailstore.Product;

import java.util.List;

/**
 * Created by sameer.belsare on 13/2/17.
 */

public interface CartView {
    void loadCartData(List<Product> cartProducts);

    void showProductDetails(int id);

    void setTotalAmount(int amount);
}
