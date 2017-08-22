package com.vmoreno.bapp.client.retrofit;
import com.vmoreno.bapp.model.ResponseUserList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by menis on 3/07/2017.
 */

public interface UsersApiConfig {

    String URL_COMPLEMENT_USERS = "users";

    @GET(URL_COMPLEMENT_USERS)
    Call<ResponseUserList> getUsers();

}
