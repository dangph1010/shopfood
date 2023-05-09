package com.example.duantotnghiep.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.UI.Activity.FoodDetailActivity;
import com.example.duantotnghiep.model.Food;

import java.util.List;

public class HomePopularAdapter extends RecyclerView.Adapter<HomePopularAdapter.ViewHolder>{
    Context context;
    List<Food> lst;

    public HomePopularAdapter(Context context, List<Food> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items_home_popular, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = lst.get(position);
        Glide.with(context).load(lst.get(position).getiMAGE()).into(holder.imageView);
        holder.tvHomeitems.setText(lst.get(position).getfOODNAME());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDetailActivity.class);
                intent.putExtra("id",food.getfOODID());
                intent.putExtra("name",food.getfOODNAME());
                intent.putExtra("image",food.getiMAGE());
                intent.putExtra("price",food.getpRICE());
                intent.putExtra("quantity",food.getqUANTITY());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvHomeitems;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgHomeitems);
            tvHomeitems = itemView.findViewById(R.id.tvHomeitemsname);
        }
    }
}
