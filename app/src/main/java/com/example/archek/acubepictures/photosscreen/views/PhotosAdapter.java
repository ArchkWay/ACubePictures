package com.example.archek.acubepictures.photosscreen.views;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.archek.acubepictures.R;
import com.example.archek.acubepictures.utils.pojos.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;




public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private List <Photo> photos = new ArrayList <>();//main list for results to adapter

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
        Picasso.get()
                .load(photo.getUrl())
                .placeholder(R.drawable.progress_animation)
                .into(holder.ivPhoto);
        holder.tvText.setText(photo.getTitle());
        if(Integer.valueOf(photo.getId())% photos.size() != 0) {
            holder.tvIdnum.setText(Integer.valueOf(photo.getId()) % photos.size() + " / " + photos.size());
        }
        else holder.tvIdnum.setText(photos.size() + " / " + photos.size());
    }


    @Override
    public int getItemCount() {
        return (photos == null) ? 0 : photos.size();
    } //count all items


    public void replaceAll(List <Photo> collectPhotos) {//load all photos in main list
        photos.clear();
        photos.addAll(collectPhotos);

        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPhoto; //initial all views in holder
        private TextView tvText;
        private TextView tvIdnum;


        private ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);//initiate views
            tvText = itemView.findViewById(R.id.tvText);
            tvIdnum = itemView.findViewById(R.id.tvIdnum);

        }
    }
}
