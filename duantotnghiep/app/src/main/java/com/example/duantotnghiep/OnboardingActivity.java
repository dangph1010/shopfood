package com.example.duantotnghiep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duantotnghiep.UI.loginscreen.SigninActivity;

import me.relex.circleindicator.CircleIndicator;

public class OnboardingActivity extends AppCompatActivity {

   private ViewPager viewPager;
   private LinearLayout layout_bottom;
   private CircleIndicator circleIndicator;
   private LinearLayout layout_next;
   private ViewPagerAdapter viewPagerAdapter;
   private Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        initUI();
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        circleIndicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if (position == 2){
//                    tvSkip.setVisibility(View.GONE);
//                    layout_bottom.setVisibility(View.GONE);
//
//                }else {
//                    tvSkip.setVisibility(View.VISIBLE);
//                    layout_bottom.setVisibility(View.VISIBLE);
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnboardingActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initUI(){
        viewPager = findViewById(R.id.viewPager);
        layout_bottom = findViewById(R.id.layout_bottom);
        circleIndicator = findViewById(R.id.circleIndicator);
        layout_next = findViewById(R.id.layout_next);

        layout_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() < 2){
                    viewPager.setCurrentItem(viewPager.getCurrentItem() +1 );
                }
            }
        });
    }

}