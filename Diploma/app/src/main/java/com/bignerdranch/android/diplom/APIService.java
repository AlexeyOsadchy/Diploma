package com.bignerdranch.android.diplom;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Алексей on 05.06.2017.
 */

public interface APIService {
    @GET("allOffers.php")
    Call <ArrayList<Offer>> getAllOffers();
    @GET("getCat.php")
    Call <ArrayList<Offer>> getCategory(@Query("categoryID") int id);
    @GET("near.php")
    Call <ArrayList<Offer>> getNear(@Query("lat") double lat, @Query("lng") double lng);
    @GET("getShop.php")
    Observable<Shop> getShop(@Query("shopID") int id);
    @GET("getOfferShop.php")
    Call <ArrayList<Offer>> getOfferShop(@Query("shopID") int id);
}
