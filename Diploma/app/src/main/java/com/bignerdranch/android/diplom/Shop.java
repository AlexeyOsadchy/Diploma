package com.bignerdranch.android.diplom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Алексей on 08.06.2017.
 */

public class Shop {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("telephone")
    private String mTelephone;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("url")
    private String mURLpicture;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getTelephone() {
        return mTelephone;
    }

    public void setTelephone(String telephone) {
        mTelephone = telephone;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getURLpicture() {
        return mURLpicture;
    }

    @Override
    public String toString() {
        return "ID: " + mId +
                " Description: " + mDescription +
                " Name: " + mName
           ;
    }
}
