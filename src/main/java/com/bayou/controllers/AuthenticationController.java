package com.bayou.controllers;

<<<<<<< 6676458cd7013abca7b5bacf195ea74f610b6839
import com.bayou.exceptions.VerificationException;
import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.managers.impl.UserManager;
=======
import com.bayou.engines.AuthenticationEngine;
import com.bayou.exceptions.VerificationException;
import com.bayou.views.impl.LoginView;
>>>>>>> Authentication(Login/Verification) update.
import com.bayou.views.impl.UserView;
import com.bayou.views.impl.VerifyUserView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
<<<<<<< 6676458cd7013abca7b5bacf195ea74f610b6839
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> Authentication(Login/Verification) update.
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
<<<<<<< 6676458cd7013abca7b5bacf195ea74f610b6839
    @Autowired
    UserManager userManager = new UserManager();
    @Autowired
    UnverifiedUserManager unverifiedUserManager = new UnverifiedUserManager();
=======

    AuthenticationEngine authenticationEngine = new AuthenticationEngine();
>>>>>>> Authentication(Login/Verification) update.

    @ApiOperation(value = "Login as user by email or account name", response = ResponseEntity.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> login(@RequestBody VerifyUserView verifyUserView) throws NotFoundException {
        ResponseEntity<UserView> responseEntity;

        try {
<<<<<<< 6676458cd7013abca7b5bacf195ea74f610b6839
            responseEntity = new ResponseEntity<>(userManager.login(verifyUserView), HttpStatus.OK);
=======
            responseEntity = new ResponseEntity<>(authenticationEngine.login(verifyUserView), HttpStatus.OK);
>>>>>>> Authentication(Login/Verification) update.
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
<<<<<<< 6676458cd7013abca7b5bacf195ea74f610b6839
            responseEntity = new ResponseEntity<>(unverifiedUserManager.verify(verifyUserView), HttpStatus.OK);
=======
            responseEntity = new ResponseEntity<>(authenticationEngine.verify(verifyUserView), HttpStatus.OK);
>>>>>>> Authentication(Login/Verification) update.
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VerificationException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return responseEntity;
    }
}
