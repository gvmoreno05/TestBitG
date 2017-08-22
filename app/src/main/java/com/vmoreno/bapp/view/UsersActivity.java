package com.vmoreno.bapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vmoreno.bapp.R;
import com.vmoreno.bapp.client.retrofit.RetrofitServiceApi;
import com.vmoreno.bapp.domain.Interactor;
import com.vmoreno.bapp.model.User;
import com.vmoreno.bapp.presenter.UserPresenter;
import com.vmoreno.bapp.view.adapters.AdapterUser;

import java.util.List;

public class UsersActivity extends AppCompatActivity implements UsersView {
    private RecyclerView rcview;
    private LinearLayoutManager layoutManager;
    private RetrofitServiceApi retrofitServiceApi;
    private UserPresenter userPresenter;
    private Interactor interactor;
    private AdapterUser adapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
   /*     retrofitServiceApi = ((BApplication) getApplicationContext()).getRetrofitApi();
        interactor = ((BApplication) getApplicationContext()).getUserModel(retrofitServiceApi);
        userPresenter =((BApplication) getApplicationContext()).getUserPresenter(interactor, this);*/
        rcview = (RecyclerView) findViewById(R.id.rcv_users);
        rcview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rcview.setLayoutManager(llm);
        callUsersList();
    }

    public void callUsersList() {
        userPresenter.getUsersList();
    }

    @Override
    public void resultListUsers(List<User> users) {
        adapterUser = new AdapterUser(users, this);
        rcview.setAdapter(adapterUser);
    }

    @Override
    public void failureUsers(String message) {

    }

}