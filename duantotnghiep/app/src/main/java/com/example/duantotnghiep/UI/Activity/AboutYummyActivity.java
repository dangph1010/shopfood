package com.example.duantotnghiep.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.duantotnghiep.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AboutYummyActivity extends AppCompatActivity {
    ImageView imgBack;
    GoogleMap maps;
    public static final LatLng FOOD_POSITION = new LatLng(10.775037157805304, 106.70117084493938);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_yummy);
        imgBack = this.findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);

        smf.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                maps = googleMap;
                setLocation(FOOD_POSITION);
            }
        });
    }

    public void setLocation(LatLng latLng){
        maps.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        maps.addMarker(markerOptions);
    }
}