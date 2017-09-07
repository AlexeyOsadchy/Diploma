package com.bignerdranch.android.diplom;

import android.content.Context;

import java.util.ArrayList;

public class OfferLab {
    private ArrayList <Offer> mOfferList;
    private static OfferLab sOfferLab;
    private Context mAppContext;

    private OfferLab(Context appContext) {
        mAppContext = appContext;
        mOfferList = new ArrayList<Offer>();
    }

    public static OfferLab get(Context c) {
        if (sOfferLab == null) {
            sOfferLab = new OfferLab(c.getApplicationContext());
        }
        return sOfferLab;
    }

    public Offer getOffer(int id) {
        for (Offer a : mOfferList) {
            if (a.getID() == id)
                return a;
        }
        return null;
    }

    public ArrayList<Offer> getOfferList() {
        return mOfferList;
    }
}
