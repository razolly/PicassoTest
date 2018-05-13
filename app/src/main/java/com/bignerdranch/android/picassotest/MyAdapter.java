package com.bignerdranch.android.picassotest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Hotel> mHotelDataSet;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Rz - Can think of ViewHolder as a mini-activity with its own views

        public ImageView mImageView;
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mImageView = v.findViewById(R.id.picasso_image);
            mTextView = v.findViewById(R.id.picasso_textView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Hotel> HotelDataSet) {
        mHotelDataSet = HotelDataSet;
    }

    // Create new views holders (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_recycler, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        // Rz - Change image by changing URL string
        Picasso.get()
                .load(mHotelDataSet.get(position).getImageURL())
                .into(holder.mImageView);

        // Rz - Change text
        holder.mTextView.setText(mHotelDataSet.get(position).getHotelDesc());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mHotelDataSet.size();
    }

}
