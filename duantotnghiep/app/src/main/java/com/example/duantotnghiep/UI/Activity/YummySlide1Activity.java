package com.example.duantotnghiep.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duantotnghiep.R;

public class YummySlide1Activity extends AppCompatActivity {

    Button btnSlideBuy1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yummy_slide1);
        btnSlideBuy1 = findViewById(R.id.btnSlideBuy1);

        btnSlideBuy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}