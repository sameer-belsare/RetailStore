package com.retailstore.productlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.retailstore.Product;
import com.retailstore.R;
import com.retailstore.databinding.ProductItemBinding;

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
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ProductItemBinding productItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.product_item, parent, false);
        productItemBinding.getRoot().setOnClickListener(mItemClickListener);
        return new ProductsListViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductsListViewHolder viewHolder = (ProductsListViewHolder) holder;
        Product product = products.get(position);
        viewHolder.bind(product);
        int resID = mContext.getResources().getIdentifier(product.getImage(), "drawable",  mContext.getPackageName());
        Glide.with(mContext).load(resID).placeholder(R.mipmap.ic_launcher).into(viewHolder.productItemBinding.image);
        viewHolder.itemView.setTag(product.getId());
    }

    @Override
    public int getItemCount() {
        return (products != null ? products.size() : 0);
    }

    private static class ProductsListViewHolder extends RecyclerView.ViewHolder {
        private final ProductItemBinding productItemBinding;

        public ProductsListViewHolder(ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());
            this.productItemBinding = productItemBinding;
        }

        public void bind(Product product) {
            productItemBinding.setProduct(product);
            productItemBinding.executePendingBindings();
        }
    }
}
