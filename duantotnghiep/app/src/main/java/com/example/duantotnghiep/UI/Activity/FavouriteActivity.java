package com.example.duantotnghiep.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.adapter.TypeFoodAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.TypeFood;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteActivity extends AppCompatActivity {

    RecyclerView rcfavourite;
    RelativeLayout rltFavourite;
    TextView tvfavourite;
    View linefavourite;
    List<TypeFood> lstFood;
    TypeFoodAdapter typeFoodAdapter;

    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        rcfavourite = this.findViewById(R.id.rcFavourite);
        rltFavourite = this.findViewById(R.id.rltFavourite);
        tvfavourite = this.findViewById(R.id.tvfavourite);
        linefavourite = this.findViewById(R.id.linefavourite);
        rcfavourite.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        lstFood = new ArrayList<>();

        getFavourite();
    }

    private void getFavourite() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<List<TypeFood>> call = methods.getfavourite(username = SplashScreen.getUser().getuSERNAME());
        call.enqueue(new Callback<List<TypeFood>>() {
            @Override
            public void onResponse(Call<List<TypeFood>> call, Response<List<TypeFood>> response) {
                List<TypeFood> data = response.body();
                if (data.size()!=0){
                    rltFavourite.setVisibility(View.GONE);
                }else {
                    tvfavourite.setVisibility(View.GONE);
                    linefavourite.setVisibility(View.GONE);
                }
                for (TypeFood dt:data
                ) {
                    Log.d("Add:......", dt.fOODNAME);

                }

                typeFoodAdapter = new TypeFoodAdapter(getApplicationContext(),data);
                rcfavourite.setAdapter(typeFoodAdapter);
                typeFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TypeFood>> call, Throwable t) {

            }
        });
    }
}