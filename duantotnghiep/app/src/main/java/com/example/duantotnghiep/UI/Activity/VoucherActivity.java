package com.example.duantotnghiep.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Cart;
import com.example.duantotnghiep.model.Order;
import com.example.duantotnghiep.model.VoucherCode;
import com.example.duantotnghiep.model.VoucherDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoucherActivity extends AppCompatActivity {

    EditText etVoucherCode;
    AppCompatButton btnAccept;
    ImageView imgBackVoucher;
    ImageView btnCash;
    private boolean isClick = false;
    // Biến constant được dùng để định danh dữ liệu được truyền giữa các Activity
    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final String QUANTITY_DATA = "QUANTITY_DATA";
    public static final String CODE_DATA = "CODE_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        etVoucherCode = findViewById(R.id.etVoucherCode);
        btnAccept = findViewById(R.id.btnAccept);
        imgBackVoucher = findViewById(R.id.imgBackVoucher);
        btnCash = findViewById(R.id.btnCashVoucher);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postVoucher();
            }
        });

        imgBackVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
    }

    public void postVoucher() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        VoucherCode voucher = new VoucherCode();
        voucher.setuSERNAME(SplashScreen.getUser().getuSERNAME().toString());
        voucher.setvOUCHERCODE(etVoucherCode.getText().toString());
        Call<List<VoucherDetail>> call = methods.getVoucher(voucher);
        call.enqueue(new Callback<List<VoucherDetail>>() {
            @Override
            public void onResponse(Call<List<VoucherDetail>> call, Response<List<VoucherDetail>> response) {
                List<VoucherDetail> data = response.body();
                if(data.size() == 0 || data == null){
                    Toast.makeText(VoucherActivity.this, "Voucher has been used or not exist", Toast.LENGTH_LONG ).show();
                }else{
                    for (VoucherDetail dt : data) {

                        // Tạo một Intent mới để chứa dữ liệu trả về
                        final Intent i = new Intent();

                        // Truyền data vào intent
                        i.putExtra(EXTRA_DATA, dt.getvOUCHER().toString());
                        i.putExtra(QUANTITY_DATA, dt.getqUANTITY());
                        i.putExtra(CODE_DATA, dt.getvOUCHERCODE().toString());

                        // Đặt resultCode là Activity.RESULT_OK to
                        // thể hiện đã thành công và có chứa kết quả trả về
                        setResult(Activity.RESULT_OK, i);

                        // gọi hàm finish() để đóng Activity hiện tại và trở về MainActivity.
                        finish();

                    }
                    Toast.makeText(getApplicationContext(), "Use voucher success", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<VoucherDetail>> call, Throwable t) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        // đặt resultCode là Activity.RESULT_CANCELED thể hiện
        // đã thất bại khi người dùng click vào nút Back.
        // Khi này sẽ không trả về data.
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}