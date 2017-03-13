package com.bayou.controllers;

import com.bayou.managers.impl.CategoryManager;
import com.bayou.views.CategoryView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * File: CategoryController
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 2/20/17
 */
@RestController
@RequestMapping("service/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryManager manager;

    @ApiOperation(value = "Get a list of categories", response = ResponseEntity.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryView>> getAll() throws NotFoundException {
        ResponseEntity<List<CategoryView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAll(), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get a category by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryView> get(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<CategoryView> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Add a category", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Long> add(@RequestBody CategoryView view) {
        Long id = manager.add(view);

        ResponseEntity<Long> responseEntity;
        if (id > 0)
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        else
            responseEntity = new ResponseEntity<>(id, HttpStatus.CONFLICT);

        return responseEntity;
    }

    @ApiOperation(value = "Update a category", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody CategoryView view) {
        manager.update(view);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete a category", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        manager.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}