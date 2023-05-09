package com.example.duantotnghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.model.OrderDetail;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class HistoryOrderAdapterDetail extends RecyclerView.Adapter<com.example.duantotnghiep.adapter.HistoryOrderAdapterDetail.HistoryOrderDetailVH> {
    Context context;
    List<OrderDetail> orderDetailList;

    public HistoryOrderAdapterDetail(Context context, List<OrderDetail> orderDetailList) {
        this.context = context;
        this.orderDetailList = orderDetailList;
    }

    @NonNull
    @Override
    public HistoryOrderAdapterDetail.HistoryOrderDetailVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_history_detail,parent, false);
        return new HistoryOrderAdapterDetail.HistoryOrderDetailVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryOrderAdapterDetail.HistoryOrderDetailVH holder, int position) {
        OrderDetail orderDetail = orderDetailList.get(position);
        Glide.with(context).load(orderDetail.getiMAGE()).into(holder.ivHistoryDetail);
        holder.tvNameHistoryDetail.setText(orderDetail.getfOODNAME());
        holder.tvQuantityHistoryDetail.setText("Qty: "+orderDetail.getqUANTITY());
//        holder.tvPriceHistoryDetail.setText(orderDetail.getpRICE()+" đ");
        holder.tvPriceHistoryDetail.setText(NumberFormat.getNumberInstance(new Locale("us")).format(orderDetailList.get(position).getpRICE()) +"đ");
    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public class HistoryOrderDetailVH extends RecyclerView.ViewHolder{
        ImageView ivHistoryDetail;
        TextView tvNameHistoryDetail,tvQuantityHistoryDetail, tvPriceHistoryDetail;

        public HistoryOrderDetailVH(@NonNull View itemView) {
            super(itemView);
            ivHistoryDetail = itemView.findViewById(R.id.ivHistoryDetail);
            tvNameHistoryDetail = itemView.findViewById(R.id.tvNameHistoryDetail);
            tvQuantityHistoryDetail = itemView.findViewById(R.id.tvQuantityHistoryDetail);
            tvPriceHistoryDetail = itemView.findViewById(R.id.tvPriceHistoryDetail);

//                clOrder.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Order order = orderList.get(getAdapterPosition());
//                        order.setExpanded(!order.getExpanded());
//                        notifyItemChanged(getAdapterPosition());
//                    }
//                });
        }
    }

}