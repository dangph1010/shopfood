package com.example.duantotnghiep.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.UI.Fragment.MenuFragment;

public class YummySlideActivity extends AppCompatActivity {
    Button btnSlideBuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yummy_slide);

        btnSlideBuy =findViewById(R.id.btnSlideBuy);

        btnSlideBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}