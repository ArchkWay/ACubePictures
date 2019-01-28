package com.example.archek.acubepictures.utils;

import com.example.archek.acubepictures.utils.pojos.Album;
import com.example.archek.acubepictures.utils.pojos.Photo;
import com.example.archek.acubepictures.utils.pojos.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {//interface with get queries

    @GET("/users")
    Observable<List<User>> getUsers();

    @GET("albums")
    Observable<List<Album>> getAlbums(@Query("userId") String userId);

    @GET("photos")
    Observable<List<Photo>> getPhotos(@Query("albumId") String albumId);



}
