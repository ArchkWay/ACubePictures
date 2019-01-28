package com.example.archek.acubepictures.photosscreen.models;

import android.content.Context;


import com.example.archek.acubepictures.contracts.PhotosContract;
import com.example.archek.acubepictures.di.BaseApp;
import com.example.archek.acubepictures.utils.Api;
import com.example.archek.acubepictures.utils.RetrofitProvider;
import com.example.archek.acubepictures.utils.pojos.Album;
import com.example.archek.acubepictures.utils.pojos.Photo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PhotosModel implements PhotosContract.model{
    /*get through retrofit mocks for further using -> provider -> view*/
    private final Api api;
    @Inject
    RetrofitProvider provider;

    public PhotosModel(Context context){
        BaseApp.get(context).getInjector().inject(this);
        api = provider.getApi();
    }
        /*get albums by userId from*/
    @Override
    public Observable<List<Album>> getAlbums(String id) {
        return api.getAlbums(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /*get photos by photoId from*/
    @Override
    public Observable <List <Photo>> getPhotos(String idAlbum) {
        return api.getPhotos(idAlbum).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
