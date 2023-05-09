package com.example.duantotnghiep.adapter;

import static com.android.volley.VolleyLog.TAG;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.UI.Fragment.OrderHistoryFragment;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Food;
import com.example.duantotnghiep.model.Order;
import com.example.duantotnghiep.model.OrderDetail;
import com.example.duantotnghiep.model.User;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.HistoryOrderVH> {
    Context context;
    List<Order> orderList;
    List<OrderDetail> orderDetailList = new ArrayList<>();
    static String username;

    public HistoryOrderAdapter(Context context, String username, List<Order> orderList) {
        this.context = context;
        this.username = username;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public HistoryOrderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_history,parent, false);
        return new HistoryOrderVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryOrderVH holder, int position) {
        Order order = orderList.get(position);
//        getHistoryDetail(orderList);
        getDetail(order.getOrderid(), holder.rcvHistoryDetail, context);
        String color;
        switch (order.getoRDSTATUS()){
            case 0:
                color = "#FF2D2D";
                break;
            case 2:
                color = "#FFA02D";
                break;
            default:
                color = "#0BE33F";
                break;
        }
        holder.tvOrderId.setText( "Order #"+order.getOrderid() );
        holder.tvOrderTime.setText( getDate(order.gettIME()) );
        holder.tvOrderStatus.setText( getStatus(order.getoRDSTATUS()) );
        holder.tvOrderStatus.setTextColor(Color.parseColor(color));
//        holder.tvOrderTotal.setText( order.gettOTAL()+" đ" );
        int price = Integer.parseInt(orderList.get(position).gettOTAL());
        holder.tvOrderTotal.setText(NumberFormat.getNumberInstance(new Locale("us")).format(price)+"đ");
        boolean expanded = orderList.get(position).getExpanded();
//        Log.i(TAG, "log position: "+position);
        holder.rlOrderDetail.setVisibility(expanded ? View.VISIBLE: View.GONE);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class HistoryOrderVH extends RecyclerView.ViewHolder{
        TextView tvOrderId,tvOrderTime, tvOrderStatus, tvOrderTotal;
        RecyclerView rcvHistoryDetail;
        ConstraintLayout clOrder;
        RelativeLayout rlOrderDetail;

        public HistoryOrderVH(@NonNull View itemView) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvOrderTime = itemView.findViewById(R.id.tvOrderTime);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            tvOrderTotal = itemView.findViewById(R.id.tvOrderTotal);

            rcvHistoryDetail = itemView.findViewById(R.id.rcvHistoryDetail);
            clOrder = itemView.findViewById(R.id.clOrder);
            rlOrderDetail = itemView.findViewById(R.id.rlOrderDetail);
            clOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Order order = orderList.get(getAdapterPosition());
                    order.setExpanded(!order.getExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

    public String getStatus(int i){
        switch (i){
            case 0:
                return "Canceled";
            case 2:
                return "Pending";
            case 3:
                return "Confirmed";
            default:
                return "not found";
        }
    }
    public String getDate(String fullDate){
        return fullDate.substring(0,10);
    }

    public void getDetail(int orderId, RecyclerView recyclerView, Context context){

        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<List<OrderDetail>> call = methods.getOrderDetail(username, orderId);
        call.enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                orderDetailList = response.body();
                HistoryOrderAdapterDetail historyOrderAdapter = new HistoryOrderAdapterDetail(context , orderDetailList);
                recyclerView.setAdapter(historyOrderAdapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {

            }
        });
    }
}
