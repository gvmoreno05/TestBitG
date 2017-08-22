package com.vmoreno.bapp.domain;

import com.vmoreno.bapp.client.retrofit.RetrofitCallBack;
import com.vmoreno.bapp.client.retrofit.RetrofitServiceApi;
import com.vmoreno.bapp.model.User;
import com.vmoreno.bapp.presenter.GPSPresenter;
import com.vmoreno.bapp.presenter.GpsPresenterCalLBack;
import com.vmoreno.bapp.presenter.UserPresenterCallBack;

import java.util.List;
import java.util.Random;

/**
 * Created by menis on 3/07/2017.
 */

public class Controller implements Interactor, Interactor.CalLBackUserModel, RetrofitCallBack {

    RetrofitServiceApi retrofitServiceApip;
    UserPresenterCallBack userPresenterCallBack;
    GpsPresenterCalLBack gpsPresenterCalLBack;

    public Controller(RetrofitServiceApi retrofitServiceApi) {
        retrofitServiceApip = retrofitServiceApi;
    }

    @Override
    public void returnGetUserModel(List<User> users) {

    }

    @Override
    public void failureGetUserModel(String Message) {

    }

    @Override
    public void getUsersList(UserPresenterCallBack callBack) {
        retrofitServiceApip.getUsers(this);
        userPresenterCallBack = callBack;
    }

    @Override
    public void getGpsData(GpsPresenterCalLBack calLBack) {
        retrofitServiceApip.getUsers(this);
        gpsPresenterCalLBack = calLBack;
    }

    @Override
    public void resultGetUserList(List<User> userList) {
        if (gpsPresenterCalLBack != null) {
            int userSize = userList.size();
            Random r = new Random();
            int indexList = r.nextInt(userSize);
            User user = userList.get(indexList);
            gpsPresenterCalLBack.returnGps(user);
        } else {
            userPresenterCallBack.resultGetUser(userList);
        }

    }

    @Override
    public void failureUserList(String message) {

    }
}
