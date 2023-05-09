package com.example.duantotnghiep.UI.Fragment;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep.MainActivity;
import com.example.duantotnghiep.OnboardingActivity;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.Activity.OrderActivity;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.adapter.HomeAdapter;
import com.example.duantotnghiep.adapter.HomePopularAdapter;
import com.example.duantotnghiep.adapter.TypeFoodAdapter;
import com.example.duantotnghiep.adapter.SliderAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Food;
import com.example.duantotnghiep.model.SliderItem;
import com.example.duantotnghiep.model.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    User user = SplashScreen.getUser();
    String idUser;
    CardView cv1 , cv2;
    ViewPager2 viewPager2;
    ImageView imgSearch;
    Handler sliderHandler = new Handler();
    CardView btnlogin;
    TextView tv_menu , tvhome;
    RecyclerView rcvHome , rcvHome1 , rcvHome2;
    HomeAdapter homeadapter;
    HomePopularAdapter homepopularadapter;
    TypeFoodAdapter typeFoodAdapter;
    List<Food> lstFood;
//    GoogleMap maps;
//    public static final LatLng FOOD_POSITION = new LatLng(10.7805776, 106.6995463);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnlogin = view.findViewById(R.id.btnlogin);
        tv_menu = view.findViewById(R.id.tv_menu);
        rcvHome = view.findViewById(R.id.rcvHome);
        rcvHome1 = view.findViewById(R.id.rcvHome1);
        rcvHome2 = view.findViewById(R.id.rcvHome2);
        imgSearch = view.findViewById(R.id.imgSearch);
        tvhome = view.findViewById(R.id.tvhome);
        tvhome.setText("Welcome " + user.getfULLNAME());



        cv1 = view.findViewById(R.id.cv1);
        cv2 = view.findViewById(R.id.cv2);

        if(SplashScreen.getUser().getuSERNAME().equals("") == false) {
            idUser = SplashScreen.getUser().getuSERNAME();
            cv1.setVisibility(View.GONE);
            cv2.setVisibility(View.VISIBLE);

        }else{
            cv2.setVisibility(View.GONE);
            cv1.setVisibility(View.VISIBLE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false);
        rcvHome.setLayoutManager(linearLayoutManager);
        rcvHome1.setLayoutManager(linearLayoutManager1);
        rcvHome2.setLayoutManager(linearLayoutManager2);


        viewPager2 = view.findViewById(R.id.ViewPagerImageSlider);
        List<SliderItem> sliderItems = new ArrayList<>();
        //Tai cmt
        sliderItems.add(new SliderItem(R.drawable.a));
        sliderItems.add(new SliderItem(R.drawable.a1));
//        sliderItems.add(new SliderItem(R.drawable.a2));
        sliderItems.add(new SliderItem(R.drawable.a3));

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SigninActivity.class);
                startActivity(intent);
            }
        });

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2 ,  getContext()));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 4000);
            }
        });

        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment1 = new MenuFragment();
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frame_main,fragment1).commit();
                BottomNavigationView mBottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
                mBottomNavigationView.setSelectedItemId(R.id.menu);
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment1 = new SearchFragment();
                ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_main,fragment1).commit();
            }
        });

//        SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
//
//        smf.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap googleMap) {
//                maps = googleMap;
//                setLocation(FOOD_POSITION);
//            }
//        });
    }

//    public void setLocation(LatLng latLng){
//        maps.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        maps.addMarker(markerOptions);
//    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 4000);
        //getApi
        for(int i = 0; i<3; i++){
            if(MainActivity.getHomeFoods(i) == null){
//                setDataRecycleView(i, MainActivity.getHomeFoods(i));
                getFoodApi(i);
            }else{
                setDataRecycleView(i, MainActivity.getHomeFoods(i));
            }
        }
    }

    private void getFoodApi(int numb) {
        String status ="";
        switch (numb){
            case 0:
                status = "hot";
                break;
            case 1:
                status = "featured";
                break;
            case 2:
                status = "new";
                break;
        }
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<List<Food>> call = methods.getFoodByStatus(status);
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                List<Food> data = response.body();

                MainActivity.setHomeFoods(numb, data);
                setDataRecycleView(numb, data);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
    }

    public void setDataRecycleView(int numb, List<Food> data){
        switch (numb){
            case 0:
                homepopularadapter = new HomePopularAdapter(getActivity(),data);
                rcvHome.setAdapter(homepopularadapter);
                homepopularadapter.notifyDataSetChanged();
                break;
            case 1:
                homeadapter = new HomeAdapter(getActivity(),data);

                rcvHome1.setAdapter(homeadapter);
                homeadapter.notifyDataSetChanged();
                break;
            case 2:
                homeadapter = new HomeAdapter(getActivity(), data);

                rcvHome2.setAdapter(homeadapter);
                homeadapter.notifyDataSetChanged();
                break;
        }
    }
}