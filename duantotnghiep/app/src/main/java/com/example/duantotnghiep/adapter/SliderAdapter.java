package com.example.duantotnghiep.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SearchActivity;
import com.example.duantotnghiep.UI.Activity.AboutYummyActivity;
import com.example.duantotnghiep.UI.Activity.TermsAndConditionsActivity;
import com.example.duantotnghiep.UI.Activity.YummySlide1Activity;
import com.example.duantotnghiep.UI.Activity.YummySlide2Activity;
import com.example.duantotnghiep.UI.Activity.YummySlideActivity;
import com.example.duantotnghiep.model.SliderItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{
    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;
    Context context;

    public SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2, Context context) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container,parent,false));
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(SliderAdapter.SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        if (position == sliderItems.size() - 2){
            viewPager2.post(runnable);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    Intent intent = new Intent( context, YummySlideActivity.class);
                    context.startActivity(intent);
                }else if (position == 1){
                    Intent intent = new Intent( context, YummySlide1Activity.class);
                    context.startActivity(intent);
                }else if (position == 2){
                    Intent intent = new Intent( context, YummySlide2Activity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;

        SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImage(SliderItem sliderItem){
            imageView.setImageResource(sliderItem.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
