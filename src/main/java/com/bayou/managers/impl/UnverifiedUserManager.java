package com.bayou.managers.impl;

import com.bayou.converters.UnverifiedUserConverter;
import com.bayou.domains.UnverifiedUser;
import com.bayou.exceptions.VerificationException;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.UnverifiedUserResourceAccessor;
import com.bayou.views.impl.UnverifiedUserView;
import com.bayou.views.impl.UserView;
import com.bayou.views.impl.VerifyUserView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rachelguillory on 2/16/2017.
 */
@Service
public class UnverifiedUserManager implements IManager<UnverifiedUserView> {
    @Autowired
    UnverifiedUserResourceAccessor ras = new UnverifiedUserResourceAccessor();

    @Autowired
    UnverifiedUserConverter converter = new UnverifiedUserConverter();

    @Autowired
    UserManager userManager = new UserManager();

    public UserView verify(VerifyUserView verifyUserView) throws NotFoundException, VerificationException {
       UnverifiedUser unverifiedUser = ras.findByEmail(verifyUserView.getEmail());
       UserView userView;

       verifyUserView.setPasswordHash(unverifiedUser.getPasswordHash());
       verifyUserView.setPasswordSalt(unverifiedUser.getPasswordSalt());
       if(verifyUserView.login() && verifyUserView.getEnteredVerificationCode().equals(unverifiedUser.getVerificationCode())) {
           Long id = userManager.add(verifyUserView);
           userView = userManager.get(id);
           delete(unverifiedUser.getId());
       } else {
           throw new VerificationException();
       }

       return userView;
    }

    @Override
    public UnverifiedUserView get(Long id) throws NotFoundException {
        UnverifiedUserView unvUserView;
        UnverifiedUser unvUser = ras.find(id);

        if (unvUser == null) {
            throw new NotFoundException(String.valueOf(id));
        } else {
            unvUserView = converter.convertToView(unvUser);
        }

        return unvUserView;
    }

    @Override
    public List<UnverifiedUserView> getAll() throws NotFoundException {
        return null;
    }

    public UnverifiedUserView getByEmail(String email) throws NotFoundException {
        UnverifiedUserView unvUserView;
        UnverifiedUser unvUser = ras.findByEmail(email);

        if (unvUser == null) {
            throw new NotFoundException(email);
        } else {
            unvUserView = converter.convertToView(unvUser);
        }

        return unvUserView;
    }

    @Override
    public Long add(UnverifiedUserView userView) {
        Long id = -1L;
        try {
            id = ras.add(converter.convertToDomain(userView));
        } catch (DataIntegrityViolationException e) {
            System.err.println("A user already exists with the provided email.");
        }

        return id;
    }

    //TODO implement
    @Override
    public UnverifiedUserView update(UnverifiedUserView userView) {
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            ras.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("The user with ID:" + id + " does not exist in the database ");
        }
    }
}
