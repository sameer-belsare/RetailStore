package com.retailstore.productdetails;

import com.retailstore.ApplicationController;
import com.retailstore.Product;

import io.realm.Realm;

/**
 * Created by sameer.belsare on 13/2/17.
 */

public class ProductDetailsPresenterImpl implements ProductDetailsPresenter {
    private int id;
    private ProductDetailsView productDetailsView;
    private Realm realm;

    public ProductDetailsPresenterImpl(ProductDetailsView productDetailsView, int id) {
        this.productDetailsView = productDetailsView;
        this.id = id;
    }

    @Override
    public void getProductDetails() {
        realm = ApplicationController.getInstance().getRealmInstance();
        Product product = realm.where(Product.class).equalTo("id", this.id).findFirst();
        productDetailsView.showProductDetails(product);
    }

    @Override
    public void onDestroy() {
        productDetailsView = null;
    }

    @Override
    public void addToCart(int id) {
        Product product = realm.where(Product.class).equalTo("id", this.id).findFirst();
        realm.beginTransaction();
        product.setAddedToCart(true);
        realm.copyToRealmOrUpdate(product);
        realm.commitTransaction();
    }
}
