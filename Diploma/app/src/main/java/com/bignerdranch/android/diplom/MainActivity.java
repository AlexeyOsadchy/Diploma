package com.bignerdranch.android.diplom;

import android.content.Intent;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private GPSController mGPSController;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Лента");


        fab = (FloatingActionButton) findViewById(R.id.fab);


        Bundle args = new Bundle();
        args.putInt("Arg_Num_Function", 1);
        OfferListFragment fragment = new OfferListFragment();
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mGPSController = new GPSController(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            //new JSON(this, "http://diplomosad.000webhostapp.com/allOffers.php", 2).execute();

            MyApplication.getApi().getAllOffers().enqueue(MyApplication.getCallbackMap());

        } else if (id == R.id.nav_gallery) {
            toolbar.setTitle("Все акции");
            Bundle args = new Bundle();
            args.putInt("Arg_Num_Function", 1);

            OfferListFragment fragment = new OfferListFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, fragment).commit();
        } else if (id == R.id.nav_slideshow) {
            toolbar.setTitle("Ближайшие");
            mGPSController.checkPermission();
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGPSController.mClient);
            Bundle args = new Bundle();
            args.putInt("Arg_Num_Function", 3);
            args.putDouble("Arg_Lat", location.getLatitude());
            args.putDouble("Arg_Lng", location.getLongitude());

            OfferListFragment fragment = new OfferListFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, fragment).commit();

        } else if (id == R.id.nav_manage) {
            fab.hide();
            toolbar.setTitle("Категории");
            getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, new CategoryFragment()).commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            toolbar.setTitle("Регистрация");
            fab.hide();
            RegistrationFragment fragment = new RegistrationFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
