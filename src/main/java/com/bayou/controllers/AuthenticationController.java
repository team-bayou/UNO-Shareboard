package com.bayou.controllers;

import com.bayou.engines.AuthenticationEngine;
import com.bayou.exceptions.VerificationException;
import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.managers.impl.UserManager;
import com.bayou.views.impl.LoginView;
import com.bayou.views.impl.UserView;
import com.bayou.views.impl.VerifyUserView;
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
 * Created by Rachel on 2/21/2017.
 *
 * Controller for logging in, and verifying users.
 */
@RestController
@RequestMapping("service/v1/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationEngine authenticationEngine = new AuthenticationEngine();

    @ApiOperation(value = "Login as user by email or account name", response = ResponseEntity.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> login(@RequestBody VerifyUserView verifyUserView) throws NotFoundException {
        ResponseEntity<UserView> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(authenticationEngine.login(verifyUserView), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VerificationException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Verify unverified user", response = ResponseEntity.class)
    @RequestMapping(value = "/verify", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> verify(@RequestBody VerifyUserView verifyUserView) throws NotFoundException {
        ResponseEntity<UserView> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(authenticationEngine.verify(verifyUserView), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VerificationException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return responseEntity;
    }
}
