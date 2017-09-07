package com.bignerdranch.android.diplom;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class JSON extends AsyncTask<Void, Void, String> {

    public static String LOG_TAG = "my_log";
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJson = "";
    private String mURL;
    private Context mContext;
    private int mNum_function;

    public JSON(Context context, String URL, int num_function){
        mURL = URL;
        mContext = context;
        mNum_function = num_function;
    }

    @Override
    protected String doInBackground(Void... params) {
        // получаем данные с внешнего ресурса
        try {
            URL url = new URL(mURL);     //"http://diplombsuir.000webhostapp.com/friends.json"

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    @Override
    protected void onPostExecute(String strJson) {
        super.onPostExecute(strJson);
        // выводим целиком полученную json-строку
        Log.d(LOG_TAG, strJson);
        ArrayList <Offer> actionItemsList = OfferLab.get(mContext).getOfferList();
        JSONObject dataJsonObj = null;
        ArrayList<Integer>  idlist = new ArrayList<>();
        try {
            dataJsonObj = new JSONObject(strJson);
            JSONArray offers = dataJsonObj.getJSONArray("actions");

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Log.i("GSON", mURL);
            Log.i("GSON", strJson);


            for(int i = 0; i < offers.length(); i++){
                JSONObject offerJSON = offers.getJSONObject(i);

                Offer offer = gson.fromJson(offerJSON.toString(), Offer.class);
                Log.i("GSON", strJson);
                actionItemsList.add(offer);
//                actionItemsList.add(new Offer(
//                        offerJSON.getString("description"),
//                        offerJSON.getString("url"),
//                        offerJSON.getInt("id"),
//                        offerJSON.getString("discountRate"),
//                        offerJSON.getDouble("lat"),
//                        offerJSON.getDouble("lng")
//                        )
//
//                );
//                idlist.add(offerJSON.getInt("id"));

            }

            switch(mNum_function) {
                case 1:
                    OfferListFragment.updateActionItemList();

                    break;
                case 2:
                    Intent intent = new Intent(mContext, MapsActivity.class);
                    intent.putExtra("com.bignerdranch.android.diplom_IDlist", idlist);
                    intent.putExtra("com.bignerdranch.android.diplom_onlypoint", false);
                    mContext.startActivity(intent);
                    break;
                case 3:

                    break;
                default:

                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
