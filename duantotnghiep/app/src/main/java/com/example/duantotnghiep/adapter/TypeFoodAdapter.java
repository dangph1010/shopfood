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
import com.example.duantotnghiep.model.TypeFood;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TypeFoodAdapter extends RecyclerView.Adapter<TypeFoodAdapter.ViewHolder> {
    Context context;
    List<TypeFood> lst;

    public TypeFoodAdapter(Context context, List<TypeFood> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TypeFood typeFood = lst.get(position);
        Glide.with(context).load(lst.get(position).getiMAGE()).into(holder.imgMenu);
        holder.tvMenuName.setText(lst.get(position).getfOODNAME());
//        holder.tvMenuPrice.setText(lst.get(position).getpRICE() + " VNĐ");
        holder.tvMenuPrice.setText(NumberFormat.getNumberInstance(new Locale("us")).format(lst.get(position).getpRICE()) +"đ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodDetailActivity.class);
                intent.putExtra("id", typeFood.getfOODID());
                intent.putExtra("name", typeFood.getfOODNAME());
                intent.putExtra("image", typeFood.getiMAGE());
                intent.putExtra("price", typeFood.getpRICE());
                intent.putExtra("quantity", typeFood.getqUANTITY());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return lst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMenu;
        TextView tvMenuName, tvMenuPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            imgMenu = itemView.findViewById(R.id.imgMenu);
            tvMenuName = itemView.findViewById(R.id.tvMenuName);
            tvMenuPrice = itemView.findViewById(R.id.tvMenuPrice);
        }
    }
}
