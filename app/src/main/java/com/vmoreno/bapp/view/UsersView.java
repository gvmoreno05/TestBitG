package com.vmoreno.bapp.view;

import com.vmoreno.bapp.model.User;

import java.util.List;

/**
 * Created by menis on 3/07/2017.
 */

public interface UsersView {

        void resultListUsers(List<User> users);
        void failureUsers(String message);
}
