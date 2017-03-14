package com.bayou.managers.impl;

import com.bayou.converters.ReviewConverter;
import com.bayou.converters.UserConverter;
import com.bayou.domains.Review;
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

    public List<ReviewView> getAllByReviewee(Long id) throws NotFoundException {
        List<ReviewView> views = new ArrayList<>();

        for (Review ad : reviewRas.findByReviewee(id))
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
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            reviewRas.delete(id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("The review with ID:" + id + " does not exist in the database");
        }
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
