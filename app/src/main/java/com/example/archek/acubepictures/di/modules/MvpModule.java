package com.example.archek.acubepictures.di.modules;

import android.content.Context;


import com.example.archek.acubepictures.contracts.PhotosContract;
import com.example.archek.acubepictures.contracts.UsersContract;
import com.example.archek.acubepictures.photosscreen.models.PhotosModel;
import com.example.archek.acubepictures.photosscreen.presenters.PhotosPresenter;
import com.example.archek.acubepictures.usersscreen.model.UsersModel;
import com.example.archek.acubepictures.usersscreen.presenter.UsersUsersPresenter;
import com.example.archek.acubepictures.utils.RetrofitProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MvpModule {
    /*modules injectors*/
    private final Context context;
    public MvpModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    UsersContract.usersPresenter provideUsersContractPresenter(Context context){
        return new UsersUsersPresenter(context);
    }

    @Provides
    @Singleton
    UsersContract.model provideUsersContractModel(Context context){
        return new UsersModel(context);
    }


    @Provides
    @Singleton
    PhotosContract.photosPresenter providePhotosContractPresenter(Context context){
        return new PhotosPresenter(context);
    }

    @Provides
    @Singleton
    PhotosContract.model providePhotosContractModel(Context context){
        return new PhotosModel(context);
    }

    @Provides
    @Singleton
    RetrofitProvider provideRetrofit(){
        return new RetrofitProvider();
    }
}
