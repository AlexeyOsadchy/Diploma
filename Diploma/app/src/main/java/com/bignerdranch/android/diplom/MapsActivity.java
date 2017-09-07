package com.bignerdranch.android.diplom;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ArrayList<Integer> mIDList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mIDList = getIntent().getIntegerArrayListExtra("com.bignerdranch.android.diplom_IDlist");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(GPSController.mClient);
        mMap.setMyLocationEnabled(true);
        mMap.addCircle(new CircleOptions().center(new LatLng(location.getLatitude(), location.getLongitude())).radius(1000).strokeColor(Color.argb(90, 165, 0, 0)).fillColor(Color.argb(60, 114, 219, 255)).strokeWidth(5.0f));

        if(getIntent().getBooleanExtra("com.bignerdranch.android.diplom_onlypoint", false) == true){
            //LatLng latLng = OfferLab.get(this).getOffer(mIDList.get(0)).getCoordinates();
            double lat = OfferLab.get(this).getOffer(mIDList.get(0)).getLat();
            double lng = OfferLab.get(this).getOffer(mIDList.get(0)).getLng();
            LatLng latLng = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15.0f));
        }
        else{
            for (Integer a : mIDList) {
                Offer offer = OfferLab.get(this).getOffer(a);
                mMap.addMarker(new MarkerOptions().position( new LatLng(offer.getLat(), offer.getLng())).title(offer.getDescription()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(53.901409, 27.558917)));
                mMap.moveCamera(CameraUpdateFactory.zoomTo(10.5f));
            }
        }
    }
}
