package com.example.archek.acubepictures.contracts;


import com.example.archek.acubepictures.utils.pojos.User;

import java.util.List;

import io.reactivex.Observable;

public interface UsersContract {
    /*interfaces for mvp*/
    interface view {
        void setUsers(List <User> users);
    }

    interface usersPresenter extends UsersPresenter<view> {

    }

    interface model {
        Observable <List <User>> getUsers();
    }
}
