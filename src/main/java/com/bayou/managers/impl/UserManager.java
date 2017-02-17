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

    public UserView login(UserView userView){
        // TODO Decide whether to fetch the user by account name or by email.

        return converter.convertToView(ras.findByEmail(userView.getEmail()));
    }

    public UserView getByAccountName(String accountName) {
        return converter.convertToView(ras.findByAccountName(accountName));
    }

    public UserView get(Long id) {
        return converter.convertToView(ras.findById(id));
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

    @Override
    public void delete(Long id) {
        ras.delete(id);
    }
}
