package com.example.duantotnghiep.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duantotnghiep.R;

public class FrequentlyAskedQuestionsActivity extends AppCompatActivity {
    ImageView ivCachDKy, ivLTN, ivHoanTien, ivDonHangO, ivPTTT, ivTTDH, ivViTri, ivHuyD, ivTTTV, ivLoiIch, imgBack;
    TextView tvDangKy, tvLTN, tvHoanTien, tvDonHangO, tvPTTT, tvTTDH, tvViTri, tvHuyD, tvTTTV, tvLoiIch;
    boolean isMenu = false;
    boolean isMenu2 = false;
    boolean isMenu3 = false;
    boolean isMenu4 = false;
    boolean isMenu5 = false;
    boolean isMenu6 = false;
    boolean isMenu7 = false;
    boolean isMenu8 = false;
    boolean isMenu9 = false;
    boolean isMenu10 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequently_asked_questions);
        ivCachDKy = this.findViewById(R.id.ivCachDky);
        ivLTN = this.findViewById(R.id.ivLTN);
        ivHoanTien = this.findViewById(R.id.ivHoanTien);
        ivDonHangO = this.findViewById(R.id.ivDonHangO);
        ivPTTT = this.findViewById(R.id.ivPTTT);
        ivTTDH = this.findViewById(R.id.ivTTDH);
        ivViTri = this.findViewById(R.id.ivViTri);
        ivHuyD = this.findViewById(R.id.ivHuyD);
        ivTTTV = this.findViewById(R.id.ivTTTV);
        ivLoiIch = this.findViewById(R.id.ivLoiIch);
        imgBack = this.findViewById(R.id.imgBack);

        tvDangKy = this.findViewById(R.id.tvDangKy);
        tvLTN = this.findViewById(R.id.tvLTN);
        tvHoanTien = this.findViewById(R.id.tvHoanTien);
        tvDonHangO = this.findViewById(R.id.tvDonHangO);
        tvPTTT = this.findViewById(R.id.tvPTTT);
        tvTTDH = this.findViewById(R.id.tvTTDH);
        tvViTri = this.findViewById(R.id.tvViTri);
        tvHuyD = this.findViewById(R.id.tvHuyD);
        tvTTTV = this.findViewById(R.id.tvTTTV);
        tvLoiIch = this.findViewById(R.id.tvLoiIch);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivCachDKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu){
                    ivCachDKy.setImageResource(R.drawable.img_9);
                    tvDangKy.setVisibility(View.VISIBLE);
                    isMenu = true;
                }else {
                    ivCachDKy.setVisibility(View.VISIBLE);
                    ivCachDKy.setImageResource(R.drawable.img_8);
                    tvDangKy.setVisibility(View.GONE);
                    isMenu = false;
                }
            }
        });

        ivLTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu2){
                    ivLTN.setImageResource(R.drawable.img_9);
                    tvLTN.setVisibility(View.VISIBLE);
                    isMenu2 = true;
                }else {
                    ivLTN.setVisibility(View.VISIBLE);
                    ivLTN.setImageResource(R.drawable.img_8);
                    tvLTN.setVisibility(View.GONE);
                    isMenu2 = false;
                }
            }
        });


        ivTTDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu6){
                    ivTTDH.setImageResource(R.drawable.img_9);
                    tvTTDH.setVisibility(View.VISIBLE);
                    isMenu6 = true;
                }else {
                    ivTTDH.setVisibility(View.VISIBLE);
                    ivTTDH.setImageResource(R.drawable.img_8);
                    tvTTDH.setVisibility(View.GONE);
                    isMenu6 = false;
                }
            }
        });

        ivViTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu7){
                    ivViTri.setImageResource(R.drawable.img_9);
                    tvViTri.setVisibility(View.VISIBLE);
                    isMenu7 = true;
                }else {
                    ivViTri.setVisibility(View.VISIBLE);
                    ivViTri.setImageResource(R.drawable.img_8);
                    tvViTri.setVisibility(View.GONE);
                    isMenu7 = false;
                }
            }
        });

        ivHuyD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu8){
                    ivHuyD.setImageResource(R.drawable.img_9);
                    tvHuyD.setVisibility(View.VISIBLE);
                    isMenu8 = true;
                }else {
                    ivHuyD.setVisibility(View.VISIBLE);
                    ivHuyD.setImageResource(R.drawable.img_8);
                    tvHuyD.setVisibility(View.GONE);
                    isMenu8 = false;
                }
            }
        });

        ivHoanTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu3){
                    ivHoanTien.setImageResource(R.drawable.img_9);
                    tvHoanTien.setVisibility(View.VISIBLE);
                    isMenu3 = true;
                }else {
                    ivHoanTien.setVisibility(View.VISIBLE);
                    ivHoanTien.setImageResource(R.drawable.img_8);
                    tvHoanTien.setVisibility(View.GONE);
                    isMenu3 = false;
                }
            }
        });

        ivDonHangO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu4){
                    ivDonHangO.setImageResource(R.drawable.img_9);
                    tvDonHangO.setVisibility(View.VISIBLE);
                    isMenu4 = true;
                }else {
                    ivDonHangO.setVisibility(View.VISIBLE);
                    ivDonHangO.setImageResource(R.drawable.img_8);
                    tvDonHangO.setVisibility(View.GONE);
                    isMenu4 = false;
                }
            }
        });

        ivPTTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu5){
                    ivPTTT.setImageResource(R.drawable.img_9);
                    tvPTTT.setVisibility(View.VISIBLE);
                    isMenu5 = true;
                }else {
                    ivPTTT.setVisibility(View.VISIBLE);
                    ivPTTT.setImageResource(R.drawable.img_8);
                    tvPTTT.setVisibility(View.GONE);
                    isMenu5 = false;
                }
            }
        });

        ivTTTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu9){
                    ivTTTV.setImageResource(R.drawable.img_9);
                    tvTTTV.setVisibility(View.VISIBLE);
                    isMenu9 = true;
                }else {
                    ivTTTV.setVisibility(View.VISIBLE);
                    ivTTTV.setImageResource(R.drawable.img_8);
                    tvTTTV.setVisibility(View.GONE);
                    isMenu9 = false;
                }
            }
        });

        ivLoiIch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenu10){
                    ivLoiIch.setImageResource(R.drawable.img_9);
                    tvLoiIch.setVisibility(View.VISIBLE);
                    isMenu10 = true;
                }else {
                    ivLoiIch.setVisibility(View.VISIBLE);
                    ivLoiIch.setImageResource(R.drawable.img_8);
                    tvLoiIch.setVisibility(View.GONE);
                    isMenu10 = false;
                }
            }
        });
    }
}