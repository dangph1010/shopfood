package com.example.duantotnghiep.UI.loginscreen;

import static com.android.volley.VolleyLog.TAG;
import static com.example.duantotnghiep.Constants.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.example.duantotnghiep.MainActivity;
import com.example.duantotnghiep.OnboardingActivity;
import com.example.duantotnghiep.R;
import com.example.duantotnghiep.UI.Fragment.OrderHistoryFragment;
import com.example.duantotnghiep.model.User;

public class SigninActivity extends AppCompatActivity {
//    private static ViewPager myViewPager;
    private static com.example.duantotnghiep.UI.loginscreen.NonSwipeableViewPager myViewPager;
    private static TextView toolBarTitle, tv_skip;
    ImageButton back_btn;
    Toolbar myToolbar;
//    static SharedPreferences sharedPreferences ;

    public static ViewPager getMyViewPager() {
        return myViewPager;
    }

    public static TextView getToolBarTitle() {return toolBarTitle;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        //ẩn tên app
//        setSupportActionBar(myToolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        sharedPreferences = getSharedPreferences(USER_INFOR, MODE_PRIVATE);
//        createSession(new User());
        setIdToViews();
        viewPagerSetup();


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments();
            }
        });

        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    public void viewPagerSetup(){
        SigninAdapter signinAdapter = new SigninAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        signinAdapter.addFragment(new SigninFragment_1());
        signinAdapter.addFragment(new SigninFragment_2());
        signinAdapter.addFragment(new OrderHistoryFragment());
        signinAdapter.addFragment(new SigninFragment_4());
        signinAdapter.addFragment(new SigninFragment_5());
        myViewPager.setAdapter(signinAdapter);
    }

    public void setIdToViews(){
        myToolbar = findViewById(R.id.myToolbar);
        back_btn = findViewById(R.id.back_btn);
        myViewPager = findViewById(R.id.myViewPager);
        toolBarTitle = findViewById(R.id.toolBarTitle);
        tv_skip = findViewById(R.id.tv_skip);
    }

    protected void addFragment(Fragment fragment) {

        FragmentManager fmgr = getSupportFragmentManager();

        FragmentTransaction ft = fmgr.beginTransaction();

        ft.add(R.id.myViewPager, fragment);

        ft.addToBackStack(fragment.getClass().getSimpleName());

        ft.commit();

    }
    //back button
    public void changeFragments(){
        int state = 1;
        switch (myViewPager.getCurrentItem()){
            case 0:
                state = 0;
                break;
            case 1:
                myViewPager.setCurrentItem(0, true);
                break;
            case 2:
                myViewPager.setCurrentItem(1,true);
                break;
            case 3:
                myViewPager.setCurrentItem(0, false);
                break;
            case 4:
                myViewPager.setCurrentItem(3, true);
                break;
        }
        if (state == 0){
            Intent intent = new Intent(SigninActivity.this, OnboardingActivity.class);
            startActivity(intent);
            finish();
        }
    }
//    //tạo session
//    public static void createSession(User user){
//        endSession();
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(USER_NAME, user.getuSERNAME());
//        editor.putString(EMAIL, user.geteMAIL());
//        editor.putString(FULLNAME, user.getfULLNAME());
//        editor.putString(PHONE, user.getpHONENUMBER());
//        editor.putString(LOCATION, user.getaDDRESS());
//        editor.commit();
//    }
//    //check session
//    public static User getUser(){
//        String username = sharedPreferences.getString(USER_NAME,"");
//        String email = sharedPreferences.getString(EMAIL, "");
//        String fullname = sharedPreferences.getString(FULLNAME, "");
//        String phone = sharedPreferences.getString(PHONE,"");
//        String location = sharedPreferences.getString(LOCATION,"");
//        User user = new User();
//        user.setuSERNAME(username);
//        user.seteMAIL(email);
//        user.setfULLNAME(fullname);
//        user.setpHONENUMBER(phone);
//        user.setaDDRESS(location);
////        Log.i(TAG, "checkSession: "+ username + email);
//        return user;
//    }
//    //delete sesion
//    public static void endSession(){
//        SharedPreferences.Editor editor = sharedPreferences.edit();
////        editor.remove(USER_NAME);
////        editor.remove(EMAIL);
//        editor.clear();
//        editor.commit();
////        editor.apply();
//    }
}