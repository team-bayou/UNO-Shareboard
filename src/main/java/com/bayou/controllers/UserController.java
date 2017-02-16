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
public class UserController {
    @Autowired
    UserManager userManager = new UserManager();

    @ApiOperation(value = "Get a user by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(userManager.getById(id), HttpStatus.OK);
    }

/*    @ApiOperation(value= "Get a user by account_name" , response = ResponseEntity.class)
    @RequestMapping(value = "/{account_name}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getByAccountName(@PathVariable String accountName) {

        return new ResponseEntity<>( userManager.getByAccountName(accountName), HttpStatus.OK);
    }*/

    @ApiOperation(value = "Get a user by email", response = ResponseEntity.class)
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getByEmail(@PathVariable("email") String email) {

        return new ResponseEntity<>(userManager.getByEmail(email), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a user", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> add(@RequestBody UserView userView) {

        return new ResponseEntity<>(userManager.add(userView), HttpStatus.OK);
    }

}