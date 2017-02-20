package com.bayou.controllers;

import com.bayou.managers.impl.AdvertisementManager;
import com.bayou.views.impl.AdvertisementView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * File: AdvertisementController
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@RestController
@RequestMapping("service/v1/advertisements")
public class AdvertisementController {
    @Autowired
    AdvertisementManager manager = new AdvertisementManager();

    @ApiOperation(value = "Get a list of advertisements", response = ResponseEntity.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertisementView>> getAll() throws NotFoundException {
        ResponseEntity<List<AdvertisementView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAll(), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get an advertisement by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AdvertisementView> getById(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<AdvertisementView> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(manager.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Add an advertisement", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody AdvertisementView advertisementView) {
        return new ResponseEntity(manager.add(advertisementView));
    }

    @ApiOperation(value = "Delete an advertisement", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        manager.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}