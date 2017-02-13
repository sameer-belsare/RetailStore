package com.retailstore.cart;

import com.retailstore.ApplicationController;
import com.retailstore.Product;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by sameer.belsare on 13/2/17.
 */

public class CartPresenterImpl implements CartPresenter {
    private CartView cartView;
    private RealmResults<Product> cartProducts;

    public CartPresenterImpl(CartView cartView) {
        this.cartView = cartView;
    }

    @Override
    public void getItemsInCart() {
        Realm realm = ApplicationController.getInstance().getRealmInstance();
        cartProducts = realm.where(Product.class).equalTo("addedToCart", true).findAll();
        cartView.loadCartData(cartProducts);
    }

    @Override
    public void getTotalAmount() {
        int totalPrice = 0;
        if(cartProducts != null && cartProducts.size() > 0) {
            for (Product product : cartProducts) {
                totalPrice += totalPrice + product.getPrice();
            }
        }
        cartView.setTotalAmount(totalPrice);
    }

    @Override
    public void onItemClicked(int id) {
        if (cartView != null) {
            cartView.showProductDetails(id);
        }
    }

    @Override
    public void onDestroy() {
        cartView = null;
    }
}
