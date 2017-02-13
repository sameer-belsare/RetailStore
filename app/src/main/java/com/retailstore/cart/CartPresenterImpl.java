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
    private Realm realm;

    public CartPresenterImpl(CartView cartView) {
        this.cartView = cartView;
    }

    @Override
    public void getItemsInCart() {
        realm = ApplicationController.getInstance().getRealmInstance();
        cartProducts = realm.where(Product.class).equalTo("addedToCart", true).findAll();
        cartView.loadCartData(cartProducts);
    }

    @Override
    public void getTotalAmount() {
        int totalPrice = 0;
        if(cartProducts != null && cartProducts.size() > 0) {
            for (Product product : cartProducts) {
                totalPrice += product.getPrice();
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

    @Override
    public void deleteProductFromCart(Product product) {
        realm.beginTransaction();
        product.setAddedToCart(false);
        realm.copyToRealmOrUpdate(product);
        realm.commitTransaction();
        cartProducts = realm.where(Product.class).equalTo("addedToCart", true).findAll();
        cartView.productDeletedSuccess();
    }
}
