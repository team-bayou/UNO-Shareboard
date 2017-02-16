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


    public UserView getByEmail(String email) {

        return converter.convertToView(ras.getByEmail(email));
    }

    public UserView getById(Long id) {

        return converter.convertToView(ras.getById(id));
    }

    @Override
    public UserView add(UserView userView) {

        return converter.convertToView(ras.add(converter.convertToDomain(userView)));
    }

    //TODO implement
    @Override
    public UserView update(UserView userView) {
        return null;
    }

    //TODO implement
    @Override
    public UserView get(UserView userView) {
        return null;
    }

    //TODO implement
    @Override
    public UserView delete() {
        return null;
    }
}
