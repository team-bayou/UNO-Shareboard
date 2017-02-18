package com.bayou.managers;

import com.bayou.views.impl.UserView;

/**
 * Created by joshuaeaton on 1/31/17.
 */
public interface IUserManager {

    UserView add(UserView userView);

    UserView update(UserView userView);

    void delete(Long id);

}
