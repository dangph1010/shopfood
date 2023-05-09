package com.example.duantotnghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.UI.Fragment.CartFragment2;
import com.example.duantotnghiep.UI.Fragment.OrderHistoryFragment;
import com.example.duantotnghiep.model.Cart;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
            fragment = new CartFragment2();
        else if (position == 1)
            fragment = new OrderHistoryFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Happening";
        else if (position == 1)
            title = "History";
        return title;
    }

    public ViewPagerAdapter(
            @NonNull FragmentManager fm)
    {
        super(fm);
    }
}
