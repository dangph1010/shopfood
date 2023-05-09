package com.example.duantotnghiep.UI.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.adapter.HomeAdapter;
import com.example.duantotnghiep.adapter.HomePopularAdapter;
import com.example.duantotnghiep.adapter.SearchFoodAdapter;
import com.example.duantotnghiep.adapter.TypeFoodAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Food;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    EditText search_box;
    public List<Food> lstFood = new ArrayList<>();
    public List<Food> lstSearch = new ArrayList<>();
    private RecyclerView recyclerViewSearch;
    private SearchFoodAdapter typeFoodAdapter;
    ImageView exit;

    //Slide

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewSearch = view.findViewById(R.id.search_rec);
        search_box = view.findViewById(R.id.edtSearchBox);
        exit = view.findViewById(R.id.exitSearch);

        getFoodApi();
        recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
        typeFoodAdapter = new SearchFoodAdapter(getContext(), lstFood);
        recyclerViewSearch.setAdapter(typeFoodAdapter);
        recyclerViewSearch.setHasFixedSize(true);

        recyclerViewSearch.setHasFixedSize(true);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment1 = new HomeFragment();
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_main,fragment1).commit();
            }
        });
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lstSearch.clear();
                typeFoodAdapter.notifyDataSetChanged();
                typeFoodAdapter = new SearchFoodAdapter(getContext(), lstFood);
                recyclerViewSearch.setAdapter(typeFoodAdapter);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String editable = search_box.getText().toString();
                if (editable.toString().isEmpty()) {
                    lstSearch.clear();
                    typeFoodAdapter.notifyDataSetChanged();
                    typeFoodAdapter = new SearchFoodAdapter(getContext(), lstFood);
                    recyclerViewSearch.setAdapter(typeFoodAdapter);

                } else {
                    lstSearch.clear();
                    typeFoodAdapter.notifyDataSetChanged();
                    searchProduct(editable.toString());
                    typeFoodAdapter = new SearchFoodAdapter(getContext(), lstSearch);
                    recyclerViewSearch.setAdapter(typeFoodAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    private void searchProduct(String foodname) {
        if (!foodname.isEmpty()) {
            for (Food fd : lstFood) {
                if (fd.getfOODNAME().toLowerCase(Locale.ROOT).contains(foodname)) {
                    lstSearch.add(fd);
                    Log.d("Log...",fd.getfOODNAME());
                }
            }
        }
    }

    private void getFoodApi() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<List<Food>> call = methods.getdata();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                List<Food> data = response.body();
                for (Food dt : data
                ) {
                    Log.d("Add:......", dt.iMAGE.toString());
                    lstFood.add(dt);
                }
                typeFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
    }
}