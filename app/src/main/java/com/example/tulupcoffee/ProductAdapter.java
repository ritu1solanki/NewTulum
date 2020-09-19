package com.example.tulupcoffee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private ArrayList<blog> blogArrayList;


    public ProductAdapter(Context context, ArrayList<blog> blogArrayList) {
        this.context = context;
        this.blogArrayList = blogArrayList;
    }
    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewproducts,parent,false);
        return new ProductAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        holder.productText.setText(blogArrayList.get(position).getProducttitle());
        //Here u will add all your text like price and all similar to above part

    }

    @Override
    public int getItemCount() {
        return blogArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView productText;
        ImageView productImage; //In case you find how to change image update this image object

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productText = itemView.findViewById(R.id.productText);
            productImage = itemView.findViewById(R.id.productimage);
        }
    }
}

