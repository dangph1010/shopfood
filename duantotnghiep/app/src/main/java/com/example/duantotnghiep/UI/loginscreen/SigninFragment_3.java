package com.example.duantotnghiep.UI.loginscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.duantotnghiep.R;

public class SigninFragment_3 extends Fragment {
    EditText edtEmailResent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getParentFragmentManager().setFragmentResultListener("emailFromFragment2", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                edtEmailResent.setText(result.getString("email"));
            }
        });
        return inflater.inflate(R.layout.fragment_signin_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setIdToViews();

    }

    @Override
    public void onResume() {
        super.onResume();
        SigninActivity.getToolBarTitle().setText("Reset Password!");
    }

    public void setIdToViews(){
        edtEmailResent = (EditText) getView().findViewById(R.id.edtEmailResent);
    }
}