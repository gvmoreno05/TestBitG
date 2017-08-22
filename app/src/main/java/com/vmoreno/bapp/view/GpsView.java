package com.vmoreno.bapp.view;

import com.vmoreno.bapp.model.User;

/**
 * Created by menis on 3/07/2017.
 */

public interface GpsView {

    void returnGPSResult(User user);

    void failureGps(String message);
}
