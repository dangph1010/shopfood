package com.example.duantotnghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.model.Cart;
import com.example.duantotnghiep.model.OrderDetail;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    Context context;
    List<Cart> lst;


    public OrderAdapter(Context context, List<Cart> lst) {
        this.context = context;
        this.lst = lst;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items_pay, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context).load(lst.get(position).getiMAGE()).into(holder.imageView);
        holder.tvPayNameItems.setText(lst.get(position).getfOODNAME());
        int price = lst.get(position).getpRICE() * lst.get(position).getqUANTITY();
        holder.tvPayPriceItems.setText(price+" VNĐ");
        holder.tvPayQuantityItems.setText(String.valueOf(lst.get(position).getqUANTITY()));

    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvPayNameItems;
        TextView tvPayQuantityItems;
        TextView tvPayPriceItems;
        EditText etPhone;
        EditText etLocation;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivImgPayItem);
            tvPayNameItems = itemView.findViewById(R.id.tvNameItemPay);
            tvPayQuantityItems = itemView.findViewById(R.id.tvQuantityPay);
            tvPayPriceItems = itemView.findViewById(R.id.tvPriceItemPay);
            etPhone = itemView.findViewById(R.id.etPhoneOrder);
        }
    }
}
