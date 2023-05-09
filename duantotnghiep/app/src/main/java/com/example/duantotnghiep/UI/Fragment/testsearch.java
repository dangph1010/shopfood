package com.example.duantotnghiep.UI.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.adapter.HomeAdapter;
import com.example.duantotnghiep.adapter.TypeFoodAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Food;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class testsearch extends Fragment {
    EditText search_box;
    ImageView ivSearch;
    HomeAdapter homeadapter;
    private List<Food> lstFood;
    private RecyclerView recyclerViewSearch;
    private RecyclerView.LayoutManager layoutManager;
    private TypeFoodAdapter typeFoodAdapter;
    ProgressBar progressBar;


    //Slide

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewSearch = view.findViewById(R.id.recyclerSearch);
        progressBar = view.findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewSearch.setLayoutManager(layoutManager);
        recyclerViewSearch.setHasFixedSize(true);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
        lstFood = new ArrayList<>();

        searchFood("");
//        ivSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = new Fragment();
//                fragment = new HomeFragment();
//                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.push_up_in,R.anim.push_down_out)
//                        .commit();
//            }
//        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_testsearch, container, false);

    }


    //    @Override
//    public void onPause() {
//        super.onPause();
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        lstFood.clear();
//        menuAdapter.notifyDataSetChanged();
//
//    }
    private void searchFood(String key) {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<List<Food>> call = methods.getdata();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                progressBar.setVisibility(View.GONE);
                List<Food> data = response.body();
                for (Food dt:data
                ) {
                    Log.d("Add:......", dt.iMAGE.toString());

                }
//                menuAdapter = new MenuAdapter(getActivity(),data);
                recyclerViewSearch.setAdapter(typeFoodAdapter);
                typeFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error on: " + t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menuofsearch, menu);
//        SearchManager searchManager = (SearchManager) getSystem
        super.onCreateOptionsMenu(menu, inflater);
    }
    //    private void getMenuApi() {
//        Methods methods = ApiService.getRetrofit().create(Methods.class);
//        Call<List<Food>> call = methods.getdata();
//        call.enqueue(new Callback<List<Food>>() {
//            @Override
//            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
//                List<Food> data = response.body();
//                for (Food dt:data
//                ) {
//                    Log.d("Add:......", dt.iMAGE.toString());
//
//                }
//                menuAdapter = new MenuAdapter(getActivity(),data);
//                recyclerViewSearch.setAdapter(menuAdapter);
//                menuAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<Food>> call, Throwable t) {
//
//            }
//        });
//    }

}