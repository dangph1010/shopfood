package com.example.duantotnghiep.UI.Fragment;

import static com.android.volley.VolleyLog.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.duantotnghiep.R;

import com.example.duantotnghiep.UI.Fragment.tabLayout.CakeFragment;
import com.example.duantotnghiep.UI.loginscreen.SigninAdapter;
import com.example.duantotnghiep.UI.loginscreen.SigninFragment_1;
import com.example.duantotnghiep.UI.loginscreen.SigninFragment_2;
import com.example.duantotnghiep.UI.loginscreen.SigninFragment_4;
import com.example.duantotnghiep.UI.loginscreen.SigninFragment_5;
import com.example.duantotnghiep.adapter.MenuPagerAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.TypeFood;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {

    TabLayout tabLayout;
    static ViewPager viewPager;
    MenuPagerAdapter menuPagerAdapter;
    static List<List<TypeFood>> menuArray = new ArrayList<>();

    public static List<TypeFood> getMenuArrayChild(int numb) {
        return menuArray.get(numb);
    }

    public static void setMenuArrayChild(int position, List<TypeFood> menuArrayChild) {
        MenuFragment.menuArray.set(position, menuArrayChild);
    }

    public static ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        new Thread(new MenuFragment()).start();
        tabLayout = view.findViewById(R.id.tlMenu);
        viewPager = view.findViewById(R.id.viewPager2);
        viewPagerSetup();

        for (int i = 0; i <4 ; i++){
//            setMenuArrayChild(i, null);
            menuArray.add(null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        return view;
    }

    public void viewPagerSetup(){
        menuPagerAdapter = new MenuPagerAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        menuPagerAdapter.addFragment(new CakeFragment());
        menuPagerAdapter.addFragment(new CakeFragment());
        menuPagerAdapter.addFragment(new CakeFragment());
        menuPagerAdapter.addFragment(new CakeFragment());
        viewPager.setAdapter(menuPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

//    private void getMenuApi(int tYpe) {
//        Methods methods = ApiService.getRetrofit().create(Methods.class);
//        Call<List<TypeFood>> call = methods.getttypefood(tYpe);
//        call.enqueue(new Callback<List<TypeFood>>() {
//            @Override
//            public void onResponse(Call<List<TypeFood>> call, Response<List<TypeFood>> response) {
//                List<TypeFood> data = response.body();
//                menuArray.add(data);
//                Log.i(TAG, "onResponse: "+menuArray.toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<TypeFood>> call, Throwable t) {
//
//            }
//        });
//    }

//    @Override
//    public void run() {
//
//    }
}
