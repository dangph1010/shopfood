package com.example.duantotnghiep.UI.loginscreen;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.duantotnghiep.MainActivity;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.Fragment.HomeFragment;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.api.NotificationService;
import com.example.duantotnghiep.model.Food;
import com.example.duantotnghiep.model.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * @author: Phạm Quang Bình
 * @Date: 31/10/2022
 * @Name: Đăng Nhập
 */
public class SigninFragment_1 extends Fragment {
    TextView tvCreateAccount, tvForgotPassword;
    EditText edtEmailLogin, edtPasswordLogin;
    Button btnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setIdToViews();

        //dang ky tk
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SigninActivity.getMyViewPager().setCurrentItem(3,false);
            }
        });
        //Chuyển + truyền data từ fragment1 qua fragment 2;
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("email", edtEmailLogin.getText().toString());
                getParentFragmentManager().setFragmentResult("emailFromFragment1", bundle);
                SigninActivity.getMyViewPager().setCurrentItem(1,true);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidate()){
                    loginReq();
                }
//                if(!checkEmail(edtEmailLogin.getText().toString())){
//                    Toast.makeText(getActivity(), "Please check your email format", Toast.LENGTH_SHORT).show();
//                }else if(edtPasswordLogin.getText().toString().trim().equals("")){
//                    Toast.makeText(getActivity(), "Enter password", Toast.LENGTH_SHORT).show();
//                }else{
//                    loginReq();
//                }

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        SigninActivity.getToolBarTitle().setText("Login");
//        if(SplashScreen.getUser().getuSERNAME().trim().equals("") == false){
//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            startActivity(intent);
//        }
    }

    public void setIdToViews(){
        edtEmailLogin = (EditText) getView().findViewById(R.id.edtEmailLogin);
        edtPasswordLogin = (EditText) getView().findViewById(R.id.edtPasswordLogin);
        tvCreateAccount = (TextView) getView().findViewById(R.id.tvCreateAccount);
        tvForgotPassword = (TextView) getView().findViewById(R.id.tvForgotPassword);
        btnLogin = (Button) getView().findViewById(R.id.btnLogin);
    }

    public boolean checkValidate() {
        //email
        String email = edtEmailLogin.getText().toString().trim();
        if (email.isEmpty()) {
            edtEmailLogin.setError("Email is empty!");
            return false;
        }else if(emailValidator(email) == false){
            edtEmailLogin.setError("Wrong email format");
            return false;
        }

        //password
        String password = edtPasswordLogin.getText().toString().trim();
        if (password.isEmpty()) {
            edtPasswordLogin.setError("Password is empty!");
            return false;
        }
        return true;
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void loginReq() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        User userTemp = new User();
        userTemp.seteMAIL(edtEmailLogin.getText().toString());
        userTemp.setpASSWORD(edtPasswordLogin.getText().toString());
        Call<User> call = methods.postLogin(userTemp);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user.getuSERNAME().trim().equals("Wrong email or password")){
                    Toast.makeText(getContext(), user.getuSERNAME(), Toast.LENGTH_SHORT).show();
                }else if(user.getuSERNAME().trim().equals("banned")){
                    Toast.makeText(getContext(), "Your account was permanently banned!", Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(getContext(), "Welcome "+user.getfULLNAME(), Toast.LENGTH_SHORT).show();
                    SplashScreen.createSession(user);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("username",user.getuSERNAME());
                    startActivity(intent);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}