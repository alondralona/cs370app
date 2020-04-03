package com.sp20.ssu370.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class GoogleMap extends FragmentActivity implements OnMapReadyCallback {
    private com.google.android.gms.maps.GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Lodi, California, and move the camera.
        LatLng vinewoodDogPark = new LatLng(38.123874, -121.295720);
         mMap.addMarker(new MarkerOptions().position(vinewoodDogPark).title("Marker in Lodi Dog Park"));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(vinewoodDogPark));
    }
}
