package com.example.duantotnghiep.UI.Fragment.tabLayout;

import static com.android.volley.VolleyLog.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.UI.Fragment.MenuFragment;
import com.example.duantotnghiep.adapter.TypeFoodAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.TypeFood;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CakeFragment extends Fragment{


    RecyclerView rcvCake;
    TypeFoodAdapter typeFoodAdapter;

    List<TypeFood> lstFood;
    static int tYpe = 0;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvCake = view.findViewById(R.id.rcvCake);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvCake.setLayoutManager(new GridLayoutManager(getContext(), 2));
        lstFood = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cake, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        tYpe = MenuFragment.getViewPager().getCurrentItem() + 1;
//        lstFood = MenuFragment.getMenuArrayChild(tYpe-1);
//        typeFoodAdapter = new TypeFoodAdapter(getContext(), lstFood);
//        rcvCake.setAdapter(typeFoodAdapter);
//        typeFoodAdapter.notifyDataSetChanged();
        if(MenuFragment.getMenuArrayChild(tYpe-1) == null){
            MenuThread menuThread = new MenuThread(rcvCake, tYpe);
            menuThread.start();
        }else if(MenuFragment.getMenuArrayChild(tYpe-1) != null){
            lstFood = MenuFragment.getMenuArrayChild(tYpe-1);
            typeFoodAdapter = new TypeFoodAdapter(rcvCake.getContext(), lstFood);
            rcvCake.setAdapter(typeFoodAdapter);
            typeFoodAdapter.notifyDataSetChanged();
        }
    }


//    private void getMenuApi(int tYpe) {
//        Methods methods = ApiService.getRetrofit().create(Methods.class);
//        Call<List<TypeFood>> call = methods.getttypefood(tYpe);
//        call.enqueue(new Callback<List<TypeFood>>() {
//            @Override
//            public void onResponse(Call<List<TypeFood>> call, Response<List<TypeFood>> response) {
//                List<TypeFood> data = response.body();
//                menuArray.add(data);
//            }
//
//            @Override
//            public void onFailure(Call<List<TypeFood>> call, Throwable t) {
//
//            }
//        });
//    }
}