package com.vmoreno.bapp.domain;

import com.vmoreno.bapp.model.User;
import com.vmoreno.bapp.presenter.GpsPresenterCalLBack;
import com.vmoreno.bapp.presenter.UserPresenter;
import com.vmoreno.bapp.presenter.UserPresenterCallBack;

import java.util.List;

/**
 * Created by menis on 3/07/2017.
 */

public interface Interactor {

    void getUsersList(UserPresenterCallBack callBack);
    void getGpsData(GpsPresenterCalLBack calLBack);

    interface CalLBackUserModel {
        void returnGetUserModel(List<User> users);

        void failureGetUserModel(String Message);

    }

}
