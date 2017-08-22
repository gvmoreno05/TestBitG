package com.vmoreno.bapp.client.retrofit;

import com.vmoreno.bapp.model.User;

import java.util.List;

/**
 * Created by menis on 3/07/2017.
 */

public interface RetrofitCallBack {

    void resultGetUserList(List<User> userList);

    void failureUserList(String message);
}
