package com.vmoreno.bapp.client.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vmoreno.bapp.model.ResponseUserList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by menis on 2/07/2017.
 */

public class RetrofitServiceApi {

    private UsersApiConfig usersApiConfig;
    private Retrofit retrofit;


    public RetrofitServiceApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usersApiConfig = retrofit.create(UsersApiConfig.class);

    }

    public void getUsers(final RetrofitCallBack callBack) {
        Call<ResponseUserList> call = usersApiConfig.getUsers();
        call.enqueue(new Callback<ResponseUserList>() {
            @Override
            public void onResponse(Call<ResponseUserList> call, Response<ResponseUserList> response) {
              /*  for (User objUser: response.body()){
                    Log.d("Users",objUser.getEmail());*/
                callBack.resultGetUserList(response.body());

            }

            @Override
            public void onFailure(Call<ResponseUserList> call, Throwable t) {
                callBack.failureUserList(t.getMessage());
            }
        });
    }
}
