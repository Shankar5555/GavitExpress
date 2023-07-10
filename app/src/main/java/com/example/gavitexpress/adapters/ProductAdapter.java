package com.example.gavitexpress.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gavitexpress.R;
import com.example.gavitexpress.activities.ProductDetailActivity;
import com.example.gavitexpress.databinding.ItemProductBinding;
import com.example.gavitexpress.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHoldre>
{
    Context context;
    ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product>products){
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHoldre onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHoldre(LayoutInflater.from(context).inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHoldre holder, int position) {
        Product product = products.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);
        holder.binding.label.setText(product.getName());
        holder.binding.price.setText("INR " + product.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("name", product.getName());
                intent.putExtra("image", product.getImage());
                intent.putExtra("id", product.getId());
                intent.putExtra("price", product.getPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHoldre extends RecyclerView.ViewHolder{

        ItemProductBinding binding;

        public ProductViewHoldre(@NonNull View itemView) {
            super(itemView);
            binding = ItemProductBinding.bind(itemView);
        }
    }
}
