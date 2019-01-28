package com.example.archek.acubepictures.photosscreen.presenters;


import android.annotation.SuppressLint;
import android.content.Context;
import com.example.archek.acubepictures.contracts.PhotosContract;
import com.example.archek.acubepictures.di.BaseApp;
import com.example.archek.acubepictures.utils.pojos.Album;

import java.util.List;

import javax.inject.Inject;
import io.reactivex.Observable;


public class PhotosPresenter implements PhotosContract.photosPresenter {
    PhotosContract.view view;
    @Inject
    PhotosContract.model model;

    @Inject
    public PhotosPresenter(Context context) {
        BaseApp.get(context).getInjector().inject(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void attachView(PhotosContract.view mvpView, String userId) {
        this.view = mvpView;
        /*getting there ids of albums*/
        model.getAlbums(userId).subscribe(this::getAlbumsIDs);
    }


    @SuppressLint("CheckResult")
    private void getAlbumsIDs(List<Album> albums) {
        /*getting all photos by each album, transform to the observable thread, and then to list */
        Observable.fromIterable(albums)
                .concatMap(album ->  model.getPhotos(album.getId()))
                .concatMap(Observable::fromIterable)
                .toList()
                .subscribe(view::setPhotos);//simply depict photos in the view

    }
    @Override
    public void detachView() {
        this.view = null;
    }
}




