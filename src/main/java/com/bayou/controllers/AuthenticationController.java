package com.bayou.controllers;

import com.bayou.exceptions.VerificationException;
import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.managers.impl.UserManager;
import com.bayou.views.LoginView;
import com.bayou.views.UserView;
import com.bayou.views.VerifyUserView;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.NotFoundException;
import java.util.Random;

/**
 * Created by Rachel on 2/21/2017.
 *
 * Controller for logging in, and verifying users.
 */
@RestController
@RequestMapping("service/v1/auth")
public class AuthenticationController {
    @Autowired
    private UserManager userManager;

    @Autowired
    private UnverifiedUserManager unverifiedUserManager;

    @ApiOperation(value = "Login as user by email or account name", response = ResponseEntity.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<LoginView> login(@RequestBody VerifyUserView verifyUserView) throws NotFoundException {
        ResponseEntity<LoginView> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(userManager.login(verifyUserView), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VerificationException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Verify unverified user", response = ResponseEntity.class)
    @RequestMapping(value = "/verify", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<LoginView> verify(@RequestBody VerifyUserView verifyUserView) throws NotFoundException {
        ResponseEntity<LoginView> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(unverifiedUserManager.verify(verifyUserView), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VerificationException e) {
            LoginView errorView = new LoginView();
            errorView.setErrorMessage(e.getMessage());
            responseEntity = new ResponseEntity<>(errorView, HttpStatus.UNAUTHORIZED);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Forgot password", response = ResponseEntity.class)
    @RequestMapping(value = "/forgotPass", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<Integer> forgotPassword(@RequestBody VerifyUserView verifyUserView) {
        ResponseEntity responseEntity;
        UserView userView;

        try {
            userView = userManager.getByEmail(verifyUserView.getEmail());

            userManager.forgotPassword(verifyUserView);
        } catch (NotFoundException nfe) {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch
    }
}
