package com.bignerdranch.android.diplom;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Алексей on 05.06.2017.
 */

public class MyApplication extends Application {
    public static APIService sService;
    private static Callback sCallback;
    private static Callback sCallbackMap;
    private ArrayList <Offer> offerList;

    public static Callback getCallbackMap() {
        return sCallbackMap;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        offerList = OfferLab.get(this).getOfferList();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://diplomosad.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        sService = retrofit.create(APIService.class);

        sCallback = new Callback<ArrayList<Offer>>() {
            @Override
            public void onResponse(Call<ArrayList<Offer>> call, Response<ArrayList<Offer>> response) {
                Log.i("GSON", "ok");
                for(int i = 0; i < response.body().size(); i++){
                    offerList.add(response.body().get(i));
                    Log.i("GSON", response.body().get(i).toString());
                }
                OfferListFragment.updateActionItemList();
            }

            @Override
            public void onFailure(Call<ArrayList<Offer>> call, Throwable t) {
                Log.i("GSON", t.toString());
                OfferListFragment.mSwipeRefreshLayout.setRefreshing(false);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Сервер не отвечает",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        };

        sCallbackMap = new Callback <ArrayList<Offer>> (){
            @Override
            public void onResponse(Call<ArrayList<Offer>> call, Response<ArrayList<Offer>> response) {
                ArrayList<Integer>  idlist = new ArrayList<>();
                for(int i = 0; i < response.body().size(); i++){
                    offerList.add(response.body().get(i));
                    idlist.add(response.body().get(i).getID());
                    //Log.i("GSON", response.body().get(i).toString());
                }
                Intent intent = new Intent(MyApplication.this, MapsActivity.class);
                intent.putExtra("com.bignerdranch.android.diplom_IDlist", idlist);
                intent.putExtra("com.bignerdranch.android.diplom_onlypoint", false);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call <ArrayList<Offer>> call, Throwable t) {
                Log.i("GSON", "fail");
            }
        };


    }

    public static APIService getApi() {
        return sService;
    }

    public static Callback getCallback() {
        return sCallback;
    }

}
