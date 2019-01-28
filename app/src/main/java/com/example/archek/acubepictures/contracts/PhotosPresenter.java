package com.example.archek.acubepictures.contracts;


public interface PhotosPresenter<V> {
    void attachView(V mvpView, String userId);
    void detachView();
}
