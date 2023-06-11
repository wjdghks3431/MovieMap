package com.example.moviemap;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

public class MapActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    private StreetViewPanoramaView streetViewPanoramaView;
    private StreetViewPanorama streetViewPanorama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);

        streetViewPanoramaView = findViewById(R.id.streetviewpanorama);
        streetViewPanoramaView.onCreate(savedInstanceState);
        streetViewPanoramaView.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
        streetViewPanorama = panorama;
        LatLng location = new LatLng(37.5779, 126.9763);
        streetViewPanorama.setPosition(location);

        // Keeping the zoom and tilt. Animate bearing by 60 degrees in 1000 milliseconds.
        long duration = 1000;
        StreetViewPanoramaCamera camera =
                new StreetViewPanoramaCamera.Builder()
                        .zoom(streetViewPanorama.getPanoramaCamera().zoom)
                        .tilt(streetViewPanorama.getPanoramaCamera().tilt )
                        .bearing(streetViewPanorama.getPanoramaCamera().bearing - 100)
                        .build();
        streetViewPanorama.animateTo(camera, duration);
    }


    @Override
    protected void onResume() {
        super.onResume();
        streetViewPanoramaView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        streetViewPanoramaView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        streetViewPanoramaView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        streetViewPanoramaView.onLowMemory();
    }
}

