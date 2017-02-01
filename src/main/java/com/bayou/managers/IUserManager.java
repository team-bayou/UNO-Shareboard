package com.bayou.managers;

import com.bayou.views.impl.UserView;

/**
 * Created by joshuaeaton on 1/31/17.
 */
public interface IUserManager {

    UserView addUser(UserView userView);

    UserView updateUser(UserView userView);

    UserView getUser(UserView userView);

    UserView deleteUser();

}
