package com.example.duantotnghiep;

import static com.example.duantotnghiep.Constants.EMAIL;
import static com.example.duantotnghiep.Constants.FULLNAME;
import static com.example.duantotnghiep.Constants.LOCATION;
import static com.example.duantotnghiep.Constants.PHONE;
import static com.example.duantotnghiep.Constants.USER_INFOR;
import static com.example.duantotnghiep.Constants.USER_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.example.duantotnghiep.model.User;

public class SplashScreen extends AppCompatActivity {
    static SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences = getSharedPreferences(USER_INFOR, MODE_PRIVATE);

        //tạo channel:
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification channel";
            String description = "Order notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notichannel", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getUser().getuSERNAME().equals("") == false){
                    startActivity( new Intent(SplashScreen.this, MainActivity.class));
                }else{
                    startActivity( new Intent(SplashScreen.this, OnboardingActivity.class));
                }
            }
        }, 1500);
    }



    //tạo session
    public static void createSession(User user){
//        endSession();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, user.getuSERNAME());
        editor.putString(EMAIL, user.geteMAIL());
        editor.putString(FULLNAME, user.getfULLNAME());
        editor.putString(PHONE, user.getpHONENUMBER());
        editor.putString(LOCATION, user.getaDDRESS());
        editor.commit();
    }
    //check session
    public static User getUser(){
        String username = sharedPreferences.getString(USER_NAME,"");
        String email = sharedPreferences.getString(EMAIL, "");
        String fullname = sharedPreferences.getString(FULLNAME, "");
        String phone = sharedPreferences.getString(PHONE,"");
        String location = sharedPreferences.getString(LOCATION,"");
        User user = new User();
        user.setuSERNAME(username);
        user.seteMAIL(email);
        user.setfULLNAME(fullname);
        user.setpHONENUMBER(phone);
        user.setaDDRESS(location);
//        Log.i(TAG, "checkSession: "+ username + email);
        return user;
    }
    //delete sesion
    public static void endSession(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove(USER_NAME);
//        editor.remove(EMAIL);
        editor.clear();
        editor.commit();
//        editor.apply();
    }

}