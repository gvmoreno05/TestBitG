package com.vmoreno.bapp.presenter;

import com.vmoreno.bapp.model.User;

import java.util.List;

/**
 * Created by menis on 3/07/2017.
 */

public interface UserPresenterCallBack {

    void resultGetUser(List<User> listUser);

    void failureUser(String message);
}
