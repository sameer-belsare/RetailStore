package com.retailstore.productlist;

import android.content.res.Resources;

import com.retailstore.ApplicationController;
import com.retailstore.Constants;
import com.retailstore.R;
import com.retailstore.RetailsApplication;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by sameer.belsare on 13/2/17.
 */
public class ProductPResenterImpl implements ProductPresenter {
    private ProductsView productsView;
    private Realm realm;

    public ProductPResenterImpl(ProductsView productsView) {
        this.productsView = productsView;
    }

    @Override
    public void loadAllProductsInDB() {
        if(productsView != null) {
            productsView.showProgress();
        }
        realm = ApplicationController.getInstance().getRealmInstance();
        Resources resources = RetailsApplication.getAppContext().getResources();
        String[] productNames = resources.getStringArray(R.array.product_name);
        for (int count=0; count<productNames.length; count++){
            Product product = new Product(count+1, productNames[count],
                                        (resources.getIntArray(R.array.product_price))[count],
                                        (resources.getStringArray(R.array.product_details))[count],
                                        "",
                                        (resources.getIntArray(R.array.product_category))[count]);
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(product);
            realm.commitTransaction();
        }
    }

    @Override
    public void getProducts(int category) {
        if(productsView != null) {
            productsView.showProgress();
        }
        RealmResults<Product> products;
        if(category == Constants.PRODUCT_CATEGORY.ALL.ordinal()){
            products = realm.where(Product.class).findAll();
        } else {
            products = realm.where(Product.class).equalTo("category", category).findAll();
        }
        productsView.showProducts(products);
        productsView.hideProgress();
    }

    @Override
    public void onItemClicked(int id) {
        if (productsView != null) {
            productsView.showProductDetails(id);
        }
    }

    @Override
    public void onDestroy() {
        productsView = null;
    }
}
