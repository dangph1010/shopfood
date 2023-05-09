package com.example.duantotnghiep.UI.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duantotnghiep.OnboardingActivity;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.SplashScreen;
import com.example.duantotnghiep.UI.Activity.AboutYummyActivity;
import com.example.duantotnghiep.UI.Activity.AccountSettingActivity;
import com.example.duantotnghiep.UI.Activity.FavouriteActivity;
import com.example.duantotnghiep.UI.Activity.FeedBackAndSupportActivity;
import com.example.duantotnghiep.UI.Activity.FrequentlyAskedQuestionsActivity;
import com.example.duantotnghiep.UI.Activity.RateUSDialog;
import com.example.duantotnghiep.UI.Activity.TermsAndConditionsActivity;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.adapter.SliderAdapter;
import com.example.duantotnghiep.model.SliderItem;
import com.example.duantotnghiep.model.User;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends Fragment {
    User user = SplashScreen.getUser();

    TextView txtUpdateInfo, tvNameUser;
    LinearLayout lnExit, lnAbout, lnQuestion,lnTermsAndConditions, lnFeedBack, lnRateUs , lnFavourite;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtUpdateInfo = view.findViewById(R.id.txtUpdateInfo);
        lnExit = view.findViewById(R.id.lnExit);
        lnAbout = view.findViewById(R.id.lnAbout);
        lnQuestion = view.findViewById(R.id.lnQuestion);
        lnTermsAndConditions = view.findViewById(R.id.lnTermsAndConditions);
        lnFeedBack = view.findViewById(R.id.lnFeedBack);
        lnRateUs = view.findViewById(R.id.lnRateUs);
        lnFavourite = view.findViewById(R.id.lnFavourite);
        tvNameUser = view.findViewById(R.id.tvNameUser);
        tvNameUser.setText(user.getfULLNAME());




        txtUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AccountSettingActivity.class);
                startActivity(intent);
            }
        });

        lnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutYummyActivity.class);
                startActivity(intent);
            }
        });

        lnQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FrequentlyAskedQuestionsActivity.class);
                startActivity(intent);
            }
        });

        lnTermsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TermsAndConditionsActivity.class);
                startActivity(intent);
            }
        });

        lnFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedBackAndSupportActivity.class);
                startActivity(intent);
            }
        });

        lnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavouriteActivity.class);
                startActivity(intent);
            }
        });


        lnRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateUSDialog rateUSDialog = new RateUSDialog(getContext());
                rateUSDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
                rateUSDialog.setCancelable(false);
                rateUSDialog.show();
            }
        });


        lnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                        SplashScreen.endSession();
//                        FirebaseMessaging.getInstance().unsubscribeFromTopic(SigninActivity.getUser().getuSERNAME());
                        getActivity().finishAffinity();
                        Intent intent = new Intent(getContext(), SigninActivity.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }

}