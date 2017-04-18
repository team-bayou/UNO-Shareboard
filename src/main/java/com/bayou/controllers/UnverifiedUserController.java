package com.bayou.controllers;

import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.views.UnverifiedUserView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;

/**
 * Created by rachelguillory on 2/16/2017.
 */
@RestController
@RequestMapping("service/v1/unverified_users")
public class UnverifiedUserController {
    @Autowired
    private UnverifiedUserManager manager;

    @ApiOperation(value = "Get an unverified user by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UnverifiedUserView> getById(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<UnverifiedUserView> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get an unverified user by email", response = ResponseEntity.class)
    @RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET)
    public ResponseEntity<UnverifiedUserView> getByEmail(@PathVariable("email") String email) throws NotFoundException {
        ResponseEntity<UnverifiedUserView> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getByEmail(email), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Add an unverified user", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<Long> add(@RequestBody UnverifiedUserView userView) {
        Long id = manager.add(userView);

        ResponseEntity<Long> responseEntity;
        if (id > 0)
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        else
            responseEntity = new ResponseEntity<>(id, HttpStatus.CONFLICT);

        return responseEntity;
    }

    @ApiOperation(value = "Delete an unverified user", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)   //sets the mapping url and the HTTP method
    public ResponseEntity delete(@PathVariable("id") Long id) {
        manager.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}