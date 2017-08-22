package com.vmoreno.bapp.presenter;

import com.vmoreno.bapp.domain.Interactor;
import com.vmoreno.bapp.model.User;
import com.vmoreno.bapp.view.GpsView;

/**
 * Created by menis on 3/07/2017.
 */

public class GPSPresenter implements GpsPresenterCalLBack {

    GpsView gpsViewp;
    Interactor interactorp;

    public GPSPresenter(Interactor interactor, GpsView gpsView) {
        gpsViewp = gpsView;
        interactorp = interactor;
    }

    public void getGpsLatLong(){
        interactorp.getGpsData(this);
    }

    @Override
    public void returnGps(User objUser) {
        gpsViewp.returnGPSResult(objUser);
    }

    @Override
    public void failureGps(String message) {
        gpsViewp.failureGps(message);
    }
}
