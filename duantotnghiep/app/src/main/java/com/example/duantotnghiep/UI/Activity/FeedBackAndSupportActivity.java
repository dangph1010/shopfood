package com.example.duantotnghiep.UI.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class FeedBackAndSupportActivity extends AppCompatActivity {

    Spinner spinnerPHHT;
    ImageView imgBack;
    Button btnSendFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_and_support);
        spinnerPHHT = this.findViewById(R.id.spinnerPHHT);
        imgBack = this.findViewById(R.id.imgBack);
        btnSendFeedback = this.findViewById(R.id.btnSendFeedback);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.phanloai, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPHHT.setAdapter(adapter);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackAndSupportActivity.this);
                builder.setTitle("Are you sure");
                builder.setMessage("Please confirm");
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(FeedBackAndSupportActivity.this, "We have taken note of your feedback", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.show();

            }
        });
    }
}