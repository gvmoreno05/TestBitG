package com.vmoreno.bapp.presenter;

import com.vmoreno.bapp.model.User;

/**
 * Created by menis on 3/07/2017.
 */

public interface GpsPresenterCalLBack {

    void returnGps(User objUser);
    void failureGps(String message);


}
