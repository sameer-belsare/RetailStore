package com.retailstore.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.retailstore.Constants;
import com.retailstore.Product;
import com.retailstore.R;
import com.retailstore.cart.CartActivity;
import com.retailstore.productdetails.ProductDetailsActivity;

import java.util.List;

/**
 * Created by sameer.belsare on 13/2/17.
 */
public class ProductListActivity extends AppCompatActivity implements ProductsView, View.OnClickListener, AdapterView.OnItemSelectedListener {
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
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        productPresenter = new ProductPresenterImpl(this);
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
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_main:
                productPresenter.onItemClicked((int)view.getTag());
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        productPresenter.getProducts(Constants.PRODUCT_CATEGORY.values()[pos].ordinal());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        productPresenter.getProducts(Constants.PRODUCT_CATEGORY.ALL.ordinal());
    }
}
