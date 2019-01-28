package com.example.archek.acubepictures.di;

import android.app.Application;
import android.content.Context;

import com.example.archek.acubepictures.di.components.AppComponent;
import com.example.archek.acubepictures.di.components.DaggerAppComponent;
import com.example.archek.acubepictures.di.modules.MvpModule;


public class BaseApp extends Application {
    /*baseDI*/
    private AppComponent appComponent;

    public AppComponent getInjector() {
        if(appComponent == null){
            appComponent = DaggerAppComponent
                    .builder()
                    .mvpModule(new MvpModule(this))
                    .build();
        }
        return appComponent;
    }
    public static BaseApp get(Context ctx){
            return (BaseApp)ctx.getApplicationContext();
    }
}
