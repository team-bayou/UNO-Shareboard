package com.bayou.ras.impl;

import com.bayou.domains.Review;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * File: ReviewResourceAccessor
 * Package: com.bayou.ras.impl
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
@Service
public class ReviewResourceAccessor implements IResourceAccessor<Review> {
    @Autowired
    IReviewRepository repo;

    @Override
    public Review find(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Iterable<Review> findAll() {
        return repo.findAll();
    }

    public Iterable<Review> findByReviewer(Long id) {
        return repo.findByReviewer(id);
    }

    public Iterable<Review> findByReviewee(Long id) {
        return repo.findByReviewee(id);
    }

    @Override
    public Long add(Review entity) {
        return repo.save(entity).getId();
    }

    //TODO:implement
    @Override
    public Long update(Review entity) {
        return null;
    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }
}
