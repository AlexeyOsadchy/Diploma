package com.bignerdranch.android.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OfferFragment extends Fragment{
    private int id;
    private Offer mOffer;
    private TextView mTextView;
    private ImageView mImageView;
    private Button mapButton;
    private Button shopButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_offer, container, false);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar_offer);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapButton = (Button) v.findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                ArrayList<Integer>  o = new ArrayList<Integer>();
                o.add(id);
                intent.putExtra("com.bignerdranch.android.diplom_IDlist", o);
                intent.putExtra("com.bignerdranch.android.diplom_onlypoint", true);
                startActivity(intent);
                Bundle args = new Bundle();

            }
        });
        shopButton = (Button) v.findViewById(R.id.shopButton);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args  = new Bundle();
                args.putInt("Arg_id_shop", mOffer.getShopId());
                Fragment fragment = new ShopFragment();
                fragment.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentMain,fragment).addToBackStack(null).commit();

            }
        });


        id = (int)getArguments().getInt("Arg_ActionItem_ID");
        mOffer = OfferLab.get(getActivity()).getOffer(id);
        mTextView = (TextView)v.findViewById(R.id.activity_item_textDescription);
        mImageView =(ImageView)v.findViewById(R.id.activity_item_image);
        mTextView.setText(mOffer.getDescription());

        Picasso.with(getActivity())
                .load(mOffer.getURLPicture())
                .into(mImageView);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar_offer, menu);
    }
}
