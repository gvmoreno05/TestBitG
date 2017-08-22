package com.vmoreno.bapp.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vmoreno.bapp.utils.BApplication;
import com.vmoreno.bapp.R;
import com.vmoreno.bapp.client.retrofit.RetrofitServiceApi;
import com.vmoreno.bapp.domain.Interactor;
import com.vmoreno.bapp.model.Address;
import com.vmoreno.bapp.model.Geo;
import com.vmoreno.bapp.model.User;
import com.vmoreno.bapp.presenter.GPSPresenter;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GpsView {

    private GoogleMap mMap;
    private GPSPresenter gpsPresenter;
    private UiSettings mUiSettings;
    private RetrofitServiceApi retrofitServiceApi;
    private Interactor interactor;
    private Bundle bundle;
    private User userObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        retrofitServiceApi = ((BApplication) getApplicationContext()).getRetrofitApi();
        interactor = ((BApplication) getApplicationContext()).getUserModel(retrofitServiceApi);
        gpsPresenter = ((BApplication) getApplicationContext()).getGpsPresenter(interactor, this);
        userObj = new User();
        bundle = getIntent().getExtras();
        if (gpsPresenter == null) {
            gpsPresenter = ((BApplication) getApplicationContext()).getGpsPresenter(interactor, this);
            gpsPresenter.getGpsLatLong();
        } else {

                userObj.setName(bundle.getString("name"));
                Address address = new Address();
                address.setCity(bundle.getString("city"));
                address.setStreet(bundle.getString("street"));
                address.setSuite(bundle.getString("suite"));
                Geo geo = new Geo();
                geo.setLat(bundle.getString("latitude"));
                geo.setLng(bundle.getString("longitude"));
                address.setGeo(geo);
                userObj.setAddress(address);
                showMarkerUser();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);
        enableMyLocation();
        showMarkerUser();
    }

    @Override
    public void returnGPSResult(User user) {
        userObj = user;
    }

    @Override
    public void failureGps(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showMarkerUser() {
        if (userObj != null) {
            Double latUser = Double.parseDouble(userObj.getAddress().getGeo().getLat());
            Double lngUser = Double.parseDouble(userObj.getAddress().getGeo().getLng());
            LatLng userLocation = new LatLng(latUser, lngUser);
            mMap.addMarker(new MarkerOptions()
                    .position(userLocation)
                    .title(userObj.getName())
                    .snippet(userObj.getAddress().getStreet() + " " + userObj.getAddress().getSuite()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 14));
        }
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mMap.clear();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }
}
