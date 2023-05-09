package com.example.duantotnghiep.UI.Fragment;

import static com.android.volley.VolleyLog.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duantotnghiep.MainActivity;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.adapter.HistoryOrderAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Order;
import com.example.duantotnghiep.model.OrderDetail;
import com.example.duantotnghiep.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryFragment extends Fragment {
    static String username;
    RecyclerView recyclerView;
    List<List<OrderDetail>> orderDetailListArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.rcvHistory);

//        username = SigninActivity.getUser();
        username = SplashScreen.getUser().getuSERNAME();
        if (username.equals("") == false){
            getHistory();
        }
        else{
        }
    }

    private void setRecycleView(List<Order> orderList) {
        HistoryOrderAdapter historyOrderAdapter = new HistoryOrderAdapter(getActivity(), username, orderList);
        recyclerView.setAdapter(historyOrderAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void getHistory() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<List<Order>> call = methods.getOrderList(username);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> orderList = response.body();
                setRecycleView(orderList);
            }
            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }
}