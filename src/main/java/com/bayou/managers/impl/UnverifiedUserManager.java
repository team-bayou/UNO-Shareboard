package com.bayou.managers.impl;

import com.bayou.converters.UnverifiedUserConverter;
import com.bayou.domains.UnverifiedUser;
import com.bayou.managers.IUnverifiedUserManager;
import com.bayou.ras.UnverifiedUserResourceAccessor;
import com.bayou.repository.IUnverifiedUserRepository;
import com.bayou.views.impl.UnverifiedUserView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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


    public UnverifiedUserView getByEmail(String email) throws NotFoundException {

        UnverifiedUserView unvUserView = null;
        UnverifiedUser unvUser = ras.getByEmail(email);
        if(unvUser == null) {
            throw new NotFoundException(email);
        } else {
            unvUserView =converter.convertToView(unvUser);
        }
        return unvUserView;
    }

    public UnverifiedUserView getById(Long id) throws NotFoundException {

        UnverifiedUserView unvUserView = null;
        UnverifiedUser unvUser = ras.getById(id);
        if(unvUser == null) {
            throw new NotFoundException(String.valueOf(id));
        } else {
            unvUserView =converter.convertToView(unvUser);
        }
        return unvUserView;
    }

    @Override
    public void add(UnverifiedUserView userView) {

        try {
            ras.add(converter.convertToDomain(userView));
        } catch (DataIntegrityViolationException dive) {
            System.out.println("There already exists a user with the email " +
                    userView.getEmail() + ".");
            throw dive;
        }
    }

    //TODO implement
    @Override
    public UnverifiedUserView update(UnverifiedUserView userView) {
        return null;
    }

    //TODO implement
    @Override
    public UnverifiedUserView delete() {
        return null;
    }
}
