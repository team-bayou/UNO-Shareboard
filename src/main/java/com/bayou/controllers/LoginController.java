package com.bayou.controllers;

import com.bayou.managers.impl.UserManager;
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
@RequestMapping("service/v1")
public class LoginController {
    @Autowired
    UserManager userManager = new UserManager();

    @ApiOperation(value = "Login as user by email or account name", response = ResponseEntity.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> login(@RequestBody UserView userView) {

        return new ResponseEntity<>(userManager.login(userView), HttpStatus.OK);
    }
}