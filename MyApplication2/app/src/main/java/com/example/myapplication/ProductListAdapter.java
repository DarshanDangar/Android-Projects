package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyHolder> {

    Context context;
    ArrayList<ListModel> arrayList;

    public ProductListAdapter(Context context, ArrayList<ListModel> productArrayList) {

        this.context = context;
        this.arrayList = productArrayList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_list, parent, false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.imageView.setImageResource(arrayList.get(position).mimage);
        holder.name.setText(arrayList.get(position).mname);
        holder.price.setText(context.getResources().getString(R.string.price_symbol) + arrayList.get(position).mprice);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductDetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("name",arrayList.get(position).mname);
                bundle.putString("price",context.getResources().getString(R.string.price_symbol) + arrayList.get(position).mprice);
                bundle.putString("desc",arrayList.get(position).mdescription);
                bundle.putInt("imag",arrayList.get(position).mimage);
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.custom_product_list_image);
            name = itemView.findViewById(R.id.custom_product_list_name);
            price = itemView.findViewById(R.id.custom_product_list_price);
        }

    }

}