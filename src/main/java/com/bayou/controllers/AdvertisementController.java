package com.bayou.controllers;

import com.bayou.managers.impl.UserManager;
import com.bayou.views.impl.UserView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public ResponseEntity<UserView> getById(@PathVariable("id") Long id) throws NotFoundException {

        ResponseEntity<UserView> responseEntity = null;
        try {responseEntity = new ResponseEntity<>(userManager.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get a user by account name", response = ResponseEntity.class)
    @RequestMapping(value = "/accountName/{accountName}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getByAccountName(@PathVariable("accountName") String accountName) throws NotFoundException {

        ResponseEntity<UserView> responseEntity = null;
        try {responseEntity = new ResponseEntity<>(userManager.getByAccountName(accountName), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get a user by email", response = ResponseEntity.class)
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getByEmail(@PathVariable("email") String email) throws NotFoundException {

        ResponseEntity<UserView> responseEntity = null;
        try {responseEntity = new ResponseEntity<>(userManager.getByEmail(email), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Add a user", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity add(@RequestBody UserView userView) {

        return new ResponseEntity(userManager.add(userView));
    }

    @ApiOperation(value = "Delete a user", response = ResponseEntity.class)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)   //sets the mapping url and the HTTP method
    public ResponseEntity delete(@PathVariable("id") Long id) {

        userManager.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}