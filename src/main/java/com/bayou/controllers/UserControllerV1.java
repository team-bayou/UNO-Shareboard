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
@RequestMapping("service/v1/users")
public class UserControllerV1 {

    @Autowired
    UserManager userManager = new UserManager();



    @ApiOperation(value= "Get a user by id" , response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getUserByID(@RequestParam Long user_id ) {

        return new ResponseEntity<>( userManager.getUserByID(user_id), HttpStatus.OK);
    }
/*
    @ApiOperation(value= "Get a user by account_name" , response = ResponseEntity.class)
    @RequestMapping(value = "/{account_name}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getUserByAccountName(@RequestParam String account_name) {

        return new ResponseEntity<>( userManager.getUserByAccountName(account_name), HttpStatus.OK);
    }
*/

    @ApiOperation(value= "Get a user by email" , response = ResponseEntity.class)
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getUserByEmail(@RequestParam String email) {

        return new ResponseEntity<>( userManager.getUserByEmail(email), HttpStatus.OK);
    }

    @ApiOperation(value= "Add a user" , response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> addUser(@RequestBody UserView userView) {

        return new ResponseEntity<>( userManager.addUser(userView), HttpStatus.OK);
    }

}