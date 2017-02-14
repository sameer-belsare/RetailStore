package com.retailstore.cart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.retailstore.Product;
import com.retailstore.R;
import com.retailstore.databinding.CartItemBinding;

import java.util.List;

/**
 * Adapter class for Cart list
 * Created by sameer.belsare on 13/2/17.
 */
public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> products;
    private Context mContext;
    private View.OnClickListener mItemClickListener;

    public CartAdapter(List<Product> productList, Context context,
                           View.OnClickListener clickListener) {
        products = productList;
        mContext = context;
        mItemClickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        CartItemBinding cartItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.cart_item, parent, false);
        cartItemBinding.getRoot().setOnClickListener(mItemClickListener);
        return new CartListViewHolder(cartItemBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartListViewHolder viewHolder = (CartListViewHolder) holder;
        Product product = products.get(position);
        viewHolder.bind(product);
        int resID = mContext.getResources().getIdentifier(product.getImage(), "drawable",  mContext.getPackageName());
        Glide.with(mContext).load(resID).asBitmap().placeholder(R.mipmap.ic_launcher).into(viewHolder.cartItemBinding.image);
        viewHolder.itemView.setTag(product.getId());
        viewHolder.cartItemBinding.delete.setOnClickListener(mItemClickListener);
        viewHolder.cartItemBinding.delete.setTag(product);
    }

    @Override
    public int getItemCount() {
        return (products != null ? products.size() : 0);
    }

    /**
     * View holder for CartAdapter
     */
    private static class CartListViewHolder extends RecyclerView.ViewHolder {
        private final CartItemBinding cartItemBinding;

        public CartListViewHolder(CartItemBinding cartItemBinding) {
            super(cartItemBinding.getRoot());
            this.cartItemBinding = cartItemBinding;
        }

        public void bind(Product product) {
            cartItemBinding.setProduct(product);
            cartItemBinding.executePendingBindings();
        }
    }
}
