package com.example.duantotnghiep.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.duantotnghiep.R;

public class PaymentMethodActivity extends AppCompatActivity {

    ImageView btnCash;
    private boolean isClick = false;
    ImageView imgBackPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        btnCash = this.findViewById(R.id.btnCash);
        imgBackPayment = this.findViewById(R.id.imgBackPayment);

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick){
                    isClick = false;
                    btnCash.setImageResource(R.drawable.ic_circle_yelow);
                }else{
                    isClick = true;
                    btnCash.setImageResource(R.drawable.ic_circle);
                }
            }
        });
        imgBackPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}