package com.bayou.managers.impl;

import com.bayou.converters.ReviewConverter;
import com.bayou.converters.UserConverter;
import com.bayou.domains.Review;
import com.bayou.engines.ReviewEngine;
import com.bayou.managers.IManager;
import com.bayou.ras.impl.ReviewResourceAccessor;
import com.bayou.ras.impl.UserResourceAccessor;
import com.bayou.views.ReviewView;
import com.bayou.views.UserView;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * File: ReviewManager
 * Package: com.bayou.managers.impl
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
@Service
public class ReviewManager implements IManager<ReviewView> {
    @Autowired
    private ReviewResourceAccessor reviewRas;

    @Autowired
    private UserResourceAccessor userRas;

    @Autowired
    private ReviewConverter reviewConverter;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ReviewEngine reviewEngine;

    public ReviewView get(Long id) throws NotFoundException {
        ReviewView reviewView;
        Review review = reviewRas.find(id);

        if (review == null) {
            throw new NotFoundException(String.valueOf(id));
        } else {
            reviewView = prepare(review);
        }

        return reviewView;
    }

    @Override
    public List<ReviewView> getAll() throws NotFoundException {
        List<ReviewView> views = new ArrayList<>();

        for (Review ad : reviewRas.findAll())
            views.add(prepare(ad));

        return views;
    }

    public List<ReviewView> getAllByReviewer(Long id) throws NotFoundException {
        List<ReviewView> views = new ArrayList<>();

        for (Review ad : reviewRas.findByReviewer(id))
            views.add(prepare(ad));

        return views;
    }

    public List<ReviewView> getAllByReviewer(Long id, Integer page) throws NotFoundException {
        List<ReviewView> views = new ArrayList<>();

        for (Review ad : reviewRas.findByReviewer(id, page))
            views.add(prepare(ad));

        return views;
    }

    public List<ReviewView> getAllByReviewee(Long id) throws NotFoundException {
        List<ReviewView> views = new ArrayList<>();

        for (Review ad : reviewRas.findByReviewee(id))
            views.add(prepare(ad));

        return views;
    }

    public List<ReviewView> getAllByReviewee(Long id, Integer page) throws NotFoundException {
        List<ReviewView> views = new ArrayList<>();

        for (Review ad : reviewRas.findByReviewee(id, page))
            views.add(prepare(ad));

        return views;
    }

    @Override
    public Long add(ReviewView view) {
        Long id = -1L;
        try {
            id = reviewRas.add(reviewConverter.convertToDomain(view));
        } catch (DataIntegrityViolationException e) {
            System.err.println("Review: " + view.getId() + " already exist");
        }

        return id;
    }

    @Override
    public Long update(ReviewView view) {

        Review review = reviewConverter.convertToDomain(view);    //converts the review view to the review domain Object
        if (view.getId() == null)    //triggers a no content if the id is null
        {
            return -1L;
        }

        Review retrievedReview = reviewRas.find(view.getId());    //get the review we are updating

        if (retrievedReview == null) {    //if the requested review doesn't exist
            throw new javax.ws.rs.NotFoundException();
        }

        review.setVersion(retrievedReview.getVersion());   //gets the record's we are updating version number

        review = reviewConverter.updateConversion(review, retrievedReview); //adds values to any null properties that were not sent in the request on a partial update

        return reviewRas.update(review);
    }

    @Override
    public void delete(Long id) {
        try {
            reviewRas.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("The review with ID:" + id + " does not exist in the database");
        }
    }

    public int getUserAverageRating(Long id) {

        int avgRating = -1;
        try {
          avgRating = reviewEngine.avgUserRating(getAllByReviewee(id)); //pass in list of reviews belong to given user id
        } catch (NotFoundException e) {
            throw new javax.ws.rs.NotFoundException();
        }

        return avgRating;
    }

    private ReviewView prepare(Review ad) {
        ReviewView reviewView = reviewConverter.convertToView(ad);

        UserView reviewerView = userConverter.convertToView(userRas.find(ad.getReviewer()));
        reviewView.setReviewer(reviewerView);

        UserView revieweeView = userConverter.convertToView(userRas.find(ad.getReviewee()));
        reviewView.setReviewee(revieweeView);

        return reviewView;
    }
}
