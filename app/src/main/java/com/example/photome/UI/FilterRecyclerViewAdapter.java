package com.example.photome.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.photome.R;

import java.util.ArrayList;
import java.util.List;

public class FilterRecyclerViewAdapter extends RecyclerView.Adapter<FilterRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "FilterRecyclerViewAdapter";

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImgPaths = new ArrayList<>();
    private Context mContext;


    // data is passed into the constructor
    public FilterRecyclerViewAdapter(ArrayList<String> names, ArrayList<String> imgPaths, Context context) {
        mNames = names;
        mImgPaths = imgPaths;
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);

    }

    // Require: inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder called.");

        View view = mInflater.inflate(R.layout.filter_recycle_view, parent, false);
        return new ViewHolder(view);
    }

    // Require: binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder called.");

        holder.myTextView.setText(mNames.get(position));

        Glide.with(mContext).asBitmap().load(mImgPaths.get(position)).into(holder.myImageView);
    }

    // Require: total number of rows
    @Override
    public int getItemCount() {
        return mNames.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImageView;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.filter_image);
            myTextView = itemView.findViewById(R.id.filter_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mNames.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}