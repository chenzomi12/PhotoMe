package com.example.photome.gallery.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.photome.gallery.loader.GlideImageLoader;
import com.example.photome.gallery.info.PhotoInfo;
import com.example.photome.R;
import com.example.photome.Utils.ScreenUtils;

import java.util.List;

/**
 * update by zomi. 2019.5.4: Fix bug final position onClick.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private final static String TAG = PhotoAdapter.class.getSimpleName();

    private Context mContext;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<PhotoInfo> photoInfoList;
    private GlideImageLoader mImageLoader;

    private OnClickListener onClickListener;

    public PhotoAdapter(Context context, List<PhotoInfo> photoInfoList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.photoInfoList = photoInfoList;
        mImageLoader = new GlideImageLoader();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoImage;

        public ViewHolder(View itemView) {
            super(itemView);
            photoImage = (ImageView) itemView.findViewById(R.id.gallery_photo_image_view);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.gallery_item_photos, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        mImageLoader.displayImage(mActivity,
                mContext,
                photoInfoList.get(position).path,
                holder.photoImage,
                ScreenUtils.getScreenWidth(mContext)/5,
                ScreenUtils.getScreenWidth(mContext)/5);

        // active the holder clickable
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(photoInfoList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoInfoList.size();
    }


    public interface OnClickListener {
        void onClick(PhotoInfo photoInfo);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
