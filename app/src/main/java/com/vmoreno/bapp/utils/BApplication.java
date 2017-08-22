package com.vmoreno.bapp.utils;

import android.app.Application;
import com.vmoreno.bapp.client.retrofit.RetrofitServiceApi;
import com.vmoreno.bapp.domain.Controller;
import com.vmoreno.bapp.domain.Interactor;
import com.vmoreno.bapp.presenter.GPSPresenter;
import com.vmoreno.bapp.presenter.UserPresenter;
import com.vmoreno.bapp.view.GpsView;
import com.vmoreno.bapp.view.UsersView;

/**
 * Created by menis on 3/07/2017.
 */

public class BApplication extends Application {

    RetrofitServiceApi retrofitApi;
    Interactor userModel;
    UserPresenter userPresenter;
    GPSPresenter gpsPresenter;

    public Interactor getUserModel(RetrofitServiceApi retrofitServiceApi) {
        if (userModel == null) {
            userModel = new Controller(retrofitServiceApi);
        }
        return userModel;
    }

    public UserPresenter getUserPresenter(Interactor interactor, UsersView usersView) {
        if (userPresenter == null) {
            userPresenter = new UserPresenter(usersView, interactor);
        }
        return userPresenter;
    }
    public GPSPresenter getGpsPresenter(Interactor interactor, GpsView gpsView){
        if (gpsPresenter == null) {
            gpsPresenter = new GPSPresenter(interactor, gpsView);
        }
        return gpsPresenter;
    }

    public RetrofitServiceApi getRetrofitApi() {
        if (retrofitApi == null) {
            retrofitApi = new RetrofitServiceApi();
        }
        return retrofitApi;
    }
}
