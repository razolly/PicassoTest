package com.bignerdranch.android.picassotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Hotel> mHotelData = new ArrayList<Hotel>();

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Create new LayoutManager and attach to RecyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        httpRequest(); // Gets JSON data from a URL

        // Create new Adapter and attach to RecyclerView
        mAdapter = new MyAdapter(mHotelData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        httpRequest(); // Just a test to demonstrate that the List<Hotel> has to be cleared or else data will multiply
    }

    // Note: this code was copied from: https://github.com/codepath/android_guides/wiki/Using-Android-Async-Http-Client
    //       Don't put this code in an Adapter, because List<Hotel> should be populated first before being passed to the Adapter
    public void httpRequest(){
        String url = "http://raz-test.herokuapp.com/api/v1/images/get";
        AsyncHttpClient client = new AsyncHttpClient(); // Rz - This opens a new thread that runs in the background
        client.get(url, new JsonHttpResponseHandler() { // Rz - Handles parsing the JSON data from the URL
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    mHotelData.clear(); // Rz. So that array does not keep on accumulating new Hotel Objects
                    for(int i = 0; i < dataArray.length(); i++) {
//                        JSONObject data = dataArray.getJSONObject(i);
//                        String title = data.getString("title");
//                        String url = data.getString("image_url");
                        String title = dataArray.getJSONObject(i).getString("title");
                        String url = dataArray.getJSONObject(i).getString("image_url");
                        mHotelData.add(new Hotel(url, title));
                    }
                    //
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, response.toString());
                // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                // Handle resulting parsed JSON response here
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
        });
    }
}
