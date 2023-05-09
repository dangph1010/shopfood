package com.example.duantotnghiep.UI.Fragment.tabLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep.UI.Fragment.MenuFragment;
import com.example.duantotnghiep.adapter.TypeFoodAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.TypeFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuThread extends Thread{
    private RecyclerView recyclerView;
    private TypeFoodAdapter typeFoodAdapter;

    private int numb;

    public MenuThread(RecyclerView recyclerView, int numb){
        this.recyclerView = recyclerView;
        this.numb = numb;
    }

    @Override
    public void run() {
        super.run();
        getMenuApi(numb);
    }

    private void getMenuApi(int tYpe) {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<List<TypeFood>> call = methods.getttypefood(tYpe);
        call.enqueue(new Callback<List<TypeFood>>() {
            @Override
            public void onResponse(Call<List<TypeFood>> call, Response<List<TypeFood>> response) {
                List<TypeFood> data = response.body();
                typeFoodAdapter = new TypeFoodAdapter(recyclerView.getContext(), data);
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(typeFoodAdapter);
                        typeFoodAdapter.notifyDataSetChanged();
                        MenuFragment.setMenuArrayChild(tYpe-1, data);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<TypeFood>> call, Throwable t) {

            }
        });
    }
}
