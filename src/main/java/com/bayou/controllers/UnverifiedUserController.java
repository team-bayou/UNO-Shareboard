package com.bayou.controllers;

import com.bayou.domains.UnverifiedUser;
import com.bayou.managers.impl.UnverifiedUserManager;
import com.bayou.views.impl.UnverifiedUserView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
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
    public ResponseEntity<UnverifiedUserView> getById(@PathVariable("id") Long id) throws NotFoundException {

        ResponseEntity<UnverifiedUserView> responseEntity = null;
        try { responseEntity = new ResponseEntity<>(userManager.getById(id), HttpStatus.OK);}
        catch (NotFoundException e){
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get an unverified user by email", response = ResponseEntity.class)
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)   //sets the mapping url and the HTTP method
    public ResponseEntity<UnverifiedUserView> getByEmail(@PathVariable("email") String email) throws NotFoundException {

        ResponseEntity<UnverifiedUserView> responseEntity = null;
        try { responseEntity = new ResponseEntity<>(userManager.getByEmail(email), HttpStatus.OK);}
        catch (NotFoundException e){
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Add a user", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)   //sets the mapping url and the HTTP method
    public ResponseEntity<UnverifiedUserView> add(@RequestBody UnverifiedUserView userView) {

        userManager.add(userView);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}