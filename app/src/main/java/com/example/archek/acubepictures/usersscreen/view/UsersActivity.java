package com.example.archek.acubepictures.usersscreen.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.archek.acubepictures.R;
import com.example.archek.acubepictures.contracts.UsersContract;
import com.example.archek.acubepictures.di.BaseApp;
import com.example.archek.acubepictures.photosscreen.views.PhotosActivity;
import com.example.archek.acubepictures.utils.pojos.User;

import java.util.List;

import javax.inject.Inject;


public class UsersActivity extends AppCompatActivity implements UsersContract.view, UsersAdapter.Callback{
    private UsersAdapter adapter;

    @Inject
    UsersContract.usersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Users");
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        /*get recyclerview parts, usersPresenter and data objects from it*/
        RecyclerView rvUsers = findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdapter(this);
        rvUsers.setAdapter(adapter);
        BaseApp.get(this).getInjector().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void setUsers(List<User> users) {
        /*replace data through the adapter into the recyclerview*/
        adapter.replaceAll(users);
    }

    @Override
    public void onDestroy() {
        /*if destroy - detach view*/
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onUserClick(User user) {
        Intent intent = PhotosActivity.makeIntent(this, user);
        startActivity(intent);
    }
}