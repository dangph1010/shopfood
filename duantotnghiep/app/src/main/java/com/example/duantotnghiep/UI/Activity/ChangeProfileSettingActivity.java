package com.example.duantotnghiep.UI.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeProfileSettingActivity extends AppCompatActivity {
    Button btnChangeProfile;
    EditText edtFullnameProfile, edtEmailProfile, edtPhoneProfile, edtAddressProfile, edtBirthdayProfile;
    ImageView imgBack;
    static User tempUser = new User();
    static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        username =  SplashScreen.getUser().getuSERNAME();

        setIdToViews();
        if(username.equals("") == false){
            getUserProfile();
        }else{
            Toast.makeText(ChangeProfileSettingActivity.this, "Please login to view your profile", Toast.LENGTH_LONG).show();
            btnChangeProfile.setEnabled(false);
        }



        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChangeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkTempUser(tempUser)){
                    postUserProfile();
                    finish();
                }else{
                    Toast.makeText(ChangeProfileSettingActivity.this, "Ban chua thay doi thong tin", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //cập nhật thông tin user
    public void postUserProfile() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        User userInfor = new User();
        userInfor.setuSERNAME(username);
        userInfor.setfULLNAME(edtFullnameProfile.getText().toString());
        userInfor.seteMAIL(edtEmailProfile.getText().toString());
        userInfor.setpHONENUMBER(edtPhoneProfile.getText().toString());
        userInfor.setaDDRESS(edtAddressProfile.getText().toString());
        userInfor.setdATEOFBIRTH(edtBirthdayProfile.getText().toString());
        Call<User> call = methods.updateinfor(userInfor);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }

        });
    }
    //bắt lỗi
    public Boolean checkTempUser(User userTemp){
        if(edtFullnameProfile.getText().toString().trim().equals(userTemp.getfULLNAME()) == false){
            return true;
        }else if(edtEmailProfile.getText().toString().trim().equals(userTemp.geteMAIL()) == false){
            return true;
        }else if(edtPhoneProfile.getText().toString().trim().equals(userTemp.getpHONENUMBER()) == false){
            return true;
        }else if(edtAddressProfile.getText().toString().trim().equals(userTemp.getaDDRESS()) == false){
            return true;
        }else if(edtBirthdayProfile.getText().toString().trim().equals(userTemp.getdATEOFBIRTH()) == false){
            return true;
        }else{
            return false;
        }
    }
    //lấy thông tin user hiện tại
    public void getUserProfile(){
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        Call<User> call = methods.getUserInfor(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User data = response.body();
                edtFullnameProfile.setText(data.getfULLNAME());
                edtEmailProfile.setText(data.geteMAIL());
                edtPhoneProfile.setText(data.getpHONENUMBER());
                edtAddressProfile.setText(data.getaDDRESS());
                edtBirthdayProfile.setText(data.getdATEOFBIRTH());
                tempUser = data;
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    public void setIdToViews(){
        edtFullnameProfile = findViewById(R.id.edtFullnameProfile);
        edtEmailProfile = findViewById(R.id.edtEmailProfile);
        edtPhoneProfile = findViewById(R.id.edtPhoneProfile);
        btnChangeProfile = findViewById(R.id.btnChangeProfile);
        edtAddressProfile = findViewById(R.id.edtAddressProfile);
        edtBirthdayProfile = findViewById(R.id.edtBirthdayProfile);
        imgBack = findViewById(R.id.imgBack);
    }
}