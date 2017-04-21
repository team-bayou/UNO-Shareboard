package com.bayou.controllers;

import com.bayou.exceptions.ValidationException;
import com.bayou.exceptions.VerificationException;
import com.bayou.loggers.Loggable;
import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.managers.impl.UserManager;
import com.bayou.views.LoginView;
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

/**
 * Created by Rachel on 2/21/2017.
 * <p>
 * Controller for logging in, and verifying users.
 */
@RestController
@RequestMapping("service/v1/auth")
public class AuthenticationController {
    @Autowired
    private UserManager userManager;

    @Autowired
    private UnverifiedUserManager unverifiedUserManager;

    @Loggable
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
    @Loggable
    @ApiOperation(value = "Verify unverified user", response = ResponseEntity.class)
    @RequestMapping(value = "/verify", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<LoginView> verify(@RequestBody VerifyUserView verifyUserView) throws NotFoundException, ValidationException {
        ResponseEntity<LoginView> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(unverifiedUserManager.verify(verifyUserView), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VerificationException e) {
            LoginView errorView = new LoginView();
            errorView.setErrorMessage(e.getMessage());
            responseEntity = new ResponseEntity<>(errorView, HttpStatus.BAD_REQUEST);
        } catch(ValidationException ve) {
            LoginView errorView = new LoginView();
            errorView.setErrorMessage(ve.getMessage());
            responseEntity = new ResponseEntity<>(errorView, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Forgot password", response = ResponseEntity.class)
    @RequestMapping(value = "/forgotPass", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity forgotPassword(@RequestBody VerifyUserView verifyUserView) throws ValidationException {
        ResponseEntity responseEntity;

        try {
            userManager.forgotPassword(verifyUserView);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        } catch (NotFoundException nfe) {
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }
    @Loggable
    @ApiOperation(value = "Reset password", response = ResponseEntity.class)
    @RequestMapping(value = "/resetPass", method = RequestMethod.POST)
    public ResponseEntity resetPassword(@RequestBody VerifyUserView verifyUserView) throws ValidationException {
        ResponseEntity responseEntity;

        try {
            userManager.resetPassword(verifyUserView);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        } catch (NotFoundException nfe) {
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (VerificationException ve) {
            responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
}
