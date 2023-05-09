package com.example.duantotnghiep.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duantotnghiep.R;

public class YummySlide2Activity extends AppCompatActivity {

    Button btnSlideBuy2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yummy_slide2);
        btnSlideBuy2 = findViewById(R.id.btnSlideBuy2);

        btnSlideBuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}