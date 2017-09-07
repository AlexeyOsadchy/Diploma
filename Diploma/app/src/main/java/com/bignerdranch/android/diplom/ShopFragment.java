package com.bignerdranch.android.diplom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShopFragment extends Fragment {
    private Shop mShop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop, container, false);

        (v.findViewById(R.id.shopAllOffersButton)).setOnClickListener(v1 -> {
            Bundle args = new Bundle();
            args.putInt("Arg_Num_Function", 4);
            args.putInt("Arg_shopID", mShop.getId());
            OfferListFragment fragment = new OfferListFragment();
            fragment.setArguments(args);
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentMain, fragment)
                    .addToBackStack(null)
                    .commit();
        });


        (MyApplication.getApi().getShop(getArguments().getInt("Arg_id_shop")))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(shop -> {
                    mShop = shop;
                    ((TextView) v.findViewById(R.id.emailShopTextView)).setText(shop.getEmail());
                    ((TextView)v.findViewById(R.id.telephoneShopTextView)).setText(shop.getTelephone());
                    ((TextView) v.findViewById(R.id.descriptionShopTextView)).setText(shop.getDescription());
                    Picasso.with(getActivity()).load(shop.getURLpicture()).into((ImageView) v.findViewById(R.id.imageShop));
                });
        return v;
    }
}
