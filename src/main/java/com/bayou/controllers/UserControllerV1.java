package com.bayou.controllers;

import com.bayou.managers.impl.UserManager;
import com.bayou.views.impl.UserView;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("service/v1/users")
public class UserControllerV1 {

    @Autowired
    UserManager userManager = new UserManager();

    @ApiOperation(value= "Add a user" , response = ResponseEntity.class)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> addUser(@RequestBody UserView userView) {

        return new ResponseEntity<>( userManager.addUser(userView), HttpStatus.OK);
    }
}