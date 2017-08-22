package com.vmoreno.bapp.presenter;

import com.vmoreno.bapp.domain.Interactor;
import com.vmoreno.bapp.model.User;
import com.vmoreno.bapp.view.UsersView;

import java.util.List;

/**
 * Created by menis on 3/07/2017.
 */

public class UserPresenter implements UserPresenterCallBack {

    private UsersView usersViewP;
    Interactor interactor;

    public UserPresenter(UsersView usersView, Interactor model) {
        usersViewP = usersView;
        interactor = model;
    }

    public void getUsersList() {
        interactor.getUsersList(this);
    }

    @Override
    public void resultGetUser(List<User> listUser) {
        usersViewP.resultListUsers(listUser);
    }

    @Override
    public void failureUser(String message) {

    }
}
