package com.retailstore.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.retailstore.Product;
import com.retailstore.R;
import com.retailstore.productdetails.ProductDetailsActivity;

import java.util.List;

/**
 * Created by sameer.belsare on 13/2/17.
 */
public class CartActivity extends AppCompatActivity implements CartView, View.OnClickListener {
    private CartPresenter cartPresenter;
    private TextView noProductsInCartText;
    private RecyclerView listView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listView = (RecyclerView) findViewById(R.id.cartList);
        noProductsInCartText = (TextView) findViewById(R.id.noProductsInCartText);
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
            noProductsInCartText.setVisibility(View.GONE);
            cartAdapter = new CartAdapter(cartProducts, this, this);
            listView.setAdapter(cartAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            listView.setLayoutManager(linearLayoutManager);
            listView.setHasFixedSize(true);
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
    public void setTotalAmount(int amount) {
        ((TextView) findViewById(R.id.totalPrice)).setText(getString(R.string.rupee)+amount);
        ((TextView) findViewById(R.id.totalAmount)).setText(getString(R.string.rupee)+amount+getString(R.string.dash_slash));
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
