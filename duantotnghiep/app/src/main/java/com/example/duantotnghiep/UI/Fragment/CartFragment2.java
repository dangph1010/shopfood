package com.example.duantotnghiep.UI.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentResultListener;

import androidx.fragment.app.FragmentActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.Activity.OrderActivity;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.adapter.CartAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Cart;
import com.example.duantotnghiep.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment2 extends Fragment {

    static RecyclerView rcvCart;
    static RelativeLayout rltCart;
    static int foodId;
    static Button btnCart;
    CartAdapter cartAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnCart = view.findViewById(R.id.btnCart);
        rcvCart = view.findViewById(R.id.rcvCart);
        rltCart = view.findViewById(R.id.rltCart);
        btnCart = view.findViewById(R.id.btnCart);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), OrderActivity.class);
                startActivity(i);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rcvCart.setLayoutManager(linearLayoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_2, container, false);

        setRcvCart();

        return view;
    }


    public static void setRcvCart() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        User temp = new User();
        temp.setuSERNAME(SplashScreen.getUser().getuSERNAME());
        Call<List<Cart>> call = methods.getdatacart(temp);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                List<Cart> data = response.body();
                CartAdapter cartAdapter;
                if (data.size()!=0){
                    rltCart.setVisibility(View.GONE);
                }else{
                    btnCart.setVisibility(View.GONE);
                }
                for (Cart dt : data
                ) {
                    foodId = dt.getfOODID();
                    Log.d("NAHHHHHH:", String.valueOf(foodId));
                    //Toast.makeText(getActivity(),dt.fOODNAME,Toast.LENGTH_SHORT).show();
                }

                cartAdapter = new CartAdapter(rcvCart.getContext(), data);
                rcvCart.setAdapter(cartAdapter);
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
    }

}
