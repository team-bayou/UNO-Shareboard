package com.bayou.managers.impl;

import com.bayou.converters.LoginConverter;
import com.bayou.converters.UserConverter;
import com.bayou.domains.User;
import com.bayou.managers.IUserManager;
import com.bayou.ras.UserResourceAccessor;
import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UserView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Autowired
    LoginConverter loginConverter = new LoginConverter();

    public LoginView login(LoginView loginView) throws NotFoundException {

        User returnedUser = null;

        if(!(loginView.getEmail() == null)) { //if email field is not null, get the user by email
            returnedUser = ras.findByEmail(loginView.getEmail());
        }
        else if (!(loginView.getAccountName() == null)) { //if account name is not null, get the user by account name
            returnedUser = ras.findByAccountName(loginView.getAccountName());
        } else {
            throw new NotFoundException("Values of email or account name not found" +"email: "+loginView.getEmail()+" account name: "+loginView.getAccountName());
        }
        LoginView newLoginView = loginConverter.convertToLoginView(returnedUser);
        return newLoginView;
    }

    public UserView getByAccountName(String accountName) {
        return converter.convertToView(ras.findByAccountName(accountName));
    }

    public UserView get(Long id) throws NotFoundException {

        UserView userView;
        User user = ras.findById(id);

        if(user == null){
            throw new NotFoundException(String.valueOf(id));
        }
        else {
             userView = converter.convertToView(user);
        }

        return userView;
    }

    @Override
    public void add(UserView userView) {
        try {
           ras.add(converter.convertToDomain(userView));
        } catch (DataIntegrityViolationException e) {
            System.out.println("User: "+ userView.getAccountName() + "already exist");
        }
    }

    //TODO implement
    @Override
    public UserView update(UserView userView) {
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            ras.delete(id);
        }
        catch (EmptyResultDataAccessException e) {
        System.out.println("The user with ID:" + id + " does not exist in the database ");
        }
    }
}
