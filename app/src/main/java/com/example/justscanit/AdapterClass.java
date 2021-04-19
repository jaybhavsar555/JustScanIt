package com.example.justscanit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyHolderView> {
    Context context;
    public  ArrayList<Products> list;

    public AdapterClass(Context context, ArrayList<Products> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder,parent,false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderView holder, int position) {
        //Products model = list.get(position);
        holder.ProductNameTextView.setText(list.get(position).getProductname());
        holder.ProductPriceTextView.setText(list.get(position).getProductprice());
        holder.ProductCategoryTextView.setText(list.get(position).getProductcategory());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView ProductNameTextView;
        TextView ProductCategoryTextView;
        TextView ProductPriceTextView;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            ProductNameTextView = itemView.findViewById(R.id.product_name);
            ProductPriceTextView = itemView.findViewById(R.id.product_price);
            ProductCategoryTextView=itemView.findViewById(R.id.product_category);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,PaymentGatewayActivity.class);
            context.startActivity(intent);

        }
    }
}
