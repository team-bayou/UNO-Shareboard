package com.bayou.controllers;

import com.bayou.managers.impl.ReviewManager;
import com.bayou.views.ReviewView;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * File: ReviewController
 * Package: com.bayou.controllers
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
@RestController
@RequestMapping("service/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewManager manager;

    @ApiOperation(value = "Get a list of reviews", response = ResponseEntity.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewView>> getAll() throws NotFoundException {
        ResponseEntity<List<ReviewView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAll(), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get an review by id", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReviewView> get(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<ReviewView> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.get(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get a list of user's given reviews", response = ResponseEntity.class)
    @RequestMapping(value = "/reviewer/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewView>> getReviewerReviews(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<List<ReviewView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAllByReviewer(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;

    }

    @ApiOperation(value = "Get a list of user's given reviews by page number", response = ResponseEntity.class)
    @RequestMapping(value = "/reviewer/{id}/page/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewView>> getReviewerReviews(@PathVariable("id") Long id,
                                                               @PathVariable("page") Integer page) throws NotFoundException {
        ResponseEntity<List<ReviewView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAllByReviewer(id, page), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;

    }

    @ApiOperation(value = "Get a list of user's received reviews", response = ResponseEntity.class)
    @RequestMapping(value = "/reviewee/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewView>> getRevieweeReviews(@PathVariable("id") Long id) throws NotFoundException {
        ResponseEntity<List<ReviewView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAllByReviewee(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get a list of user's received reviews by page number", response = ResponseEntity.class)
    @RequestMapping(value = "/reviewee/{id}/page/{page}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewView>> getRevieweeReviews(@PathVariable("id") Long id,
                                                               @PathVariable("page") Integer page) throws NotFoundException {
        ResponseEntity<List<ReviewView>> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(manager.getAllByReviewee(id, page), HttpStatus.OK);
        } catch (NotFoundException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Get average star rating for a given user id", response = ResponseEntity.class)
    @RequestMapping(value = "/reviewee/{id}/rating", method = RequestMethod.GET)
    public ResponseEntity getUserAverageRating(@PathVariable("id") Long id) {

        ResponseEntity<Integer> responseEntity;
        try {
            int avgRating = manager.getUserAverageRating(id);

            if(avgRating != 0) {
                responseEntity = new ResponseEntity<>(avgRating, HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (javax.ws.rs.NotFoundException e) {
            System.out.println("Error: user with given id does not exist");
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (java.lang.ArithmeticException ae) {
            System.out.println("No reviews found for this user");
            responseEntity = new ResponseEntity<>(BAD_REQUEST);
        }

        return responseEntity;
    }

    @ApiOperation(value = "Add a review", response = ResponseEntity.class)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Long> add(@RequestBody ReviewView view) {
        Long id = manager.add(view);

        ResponseEntity<Long> responseEntity;
        if (id > 0)
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        else
            responseEntity = new ResponseEntity<>(id, HttpStatus.CONFLICT);

        return responseEntity;
    }

    @ApiOperation(value = "Update a review", response = ResponseEntity.class)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody ReviewView view) {

        ResponseEntity<Long> responseEntity;
        Long id = -1L;

        try {
            id = manager.update(view); //update the review, returns -1 if data is stale
            responseEntity = new ResponseEntity<>(id, HttpStatus.OK);
        } catch (javax.ws.rs.NotFoundException e) {  //catches the case of non-existent review
            System.out.println("Error: requested review does not exist");
            responseEntity = new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e) {   //catches the case where for example an id is null thus implying a insert
            System.out.println("Error: can not determine if insert or update");
            responseEntity = new ResponseEntity<>(id, BAD_REQUEST);
        }
        if (id == -1L) {    //catches the case if there was an attempt to update outdated information
            System.out.println("Error: stale data detected");
            responseEntity = new ResponseEntity<>(id, HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete a review", response = ResponseEntity.class)
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        manager.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}