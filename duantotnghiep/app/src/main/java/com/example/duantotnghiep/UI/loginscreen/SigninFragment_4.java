package com.example.duantotnghiep.UI.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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

import com.example.duantotnghiep.MainActivity;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.UI.Fragment.HomeFragment;
import com.example.duantotnghiep.UI.Fragment.MenuFragment;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: Phạm Quang Bình
 * @Date: 31/10/2022
 * @Name: Đăng kí
 */
public class SigninFragment_4 extends Fragment {
    TextView tvAlreadySignup;
    EditText etUserNameRegister, etFullNameRegister, etEmailRegister, etPasswordRegister, etConfirmPassRegister;
    Button btnSignUp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin_4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Add View
        setIdToViews();

        tvAlreadySignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SigninActivity.getMyViewPager().setCurrentItem(0,false);
            }
        });

        //Register Button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Post api to database
                if (checkValidate()){
                    postUser();
                }
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        SigninActivity.getToolBarTitle().setText("Create account");
    }

    public void setIdToViews(){
        tvAlreadySignup = (TextView) getView().findViewById(R.id.tvAlreadySignup);
        etUserNameRegister = (EditText) getView().findViewById(R.id.etUserNameRegister);
        etFullNameRegister = (EditText) getView().findViewById(R.id.etFullNameRegister);
        etEmailRegister = (EditText) getView().findViewById(R.id.etEmailRegister);
        etPasswordRegister = (EditText) getView().findViewById(R.id.etPasswordRegister);
        etConfirmPassRegister = (EditText) getView().findViewById(R.id.etConfirmPassRegister);
        btnSignUp = (Button) getView().findViewById(R.id.btnSignUp);
    }

    public boolean checkValidate() {
        //username
        String username = etUserNameRegister.getText().toString().trim();
//        Pattern pattern = Pattern.compile("\s");
//        Matcher matcher = pattern.matcher(username);
//        boolean found = matcher.find();
        if (username.isEmpty()) {
            etUserNameRegister.setError("Username is empty!");
            return false;
        }
//        else if (found){}

        //fullname
        String fullname = etFullNameRegister.getText().toString().trim();
        if (fullname.isEmpty()) {
            etFullNameRegister.setError("Fullname is empty!");
            return false;
        }

        //email
        String email = etEmailRegister.getText().toString().trim();
        if (email.isEmpty()) {
            etEmailRegister.setError("Email is empty!");
            return false;
        }else if(emailValidator(email) == false){
            etEmailRegister.setError("Wrong email format");
            return false;
        }

        //password
        String password = etPasswordRegister.getText().toString().trim();
        String repassword = etConfirmPassRegister.getText().toString().trim();
        if (password.isEmpty() && repassword.isEmpty()) {
            etPasswordRegister.setError("Password is empty!");
            etConfirmPassRegister.setError("Confirm Password is empty!");
            return false;
        }else if(!password.equals(repassword)){
            etConfirmPassRegister.setError("Password doesn't match!");
            return false;
        }
        return true;
    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void postUser() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        User user1 = new User();
        user1.setuSERNAME(etUserNameRegister.getText().toString());
        user1.setfULLNAME(etFullNameRegister.getText().toString());
        user1.setpASSWORD(etPasswordRegister.getText().toString());
        user1.seteMAIL(etEmailRegister.getText().toString());
        user1.setrOLE(1);
        Call<User> call = methods.postUser(user1);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();

                if (user.getuSERNAME().trim().equals("Username existed")){
                    Toast.makeText(getContext(), user.getuSERNAME(), Toast.LENGTH_SHORT).show();
                }else if (user.getuSERNAME().trim().equals("Email existed")){
                    Toast.makeText(getContext(), user.getuSERNAME(), Toast.LENGTH_SHORT).show();
                }else if (user.getuSERNAME().trim().equals("Sign Up Successful")){
                    Intent intent = new Intent(getActivity(), SigninActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }

        });
    }

}