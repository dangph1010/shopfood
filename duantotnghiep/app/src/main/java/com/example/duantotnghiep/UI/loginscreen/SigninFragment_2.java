package com.example.duantotnghiep.UI.loginscreen;

import static com.android.volley.VolleyLog.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.duantotnghiep.R;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninFragment_2 extends Fragment {
    Button btnResetPassSend;
    EditText edtEmailReset;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //nhận data từ fragment 1;
        getParentFragmentManager().setFragmentResultListener("emailFromFragment1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                edtEmailReset.setText(result.getString("email"));
            }
        });

        return inflater.inflate(R.layout.fragment_signin_2, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setIdToViews();

        btnResetPassSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtEmailReset.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(), "Email is empty!", Toast.LENGTH_SHORT).show();
                }else{
                    sendRequest();
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        SigninActivity.getToolBarTitle().setText("Reset Password!");
    }

    public void setIdToViews(){
        edtEmailReset = (EditText) getView().findViewById(R.id.edtEmailReset);
        btnResetPassSend = (Button) getView().findViewById(R.id.btnResetPassSend);
    }

    public void sendRequest() {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        User user = new User();
        user.seteMAIL(edtEmailReset.getText().toString());
        Call<Object> call = methods.passwordRequest(user);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object message = response.body();
                String msg = message.toString().replace("{message=", "").replace("}","");
                Toast.makeText( getContext(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }
}