package com.bignerdranch.android.diplom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        v.findViewById(R.id.textView_food).setOnClickListener(categoryItemListener);
        v.findViewById(R.id.textView_beauty_and_health).setOnClickListener(categoryItemListener);
        v.findViewById(R.id.textView_cafe_and_restaurants).setOnClickListener(categoryItemListener);
        v.findViewById(R.id.textView_clothes).setOnClickListener(categoryItemListener);
        v.findViewById(R.id.textView_construction_materials).setOnClickListener(categoryItemListener);
        v.findViewById(R.id.textView_sport_and_rest).setOnClickListener(categoryItemListener);
        v.findViewById(R.id.textView_technique).setOnClickListener(categoryItemListener);
        v.findViewById(R.id.textView_parties_shows_concerts).setOnClickListener(categoryItemListener);

        return v;
    }

    View.OnClickListener categoryItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.textView_food:
                    click(1);
                    break;
                case R.id.textView_beauty_and_health:
                    click(2);
                    break;
                case R.id.textView_cafe_and_restaurants:
                    click(3);
                    break;
                case R.id.textView_clothes:
                    click(4);
                    break;
                case R.id.textView_construction_materials:
                    click(5);
                    break;
                case R.id.textView_sport_and_rest:
                    click(6);
                    break;
                case R.id.textView_technique:
                    click(7);
                    break;
                case R.id.textView_parties_shows_concerts:
                    click(8);
                    break;
            }
        }
    };


    private void click(int categoryID){
        Bundle args = new Bundle();
        args.putInt("Arg_Num_Function", 2);
        args.putInt("Arg_Num_Category", categoryID);
        OfferListFragment fragment = new OfferListFragment();
        fragment.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, fragment).addToBackStack(null).commit();
    }
}
