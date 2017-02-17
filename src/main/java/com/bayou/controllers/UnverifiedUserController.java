package com.bayou.controllers;

import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.views.impl.UnverifiedUserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by rachelguillory on 2/16/2017.
 */
@RestController
@RequestMapping("service/v1/unverified_users")
public class UnverifiedUserController {
    @Autowired
    UnverifiedUserManager userManager = new UnverifiedUserManager();

    @ApiOperation(value = "Get an unverified user by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UnverifiedUserView> getById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(userManager.getById(id), HttpStatus.OK);
    }

/*    @ApiOperation(value= "Get an unverified user by account_name" , response = ResponseEntity.class)
    @RequestMapping(value = "/{account_name}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UnverifiedUserView> getByAccountName(@PathVariable String accountName) {

        return new ResponseEntity<>( userManager.getByAccountName(accountName), HttpStatus.OK);
    }*/

    @ApiOperation(value = "Get an unverified user by email", response = ResponseEntity.class)
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UnverifiedUserView> getByEmail(@PathVariable("email") String email) {

        return new ResponseEntity<>(userManager.getByEmail(email), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a user", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UnverifiedUserView> add(@RequestBody UnverifiedUserView userView) {

        return new ResponseEntity<>(userManager.add(userView), HttpStatus.OK);
    }

}