package com.bignerdranch.android.diplom;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class GPSController implements LocationListener {
    public static GoogleApiClient mClient;
    private Context mContext;
    private static final String TAG = "LocatrFragment";
    private Location mCurrentLocation;
    private List<Offer> mActionItemsList;

    public GPSController(Context context) {
        mContext = context;
        mClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        Log.i("LocatrAPK", "trueeeeeeee conconnect");
                        findImage();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                }).build();
        mClient.connect();
    }

    private void findImage() {

        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setInterval(60000);
        //request.setSmallestDisplacement(1);
        checkPermission();
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, request, this);
    }

    int a = 0;

    @Override
    public void onLocationChanged(Location location) {

        Log.i(TAG, "LocationA: " + location);


        //String url = "http://diplomosad.000webhostapp.com/near.php/?lat=" + location.getLatitude() + "&lng=" + location.getLongitude();
        //new JSON(mContext, url).execute();
        //System.out.println("LocationA " + url);
        //LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mCurrentLocation = location;
        //marker.setPosition(myLocation);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        a++;

        //if(a == 6)LocationServices.FusedLocationApi.removeLocationUpdates(mClient, this);

    }

    public LatLng getLatLng() {
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setNumUpdates(1);
        request.setInterval(0);

        checkPermission();
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, request, this);
        LatLng latLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        return latLng;
    }

    public void checkPermission(){
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {}
    }
}
