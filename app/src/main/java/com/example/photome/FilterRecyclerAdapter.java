package com.example.photome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * https://www.youtube.com/watch?v=94rCjYxvzEE
 */
public class FilterRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "FilterRecyclerAdapter";

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mImgPath = new ArrayList<>();
    private Context mContext;


    public FilterRecyclerAdapter(ArrayList<String> name, ArrayList<String> imgPath, Context context){
        mName = name;
        mImgPath = imgPath;
        mContext = context;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mName.get(position);
        holder.myTextView.setText(animal);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mName.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mName.get(id);
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