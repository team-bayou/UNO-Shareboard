package com.bayou.managers.impl;

import com.bayou.converters.LoginConverter;
import com.bayou.converters.UnverifiedUserConverter;
import com.bayou.converters.UserConverter;
import com.bayou.domains.UnverifiedUser;
import com.bayou.engines.UnverifiedUserEngine;
import com.bayou.exceptions.VerificationException;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.UnverifiedUserResourceAccessor;
import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UnverifiedUserView;
import com.bayou.views.impl.VerifyUserView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    LoginConverter loginConverter = new LoginConverter();

    @Autowired
    UserConverter userConverter = new UserConverter();

    @Autowired
    UserManager userManager = new UserManager();

    @Autowired
    UnverifiedUserEngine unvEngine = new UnverifiedUserEngine();

    public LoginView verify(VerifyUserView verifyUserView) throws NotFoundException, VerificationException {
       UnverifiedUser unverifiedUser = ras.findByEmail(verifyUserView.getEmail());
       LoginView userView;

       verifyUserView.setPasswordHash(unverifiedUser.getPasswordHash());
       verifyUserView.setPasswordSalt(unverifiedUser.getPasswordSalt());
       boolean passwordFail = verifyUserView.login();
       boolean verifFail = verifyUserView.getEnteredVerificationCode().equals(unverifiedUser.getVerificationCode());
       if(passwordFail && verifFail) {
           Long id = userManager.add(verifyUserView);
           userView = loginConverter.convertToLoginView(userConverter.convertToDomain(userManager.get(id)));
           delete(unverifiedUser.getId());
       } else {
           String message;
           if(!passwordFail && !verifFail) {
               message = "both";
           } else if(!passwordFail) {
               message = "password";
           } else {
               message = "verify";
           }
           throw new VerificationException(message);
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
            id = ras.add(converter.convertToDomain(userView));  //add the unverified user to the database
            try {   //try to send the verification code to the email of the unverified user
                unvEngine.sendVerificationCode(userView.getVerificationCode().toString() , userView.getEmail());
            } catch (IOException e) {   //catch a IO exception if an issue occured performing this operation
                e.printStackTrace();
            }
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
