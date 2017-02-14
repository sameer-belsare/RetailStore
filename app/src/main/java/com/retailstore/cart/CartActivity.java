package com.retailstore.cart;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.retailstore.Product;
import com.retailstore.R;
import com.retailstore.databinding.ActivityCartBinding;
import com.retailstore.productdetails.ProductDetailsActivity;

import java.util.List;

/**
 * Created by sameer.belsare on 13/2/17.
 */
public class CartActivity extends AppCompatActivity implements CartView, View.OnClickListener {
    private CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    private ActivityCartBinding activityCartBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCartBinding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        cartPresenter = new CartPresenterImpl(this);
        cartPresenter.getItemsInCart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cartPresenter.onDestroy();
    }

    @Override
    public void loadCartData(List<Product> cartProducts) {
        if(cartProducts != null && cartProducts.size() > 0) {
            activityCartBinding.noProductsInCartText.setVisibility(View.GONE);
            cartAdapter = new CartAdapter(cartProducts, this, this);
            activityCartBinding.cartList.setAdapter(cartAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            activityCartBinding.cartList.setLayoutManager(linearLayoutManager);
            activityCartBinding.cartList.setHasFixedSize(true);
            cartPresenter.getTotalAmount();
        }
    }

    @Override
    public void showProductDetails(int id) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void setTotalAmount(int totalItems, int amount) {
        if(amount == 0){
            activityCartBinding.noProductsInCartText.setVisibility(View.VISIBLE);
        }
        activityCartBinding.setTotalItems(" ("+totalItems+" item/s)");
        activityCartBinding.setAmount(getString(R.string.rupee)+amount+getString(R.string.dash_slash));
    }

    @Override
    public void productDeletedSuccess() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.rlMain), getString(R.string.deleted_from_cart), Snackbar.LENGTH_SHORT);
        snackbar.show();
        cartAdapter.notifyDataSetChanged();
        cartPresenter.getTotalAmount();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_main:
                cartPresenter.onItemClicked((int)view.getTag());
                break;
            case R.id.delete:
                cartPresenter.deleteProductFromCart((Product)view.getTag());
                break;
        }
    }
}
