package com.example.archek.acubepictures.photosscreen.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.archek.acubepictures.R;
import com.example.archek.acubepictures.contracts.PhotosContract;
import com.example.archek.acubepictures.di.BaseApp;
import com.example.archek.acubepictures.utils.pojos.Photo;
import com.example.archek.acubepictures.utils.pojos.User;

import java.util.List;

import javax.inject.Inject;

public class PhotosActivity extends AppCompatActivity implements PhotosContract.view {

    @Inject
    PhotosContract.photosPresenter presenter;

    private static final String EXTRA_ID = "EXTRA_ID";
    private PhotosAdapter adapter;
    private RecyclerView rvPhotos;

    public static Intent makeIntent(Context context, User user) {
        return new Intent(context, PhotosActivity.class).putExtra(PhotosActivity.EXTRA_ID, user.getId());
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        setTitle("Photos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }
    private void init(){
        /*getting recyclerview parts, presenter and data objects from it*/
        BaseApp.get(this).getInjector().inject(this);

        /*getting userId from click from 1st screen*/
        Intent intent = getIntent();
        String userId = intent.getStringExtra(EXTRA_ID);

        rvPhotos = findViewById(R.id.rvPhotos);

        rvPhotos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PhotosAdapter();
        rvPhotos.setAdapter(adapter);
        presenter.attachView(this, userId);
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        /*sending data in adapter*/
        adapter.replaceAll(photos);
    }

    @Override
    public void onDestroy() {
        /*if destroy - detach view*/
        super.onDestroy();
        presenter.detachView();
        adapter.clearAll();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
