package com.example.photome.Gallery.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.photome.Gallery.utils.FolderInfo;
import com.example.photome.Gallery.Loader.GlideImageLoader;
import com.example.photome.R;
import com.example.photome.Utils.ScreenUtils;

import java.util.List;

/**
 * Adapter should contain:
 * onCreateViewHolder
 * onBindViewHolder
 * getItemCount
 */
public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {

    private static final String TAG = FolderAdapter.class.getSimpleName();

    private Context mContext;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<FolderInfo> mFolderList;
    private GlideImageLoader mImageLoader;
    private OnClickListener onClickListener;

    public FolderAdapter(Activity activity, Context context, List<FolderInfo> result) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mActivity = activity;
        this.mFolderList = result;
        this.mImageLoader = new GlideImageLoader();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.gallery_item_folder, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position == 0) {
            holder.galleryFolderName.setText(R.string.gallery_all_images);
            holder.galleryPhotoNum.setText(mContext.getString(R.string.gallery_photo_num, getTotalImageSize()));
            if (mFolderList.size() > 0) {
                // Show the first image to the album.
                mImageLoader.displayImage(mActivity,
                        mContext,
                        mFolderList.get(0).photoInfo.path,
                        holder.galleryFolderImage,
                        ScreenUtils.getScreenWidth(mContext) / 5,
                        ScreenUtils.getScreenWidth(mContext) / 5);
            }

            // active the holder clickable
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(null);
                }
            });

            return;
        }

        final FolderInfo folderInfo = mFolderList.get(position - 1);
        holder.galleryFolderName.setText(folderInfo.name);
        holder.galleryPhotoNum.setText(mContext.getString(R.string.gallery_photo_num, folderInfo.photoInfoList.size()));
        // show the image to the album.
        mImageLoader.displayImage(mActivity,
                mContext,
                folderInfo.photoInfo.path,
                holder.galleryFolderImage,
                ScreenUtils.getScreenWidth(mContext) / 5,
                ScreenUtils.getScreenWidth(mContext) / 5);

        // active th holder clickable
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(folderInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFolderList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView galleryFolderImage;
        private TextView galleryFolderName;
        private TextView galleryPhotoNum;

        public ViewHolder(View itemView) {
            super(itemView);
            galleryFolderImage = (ImageView) itemView.findViewById(R.id.gallery_image_view);
            galleryFolderName = (TextView) itemView.findViewById(R.id.gallery_folder_name);
            galleryPhotoNum = (TextView) itemView.findViewById(R.id.gallery_photo_number);
        }
    }

    public interface OnClickListener {
        void onClick(FolderInfo folderInfo);
    }

    /**
     * @return All Image size
     */
    private int getTotalImageSize() {
        int result = 0;
        if (this.mFolderList != null && this.mFolderList.size() > 0) {
            for (FolderInfo folderInfo : this.mFolderList) {
                result += folderInfo.photoInfoList.size();
            }
        }
        return result;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
