package com.example.duantotnghiep.UI.Activity;

import static com.android.volley.VolleyLog.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
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
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordProfileActivity extends AppCompatActivity {
    EditText edtOldPassword, edtNewPassword, edtConfirmPassword;
    Button btnChangePassword ;
    ImageView imgBack;
    String oldPassword, newPassword, confirmPassword;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_settings);

        imgBack = findViewById(R.id.imgBack);
        edtOldPassword = findViewById(R.id.edtOldPassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        oldPassword = edtOldPassword.getText().toString();
        newPassword = edtNewPassword.getText().toString();
        confirmPassword = edtConfirmPassword.getText().toString();

        user = SplashScreen.getUser();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtOldPassword.getText().toString().trim().equals("") || edtNewPassword.getText().toString().trim().equals("") || edtConfirmPassword.getText().toString().trim().equals("")){
                    Toast.makeText(ChangePasswordProfileActivity.this, "vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT);
                    Log.i(TAG, "onClick: pls check information");
                }else{
                    PostUserPassword();
                }
            }
        });

        edtOldPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() >= (edtOldPassword.getRight()
                            - edtOldPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){

                        if(edtOldPassword.getInputType() == 128){
                            edtOldPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        }else if(edtOldPassword.getInputType() == 1){
                            edtOldPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }

                        return true;
                    }
                }
                return false;
            }
        });

    }

    public void showHidePassword(){
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;
    }

    public void PostUserPassword(){
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        PasswordChanger changer = new PasswordChanger();
        changer.setUsername(user.getuSERNAME());
        changer.setOldPassword(edtOldPassword.getText().toString());
        changer.setNewPassword(edtNewPassword.getText().toString());
        Call<PasswordChanger> call = methods.updatepassword(changer);
        call.enqueue(new Callback<PasswordChanger>() {
            @Override
            public void onResponse(Call<PasswordChanger> call, Response<PasswordChanger> response) {

            }

            @Override
            public void onFailure(Call<PasswordChanger> call, Throwable t) {

            }

        });

    }

    public class PasswordChanger {
        @SerializedName("USERNAME")
        public String username;
        @SerializedName("OLDPASSWORD")
        public String oldPassword;
        @SerializedName("NEWPASSWORD")
        public String newPassword;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
}