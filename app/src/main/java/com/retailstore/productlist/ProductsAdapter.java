package com.retailstore.productlist;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.retailstore.Constants;
import com.retailstore.R;

import java.util.List;

/**
 * Created by sameer.belsare on 13/2/17.
 */
public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> products;
    private Context mContext;
    private View.OnClickListener mItemClickListener;

    public ProductsAdapter(List<Product> productList, Context context,
                           View.OnClickListener clickListener) {
        products = productList;
        mContext = context;
        mItemClickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent,
                false);
        view.setOnClickListener(mItemClickListener);
        return new ProductsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductsListViewHolder viewHolder = (ProductsListViewHolder) holder;
        Product product = products.get(position);
        String imgPath = product.getImage();
        Glide.with(mContext).load(Uri.parse(imgPath)).asBitmap().placeholder(R.mipmap.ic_launcher).into(viewHolder.image);
        viewHolder.name.setText(product.getName());
        viewHolder.category.setText("Category: " + Constants.PRODUCT_CATEGORY.values()[product.getCategory()].name());
        viewHolder.price.setText("₹ "+product.getPrice());
        viewHolder.itemView.setTag(product.getId());
    }

    @Override
    public int getItemCount() {
        return (products != null ? products.size() : 0);
    }

    private static class ProductsListViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView price;
        public TextView category;

        public ProductsListViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            category = (TextView) itemView.findViewById(R.id.category);
        }
    }
}
