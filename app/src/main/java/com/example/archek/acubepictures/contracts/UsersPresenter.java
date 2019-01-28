package com.example.archek.acubepictures.contracts;

public interface UsersPresenter<V> {
    void attachView(V mvpView);
    void detachView();
}
