package com.bayou.managers.impl;

import com.bayou.converters.UnverifiedUserConverter;
import com.bayou.managers.IUnverifiedUserManager;
import com.bayou.ras.UnverifiedUserResourceAccessor;
import com.bayou.repository.IUnverifiedUserRepository;
import com.bayou.views.impl.UnverifiedUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rachelguillory on 2/16/2017.
 */
@Service
public class UnverifiedUserManager implements IUnverifiedUserManager{
    @Autowired
    UnverifiedUserResourceAccessor ras = new UnverifiedUserResourceAccessor();

    @Autowired
    UnverifiedUserConverter converter = new UnverifiedUserConverter();


    public UnverifiedUserView getByEmail(String email) {

        return converter.convertToView(ras.getByEmail(email));
    }

    public UnverifiedUserView getById(Long id) {

        return converter.convertToView(ras.getById(id));
    }

    @Override
    public UnverifiedUserView add(UnverifiedUserView userView) {

        return converter.convertToView(ras.add(converter.convertToDomain(userView)));
    }

    //TODO implement
    @Override
    public UnverifiedUserView update(UnverifiedUserView userView) {
        return null;
    }

    //TODO implement
    @Override
    public UnverifiedUserView get(UnverifiedUserView userView) {
        return null;
    }

    //TODO implement
    @Override
    public UnverifiedUserView delete() {
        return null;
    }
}