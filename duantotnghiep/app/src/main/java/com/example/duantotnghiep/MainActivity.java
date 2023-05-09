package com.example.duantotnghiep;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.duantotnghiep.UI.Fragment.CartFragment;
import com.example.duantotnghiep.UI.Fragment.HomeFragment;
import com.example.duantotnghiep.UI.Fragment.MenuFragment;
import com.example.duantotnghiep.UI.Fragment.UserFragment;
import com.example.duantotnghiep.UI.loginscreen.SigninActivity;
import com.example.duantotnghiep.api.ApiService;
import com.example.duantotnghiep.api.Methods;
import com.example.duantotnghiep.api.NotificationService;
import com.example.duantotnghiep.model.Food;

import com.example.duantotnghiep.model.User;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static BottomNavigationView bottomNavigationView;
    static List<List<Food>> homeFoodArray = new ArrayList<>();
    Fragment manager;
    TextView title;
    int startingPosition;
    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });


    public static List<Food> getHomeFoods(int position) {
        return homeFoodArray.get(position);
    }

    public static void setHomeFoods(int position, List<Food> homeFoods) {
        MainActivity.homeFoodArray.set(position, homeFoods);
    }


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
        loadFragment(new HomeFragment(),1);

        //subcribe to topic
        notificateInit();
        checkGooglePlayService();

        for(int i=0 ; i<3; i++){
            homeFoodArray.add(null);
        }

        if (savedInstanceState == null){
            HomeFragment fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_main,fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkGooglePlayService();
    }

    private void checkGooglePlayService() {
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(MainActivity.this);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment selectfrg = null;
            int newPosition = 0;
            switch (item.getItemId()) {
                case R.id.home:
                    selectfrg = new HomeFragment();
                    newPosition = 1 ;
                    break;
                case R.id.menu:
                    selectfrg = new MenuFragment();
                    newPosition = 2 ;
                    break;
                case R.id.cart:
                    selectfrg = new CartFragment();
                    newPosition = 3 ;
                    break;
                case R.id.user:
                    selectfrg = new UserFragment();
                    newPosition = 4 ;
                    break;

            }
//            getSupportFragmentManager().beginTransaction().setCustomAnimations(
//                    .addToBackStack(null).replace(R.id.frame, selectfrg).commit();
            return loadFragment(selectfrg, newPosition);
        }
    };

    private boolean loadFragment(Fragment fragment, int newPosition) {
        if(fragment != null) {
            if(startingPosition > newPosition) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right );
                transaction.addToBackStack(null);
                transaction.replace(R.id.frame_main, fragment);
                transaction.commit();
            }
            if(startingPosition < newPosition) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                transaction.addToBackStack(null);
                transaction.replace(R.id.frame_main, fragment);
                transaction.commit();
            }
            startingPosition = newPosition;
            return true;
        }

        return false;
    }

    private void notificateInit(){
//        FirebaseMessaging.getInstance().subscribeToTopic(SigninActivity.getUser().getuSERNAME())
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        String msg = "Subscribed to "+SigninActivity.getUser().getuSERNAME();
//                        if (!task.isSuccessful()) {
//                            msg = "Subscribe failed";
//                        }
//                        Log.d(TAG, msg);
////                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        if(SplashScreen.getUser().getuSERNAME().equals("")==false){
                            sendToken(token);
                        }
                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, token);
//                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });


        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);
    }

    public void sendToken(String token) {
        Methods methods = ApiService.getRetrofit().create(Methods.class);
        TokenModel tokenModel = new TokenModel();
        tokenModel.setToken(token);
        tokenModel.setUsername(SplashScreen.getUser().getuSERNAME());
        Call<String> call = methods.sendToken(tokenModel);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    public class TokenModel {
        @SerializedName("TOKEN")
        String token;
        @SerializedName("USERNAME")
        String username;

        public String getToken() {
        return token;
        }

        public void setToken(String token) {
        this.token = token;
        }

        public String getUsername() {
        return username;
        }

        public void setUsername(String username) {
        this.username = username;
        }
    }
}