package com.bignerdranch.android.diplom;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

public class Offer {
    @SerializedName("description")
    private String mDescription;
    @SerializedName("url")
    private String mURLPicture;
    @SerializedName("id")
    private int mId;
    @SerializedName("discountRate")
    private String mDiscountRate;
    @SerializedName("lat")
    private double mLat;
    @SerializedName("lng")
    private double mLng;
    @SerializedName("id_shop")
    private int mShopId;

    public Offer(String description, String URLPicture, int ID, String discountRate, double lat, double lng){
        mDescription = description;
        mURLPicture = URLPicture;
        mId = ID;
        mDiscountRate = discountRate;
        //mCoordinates = new LatLng(lat, lng);
        mLat = lat;
        mLng = lng;
    }

    public int getID() {
        return mId;
    }

    public void setID(int ID) {
        mId = ID;
    }

    public String getURLPicture() {
        return mURLPicture;
    }

    public void setURLPicture(String URLPicture) {
        mURLPicture = URLPicture;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDiscountRate() {
        return mDiscountRate;
    }

    public void setDiscountRate(String discountRate) {
        mDiscountRate = discountRate;
    }

//    public LatLng getCoordinates() {
//        return mCoordinates;
//    }

//    public void setCoordinates(LatLng coordinates) {
//        mCoordinates = coordinates;
//    }

    @Override
    public String toString() {
        return "ID: " + mId +
                " Description: " + mDescription +
                //" URL: " + mURLPicture +
                " DiscountRate: " + mDiscountRate +
                " id_shop: " + mShopId;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    public int getShopId() {
        return mShopId;
    }

    public void setShopId(int shopId) {
        mShopId = shopId;
    }
}
