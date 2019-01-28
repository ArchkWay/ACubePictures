package com.example.archek.acubepictures.photosscreen.views;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.acubepictures.R;
import com.example.archek.acubepictures.utils.pojos.Photo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;




public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private List <Photo> photos = new ArrayList <>();//main list for results to adapter
    private LruCache <String, Bitmap> bitmaps = new LruCache <>(1024);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        if(bitmaps.get(photo.getId()) != null) {
            holder.ivPhoto.setImageBitmap(getBitmapFromMemCache(photo.getId()));
        }
        else {
            Picasso.get().load(photo.getUrl())
                    .into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    addBitmapToMemoryCache(photo.getId(), bitmap);
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    Picasso.get().load(photo.getUrl()).placeholder(R.drawable.progress_animation)
                            .into(holder.ivPhoto);
                }
            });
        }
        holder.tvText.setText(photo.getTitle());
        if(Integer.valueOf(photo.getId())%500 != 0) {
            holder.tvTest.setText(Integer.valueOf(photo.getId()) % 500 + " / " + photos.size());
        }
        else holder.tvTest.setText(500 + " / " + photos.size());
    }

    @Override
    public int getItemCount() {
        return (photos == null) ? 0 : photos.size();
    } //count all items


    public void replaceAll(List <Photo> collectPhotos) {//load all mocks in main list
        photos.clear();
        photos.addAll(collectPhotos);
//        sortById();
        notifyDataSetChanged();
    }
    public void addAll(List <Photo> collectPhotos) {//load all mocks in main list
        photos.addAll(collectPhotos);
//        sortById();
        notifyDataSetChanged();
    }

    public void clearAll() {//clear all mocks in main list
        photos.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPhoto; //initial all views in holder
        private TextView tvText;
        private TextView tvTest;


        private ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);//initiate views
            tvText = itemView.findViewById(R.id.tvText);
            tvTest = itemView.findViewById(R.id.tvTest);

        }
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
            bitmaps.put(key, bitmap);
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return bitmaps.get(key);
    }
}
