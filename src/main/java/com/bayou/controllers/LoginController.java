package com.bayou.controllers;

import com.bayou.converters.LoginConverter;
import com.bayou.managers.impl.UserManager;
import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@RestController
@RequestMapping("service/v1/login")
public class LoginController {

    @Autowired
    UserManager userManager = new UserManager();
    @Autowired
    LoginConverter converter = new LoginConverter();

    @ApiOperation(value = "Login as user by email or account name", response = ResponseEntity.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<LoginView> login(@RequestBody LoginView loginView) {

        //TODO:move converting logic to manager
        if(!loginView.getEmail().equals(null)) { //if email field is not null, get the user by email
            UserView returnedUser = userManager.getByEmail(loginView.getEmail());   //stores the returned object into returnedUser
            loginView = converter.convertToLoginView(returnedUser); //converts the returned user to a login view object
        }
        else if (!loginView.getAccountName().equals(null)) { //if account name is not null, get the user by account name
            //UserView returnedUer = userManager.getByAccountName(loginVIew.getByAccountName);
            //loginView = converter.convertToLoginView(returnedUser); //converst the returned user to a login view object
        } else {
            //throw exception here
        }

        return new ResponseEntity<>(loginView, HttpStatus.OK);
    }
}