package com.retailstore.productlist;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.retailstore.Constants;
import com.retailstore.R;

import java.util.List;

/**
 * Created by sameer.belsare on 13/2/17.
 */
public class ProductListActivity extends AppCompatActivity implements ProductsView, View.OnClickListener{
    private RecyclerView listView;
    private ProgressBar progressBar;
    private ProductPresenter productPresenter;
    private TextView noProductsText;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        listView = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        noProductsText = (TextView) findViewById(R.id.noProductsText);
        productPresenter = new ProductPResenterImpl(this);
        productPresenter.loadAllProductsInDB();
        productPresenter.getProducts(Constants.PRODUCT_CATEGORY.ALL.ordinal());
    }

    @Override
    protected void onDestroy() {
        productPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
        noProductsText.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {
        if(products == null || products.size() <= 0) {
            noProductsText.setVisibility(View.VISIBLE);
        }
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rlMain), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showProducts(List<Product> products) {
        noProductsText.setVisibility(View.GONE);
        this.products = products;
        ProductsAdapter productsAdapter = new ProductsAdapter(products, this, this);
        listView.setAdapter(productsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(linearLayoutManager);
        listView.setHasFixedSize(true);
    }

    @Override
    public void showProductDetails(int id) {

    }

    @Override
    public void onClick(View view) {

    }
}
