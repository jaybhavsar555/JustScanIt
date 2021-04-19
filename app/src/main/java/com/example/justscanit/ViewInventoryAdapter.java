package com.example.justscanit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewInventoryAdapter extends RecyclerView.Adapter<ViewInventoryAdapter.MyHolderView> {

    private List<Products> list;

    public ViewInventoryAdapter(List<Products> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewInventoryAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewInventoryAdapter.MyHolderView holder, int position) {
        Products model = list.get(position);
        holder.ProductNameTextView.setText(model.getProductname());
        holder.ProductBarCodeTextView.setText(model.getProductbarcode());
        holder.ProductPriceTextView.setText(model.getProductprice());
        holder.ProductCategoryTextView.setText(model.getProductcategory());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyHolderView extends RecyclerView.ViewHolder{
        TextView ProductNameTextView;
        TextView ProductCategoryTextView;
        TextView ProductPriceTextView;
        TextView ProductBarCodeTextView;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            ProductNameTextView = itemView.findViewById(R.id.product_name);
            ProductBarCodeTextView = itemView.findViewById(R.id.product_barcode);
            ProductPriceTextView = itemView.findViewById(R.id.product_price);
            ProductCategoryTextView=itemView.findViewById(R.id.product_category);
        }
    }
}
