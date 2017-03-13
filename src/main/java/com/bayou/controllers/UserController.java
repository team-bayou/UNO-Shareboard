package com.bayou.controllers;

import com.bayou.managers.impl.UserManager;
import com.bayou.views.UserView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.ClientErrorException;

/**
 * Created by joshuaeaton on 1/31/17.
 */
@RestController
@RequestMapping("service/v1/users")
public class UserController {
    @Autowired
    private UserManager manager;

    @ApiOperation(value = "Get a user by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getById(@PathVariable("id") Long id) throws NotFoundException {

        ResponseEntity<UserView> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get a user by account name", response = ResponseEntity.class)
    @RequestMapping(value = "/accountName/{accountName}", method = RequestMethod.GET)
    //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getByAccountName(@PathVariable("accountName") String accountName) throws NotFoundException {

        ResponseEntity<UserView> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(manager.getByAccountName(accountName), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get a user by email", response = ResponseEntity.class)
    @RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> getByEmail(@PathVariable("email") String email) throws NotFoundException {
        ResponseEntity<UserView> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(manager.getByEmail(email), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Add a user", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<Long> add(@RequestBody UserView view) {
        Long id = manager.add(view);

        ResponseEntity<Long> responseEntity;
        if (id > 0) {
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>(id, HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Update a user", response = ResponseEntity.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)   //sets the mapping url and the HTTP method
    public ResponseEntity<Long> update(@RequestBody UserView view) {

        ResponseEntity<Long> responseEntity;
        HttpStatus status;
        Long id = manager.update(view); //update the user, returns -1 if data is stale

        try {
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        } catch (ClientErrorException e) {  //catches the case of non-existent user
            System.out.println("Error: requested user does not exist");
            responseEntity = new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e) {   //catches the case where for example an id is null thus implying a insert
            System.out.println("Error: can not determine if insert or update");
            responseEntity = new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
        }
        if (id == -1L) {    //catches the case if there was an attempt to update outdated information
            System.out.println("Error: stale data detected");
            responseEntity = new ResponseEntity<>(id, HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete a user", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)   //sets the mapping url and the HTTP method
    public ResponseEntity delete(@PathVariable("id") Long id) {
        manager.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}