package com.example.archek.acubepictures.contracts;



import com.example.archek.acubepictures.utils.pojos.Album;
import com.example.archek.acubepictures.utils.pojos.Photo;

import java.util.List;

import io.reactivex.Observable;

public interface PhotosContract {
    /*interfaces for mvp*/
    interface view {

        void setPhotos(List <Photo> photos);
    }

    interface photosPresenter extends PhotosPresenter<view> {

    }

    interface model {
        Observable <List <Album>> getAlbums(String idUser);
        Observable <List <Photo>> getPhotos(String idAlbum);
    }
}
