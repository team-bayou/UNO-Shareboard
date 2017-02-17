package com.bayou.controllers;

import com.bayou.managers.impl.UserManager;
import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UserView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@RestController
@RequestMapping("service/v1/login")
public class LoginController {

    @Autowired
    UserManager userManager = new UserManager();


    @ApiOperation(value = "Login as user by email or account name", response = ResponseEntity.class)
    @RequestMapping(value = "", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<LoginView> login(@RequestBody LoginView loginView) throws NotFoundException {

        LoginView returnedLoginView = userManager.login(loginView);

        return new ResponseEntity<>(returnedLoginView, HttpStatus.OK);
    }
}