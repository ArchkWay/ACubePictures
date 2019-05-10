package com.example.archek.acubepictures.usersscreen.model;

import android.content.Context;


import com.example.archek.acubepictures.contracts.UsersContract;
import com.example.archek.acubepictures.di.BaseApp;
import com.example.archek.acubepictures.utils.Api;
import com.example.archek.acubepictures.utils.RetrofitProvider;
import com.example.archek.acubepictures.utils.pojos.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UsersModel implements UsersContract.model{
    /*get through retrofit users for further using -> provider -> view*/
    private final Api api;
    @Inject
    RetrofitProvider provider;

    public UsersModel(Context context){
        BaseApp.get(context).getInjector().inject(this);
        api = provider.getApi();
    }

    @Override
    public Observable<List <User>> getUsers() {
        return api.getUsers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
