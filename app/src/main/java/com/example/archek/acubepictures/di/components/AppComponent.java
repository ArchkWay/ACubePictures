package com.example.archek.acubepictures.di.components;



import com.example.archek.acubepictures.di.modules.MvpModule;
import com.example.archek.acubepictures.photosscreen.models.PhotosModel;
import com.example.archek.acubepictures.photosscreen.presenters.PhotosPresenter;
import com.example.archek.acubepictures.photosscreen.views.PhotosActivity;
import com.example.archek.acubepictures.usersscreen.model.UsersModel;
import com.example.archek.acubepictures.usersscreen.presenter.UsersUsersPresenter;
import com.example.archek.acubepictures.usersscreen.view.UsersActivity;

import javax.inject.Singleton;

import dagger.Component;

/*places for injecting*/
@Singleton
@Component(modules = {MvpModule.class})
public interface AppComponent {
    void inject(UsersActivity view);
    void inject(UsersUsersPresenter presenter);
    void inject(UsersModel model);

    void inject(PhotosActivity view);
    void inject(PhotosPresenter presenter);
    void inject(PhotosModel model);
}
