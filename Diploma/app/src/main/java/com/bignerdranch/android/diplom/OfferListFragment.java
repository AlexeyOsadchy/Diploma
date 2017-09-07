package com.bignerdranch.android.diplom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class OfferListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private static OfferAdapter mOfferAdapter;
    public static SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Offer> mOfferList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOfferList = OfferLab.get(getActivity()).getOfferList();
        mOfferList.clear();

        menu(getArguments().getInt("Arg_Num_Function"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_offerlist, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mOfferAdapter = new OfferAdapter();
        mRecyclerView.setAdapter(mOfferAdapter);

        return v;
    }

    @Override
    public void onRefresh() {
        mOfferList.clear();
        mSwipeRefreshLayout.setRefreshing(true);
        menu(getArguments().getInt("Arg_Num_Function"));
    }

    private class OfferHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextView;
        private TextView mTextViewDiscountRate;
        private int id;
        private ImageView mImageView;

        public OfferHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTextView = (TextView)itemView.findViewById(R.id.ActionItem_textView);
            mImageView = (ImageView)itemView.findViewById(R.id.ActionItem_imageView);
            mTextViewDiscountRate = (TextView)itemView.findViewById(R.id.ActionItem_textView_discountRate);
        }

        @Override
        public void onClick(View v) {
                        Bundle args = new Bundle();
            args.putInt("Arg_ActionItem_ID", id);
            OfferFragment fragment = new OfferFragment();
            fragment.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentMain, fragment).addToBackStack(null).commit();
            //MyApplication.getApi().getShop(OfferLab.get(getActivity()).getOffer(id).getShopId()).enqueue(MyApplication.getCallbackShop());
        }
    }
    private class OfferAdapter extends RecyclerView.Adapter<OfferHolder>{

        @Override
        public OfferHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_action_cardview, parent, false);
            return new OfferHolder(view);
        }

        @Override
        public void onBindViewHolder(OfferHolder holder, int position) {
            holder.mTextView.setText(mOfferList.get(position).getDescription());
            holder.mTextViewDiscountRate.setText(mOfferList.get(position).getDiscountRate());
            holder.id = mOfferList.get(position).getID();
            Picasso.with(getActivity())
                    .load(mOfferList.get(position).getURLPicture())
                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mOfferList.size();
        }
    }

    public static void updateActionItemList (){
        mOfferAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void menu (int item_menu){
        switch (item_menu) {
            case 1:
                MyApplication.getApi().getAllOffers().enqueue(MyApplication.getCallback());
                break;
            case 2:
                MyApplication.getApi().getCategory(getArguments().getInt("Arg_Num_Category")).enqueue(MyApplication.getCallback());
                break;
            case 3:
                MyApplication.getApi().getNear(getArguments().getDouble("Arg_Lat"), getArguments().getDouble("Arg_Lng")).enqueue(MyApplication.getCallback());
                break;
            case 4:
                Log.i("4l", "fdsg sdgds gsdg   " + getArguments().getInt("Arg_shopID"));
                MyApplication.getApi().getOfferShop(getArguments().getInt("Arg_shopID")).enqueue(MyApplication.getCallback());
                break;
        }
    }
}
