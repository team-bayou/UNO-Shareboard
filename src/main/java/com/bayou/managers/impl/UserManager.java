package com.bayou.managers.impl;

import com.bayou.converters.UserConverter;
import com.bayou.managers.IUserManager;
import com.bayou.ras.UserResourceAccessor;
import com.bayou.views.impl.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Service
public class UserManager implements IUserManager{

    @Autowired
    UserResourceAccessor ras = new UserResourceAccessor();

    @Autowired
    UserConverter converter = new UserConverter();

    @Override
    public UserView addUser(UserView userView) {

        return converter.convertToView(ras.addUser(converter.convertToDomain(userView)));
    }

    //TODO implement
    @Override
    public UserView updateUser(UserView userView) {
        return null;
    }

    //TODO implement
    @Override
    public UserView getUser(UserView userView) {
        return null;
    }

    //TODO implement
    @Override
    public UserView deleteUser() {
        return null;
    }
}
