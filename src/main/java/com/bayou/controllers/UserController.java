package com.bayou.controllers;

import com.bayou.managers.impl.UserManager;
import com.bayou.views.impl.UserView;
import io.swagger.annotations.ApiOperation;
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
    public ResponseEntity<UserView> getById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(userManager.get(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a user", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UserView> add(@RequestBody UserView userView) {


           ResponseEntity<UserView> responseEntity = new ResponseEntity<UserView>(userManager.add(userView), HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Delete a user", response = ResponseEntity.class)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)   //sets the mapping url and the HTTP method
    public ResponseEntity delete(@PathVariable("id") Long id) {
            try {
                userManager.delete(id);
            }
            catch (EmptyResultDataAccessException e) {
                System.out.println("The user with ID:" + id + " does not exist in the database ");
            }
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}