package com.bayou.managers.impl;

import com.bayou.converters.LoginConverter;
import com.bayou.converters.UserConverter;
import com.bayou.domains.User;
import com.bayou.engines.UserEngine;
import com.bayou.exceptions.VerificationException;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.UserResourceAccessor;
import com.bayou.views.LoginView;
import com.bayou.views.UserView;
import com.bayou.views.VerifyUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@Service
public class UserManager implements IManager<UserView> {
    @Autowired
    private UserResourceAccessor userRas;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private LoginConverter loginConverter;

    @Autowired
    private UserEngine userEngine;

    public LoginView login(VerifyUserView verifyUserView) throws NotFoundException, VerificationException {
        UserView returnedUser;

        if (verifyUserView.getEmail() != null) { //if email field is not null, get the user by email
            returnedUser = getByEmail(verifyUserView.getEmail());
        } else if (verifyUserView.getAccountName() != null) { //if account name is not null, get the user by account name
            returnedUser = getByAccountName(verifyUserView.getAccountName());
        } else {
            throw new NotFoundException("Values of email or account name not found" + "email: " + verifyUserView.getEmail() + " account name: " + verifyUserView.getAccountName());
        }

        if (returnedUser == null) {
            throw new NotFoundException("The requested user does not exist in the database");
        } else {
            verifyUserView.setPasswordHash(returnedUser.getPasswordHash());
        }

        if (verifyUserView.login()) {
            return loginConverter.convertToView(returnedUser);
        } else {
            throw new VerificationException("password");
        }
    }

    public UserView get(Long id) throws NotFoundException {
        UserView userView;
        User user = userRas.find(id);

        if (user == null) {
            throw new NotFoundException(String.valueOf(id));
        } else {
            userView = userConverter.convertToView(user);
        }

        return userView;
    }

    @Override
    public List<UserView> getAll() throws NotFoundException {
        List<UserView> views = new ArrayList<>();

        for (User u : userRas.findAll()) {   //convert each domain Object in the returned list to a view
            views.add(userConverter.convertToView(u));
        }

        return views;
    }

    public UserView getByAccountName(String accountName) throws NotFoundException {
        User returnedUser;
        UserView newUserView;

        returnedUser = userRas.findByAccountName(accountName);

        if (returnedUser == null) {
            throw new NotFoundException("The requested user does not exist in the database");
        } else {
            newUserView = userConverter.convertToView(returnedUser);
        }

        return newUserView;
    }

    public UserView getByEmail(String email) throws NotFoundException {
        User returnedUser;
        UserView newUserView;

        returnedUser = userRas.findByEmail(email);

        if (returnedUser == null) {
            throw new NotFoundException("The requested user does not exist in the database");
        } else {
            newUserView = userConverter.convertToView(returnedUser);
        }

        return newUserView;
    }

    @Override
    public Long add(UserView userView) {
        Long id = -1L;
        try {
            id = userRas.add(userConverter.convertToDomain(userView));
        } catch (DataIntegrityViolationException e) {
            System.err.println("User: " + userView.getAccountName() + " already exist");
        }

        return id;
    }


    @Override
    public Long update(UserView userView) {
        User user = userConverter.convertToDomain(userView);    //converts the user view to the user domain Object
        if (userView.getId() == null)    //triggers a no content if the id is null
        {
            return -1L;
        }

        User retrievedUser = userRas.find(userView.getId());    //get the user we are updating

        if (retrievedUser == null) {    //if the requested user doesn't exist
            throw new NotFoundException();
        }

        user.setVersion(retrievedUser.getVersion());   //gets the record's we are updating version number

        user = userConverter.updateConversion(user, retrievedUser); //adds values to any null properties that were not sent in the request on a partial update

        return userRas.update(user);
    }

    @Override
    public void delete(Long id) {
        try {
            userRas.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("The user with ID:" + id + " does not exist in the database");
        }
    }

    public void forgotPassword(VerifyUserView verifyUserView) throws NotFoundException {
        UserView userView = getByEmail(verifyUserView.getEmail());

        Random randomGen = new Random();
        userView.setVerificationCode(Math.abs(randomGen.nextInt()));

        try {   //try to send the verification code to the email of the user
            userEngine.sendVerificationCode(userView.getVerificationCode().toString(), userView.getEmail());
            update(userView);
        } catch (IOException e) {   //catch a IO exception if an issue occured performing this operation
            System.err.println("Email could not be sent due to:\n\t" + e.getMessage());
        }

    }

    public void resetPassword(VerifyUserView verifyUserView) throws NotFoundException, VerificationException {
        UserView userView = getByEmail(verifyUserView.getEmail());;

        if(verifyUserView != null && verifyUserView.getEnteredVerificationCode().equals(userView.getVerificationCode())) {
            userView.setPasswordSalt(verifyUserView.getEnteredPasswordSalt());
            userView.setPasswordHash(verifyUserView.getEnteredPasswordHash());
            userView.setVerificationCode(null);
            update(userView);
        } else {
            throw new VerificationException("Verification code does not match.");
        }
    }
}
