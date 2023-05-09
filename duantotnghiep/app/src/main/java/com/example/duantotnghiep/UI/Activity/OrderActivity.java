package com.example.duantotnghiep.UI.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duantotnghiep.MainActivity;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.Fragment.CartFragment2;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.adapter.OrderAdapter;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.Cart;
import com.example.duantotnghiep.model.Order;
import com.example.duantotnghiep.model.OrderDetail;
import com.example.duantotnghiep.model.RpPostItemCard;
import com.example.duantotnghiep.model.User;
import com.example.duantotnghiep.model.VoucherDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    User user = SplashScreen.getUser();
//    RecyclerView rcvOrder;
//    OrderAdapter orderAdapter;
//    List<OrderDetail> lstOrderDetail = new ArrayList<>();;
    ImageView imgBackOrder;
    Button btnOrder ;
    EditText etLocation, etPhone, etNote;
    TextView tvTotalPrice , btnLocalCard, tvAddVoucher, tvVoucherDetail, tvVoucher;
    ImageView ivDeleteVoucher;
//    int PriceTotalOrder = 0;
//    int TemOrder = 0;
    /* REQUEST_CODE là một giá trị int dùng để định danh mỗi request. Khi nhận được kết quả, hàm callback sẽ trả về cùng REQUEST_CODE này để ta có thể xác định và xử lý kết quả. */
    private static final int REQUEST_CODE_EXAMPLE = 0x100;

     String detail = "", voucherCode = "";
     
     int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        btnOrder = this.findViewById(R.id.btnOrder);
        etPhone = this.findViewById(R.id.etPhoneOrder);
        etLocation = this.findViewById(R.id.etLocationOrder);
        etNote = this.findViewById(R.id.etNoteOrder);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnLocalCard = findViewById(R.id.btnLocalCard);
        tvAddVoucher = findViewById(R.id.tvAddVoucher);
        tvVoucherDetail = findViewById(R.id.tvVoucherDetail);
        tvVoucher = findViewById(R.id.tvVoucher);

        ivDeleteVoucher = findViewById(R.id.ivDeleteVoucher);

        imgBackOrder = findViewById(R.id.imgBackOrder);


        etPhone.setText(user.getpHONENUMBER());
        etLocation.setText(user.getaDDRESS());

        imgBackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getOrderApi();

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postOrderApi();
                updateVoucher();
                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
//                OrderActivity.this.finish();
            }
        });

        btnLocalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, PaymentMethodActivity.class);
                startActivity(intent);
            }
        });

        tvAddVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final   Intent intent = new Intent(OrderActivity.this, VoucherActivity.class);

                // Start DetailActivity với request code vừa được khai báo trước đó
                startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
            }
        });

        ivDeleteVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvVoucherDetail.setText("");
                detail = "";
                voucherCode ="";
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Kiểm tra requestCode có trùng với REQUEST_CODE vừa dùng
        if (requestCode == REQUEST_CODE_EXAMPLE) {

            // resultCode được set bởi DetailActivity
            // RESULT_OK chỉ ra rằng kết quả này đã thành công
            if (resultCode == Activity.RESULT_OK) {
                // Nhận dữ liệu từ Intent trả về
                detail = data.getStringExtra(VoucherActivity.EXTRA_DATA);
                voucherCode = data.getStringExtra(VoucherActivity.CODE_DATA);
                quantity = data.getIntExtra(VoucherActivity.QUANTITY_DATA, -1);
                Log.d("SSSSSSSSS", detail.toString());

                if(quantity < 1) {
                    Toast.makeText(this, "Voucher is over", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    tvVoucherDetail.setText(detail.trim());
                }
            } else {
                // DetailActivity không thành công, không có data trả về.
            }
        }
    }

    public void updateVoucher() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        VoucherDetail update = new VoucherDetail();
        update.setvOUCHER(detail);
        update.setqUANTITY(quantity - 1);
        Call<RpPostItemCard> call = methods.updatevoucher(update);
        call.enqueue(new Callback<RpPostItemCard>() {
            @Override
            public void onResponse(Call<RpPostItemCard> call, Response<RpPostItemCard> response) {

            }

            @Override
            public void onFailure(Call<RpPostItemCard> call, Throwable t) {

            }
        });
    }

    private void postOrderApi() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Order order = new Order();
        order.setUsername(SplashScreen.getUser().getuSERNAME().toString());
        order.setvOUCHERCODE(voucherCode);
        order.setPhonenumber(etPhone.getText().toString());
        order.setAddress(etLocation.getText().toString());
        order.setVoucherdetail(detail.trim());
        Call<List<Order>> call = methods.postOrder(order);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                Toast.makeText(getApplicationContext(), "Your order has been complete", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                finish();
            }
        });
    }

    private void getOrderApi() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        String userName = user.getuSERNAME();
        User temp = new User();
        temp.setuSERNAME(userName);
        Call<List<Cart>> call = methods.getdatacart(temp);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                List<Cart> data = response.body();
                int Totalprice = 0;
                for (Cart dt : data
                ) {
                    Totalprice += dt.getqUANTITY()*dt.getpRICE();
                }
                tvTotalPrice.setText(String.valueOf(Totalprice));
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });
    }
}