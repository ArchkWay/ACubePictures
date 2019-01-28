package com.example.archek.acubepictures.usersscreen.presenter;


import android.annotation.SuppressLint;
import android.content.Context;


import com.example.archek.acubepictures.contracts.UsersContract;
import com.example.archek.acubepictures.di.BaseApp;

import javax.inject.Inject;

public class UsersUsersPresenter implements UsersContract.usersPresenter {
    UsersContract.view view;
    @Inject
    UsersContract.model model;

    @Inject
    public UsersUsersPresenter(Context context){
        BaseApp.get(context).getInjector().inject(this);
    }

    /*getting users from Model*/
    @SuppressLint("CheckResult")
    @Override
    public void attachView(UsersContract.view view) {
        this.view = view;
        model.getUsers().subscribe(view::setUsers);
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
