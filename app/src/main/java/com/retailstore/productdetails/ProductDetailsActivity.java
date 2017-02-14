package com.retailstore.productdetails;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.retailstore.Product;
import com.retailstore.R;
import com.retailstore.cart.CartActivity;
import com.retailstore.databinding.ActivityProductDetailsBinding;

/**
 * Created by sameer.belsare on 13/2/17.
 */

public class ProductDetailsActivity extends AppCompatActivity implements ProductDetailsView, View.OnClickListener {
    private ProductDetailsPresenterImpl productDetailsPresenter;
    private Product product;
    private ActivityProductDetailsBinding activityProductDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProductDetailsBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_product_details);
        int id = getIntent().getIntExtra("id", 0);
        activityProductDetailsBinding.btnCart.setOnClickListener(this);
        productDetailsPresenter = new ProductDetailsPresenterImpl(this, id);
        productDetailsPresenter.getProductDetails();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productDetailsPresenter.onDestroy();
    }

    @Override
    public void showProductDetails(Product product) {
        this.product = product;
        activityProductDetailsBinding.setProduct(product);
        int resID = getResources().getIdentifier(product.getImage(), "drawable",  getPackageName());
        Glide.with(this).load(resID).placeholder(R.mipmap.ic_launcher).into(activityProductDetailsBinding.ivImage);
    }

    @Override
    public void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.llDetailsMain), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void addedToCart(boolean success) {
        String message = getString(R.string.added_to_cart);
        if(!success) {
            message = getString(R.string.already_added_in_cart);
        }
        Snackbar snackbar = Snackbar.make(findViewById(R.id.llDetailsMain), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
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
            case R.id.btnCart:
                productDetailsPresenter.addToCart(product.getId());
                break;
        }
    }
}
