package com.retailstore.cart;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.retailstore.Constants;
import com.retailstore.Product;
import com.retailstore.R;

import java.util.List;

/**
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent,
                false);
        view.setOnClickListener(mItemClickListener);
        return new CartListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartListViewHolder viewHolder = (CartListViewHolder) holder;
        Product product = products.get(position);
        int resID = mContext.getResources().getIdentifier(product.getImage(), "drawable",  mContext.getPackageName());
        Glide.with(mContext).load(resID).asBitmap().placeholder(R.mipmap.ic_launcher).into(viewHolder.image);
        viewHolder.name.setText(product.getName());
        viewHolder.category.setText(mContext.getString(R.string.category_string) + Constants.PRODUCT_CATEGORY.values()[product.getCategory()].name());
        viewHolder.price.setText(mContext.getString(R.string.rupee)+product.getPrice());
        viewHolder.itemView.setTag(product.getId());
        viewHolder.delete.setOnClickListener(mItemClickListener);
        viewHolder.delete.setTag(product);
    }

    @Override
    public int getItemCount() {
        return (products != null ? products.size() : 0);
    }

    private static class CartListViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView price;
        public TextView category;
        public Button delete;

        public CartListViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            category = (TextView) itemView.findViewById(R.id.category);
            delete = (Button) itemView.findViewById(R.id.delete);
        }
    }
}
