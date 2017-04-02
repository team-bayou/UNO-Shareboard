package com.bayou.ras.impl;

import com.bayou.domains.Review;
import com.bayou.ras.IResourceAccessor;
import com.bayou.repository.IReviewRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

/**
 * File: ReviewResourceAccessor
 * Package: com.bayou.ras.impl
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
@Service
public class ReviewResourceAccessor implements IResourceAccessor<Review> {
    private static final int MAX_RESULTS = 10;

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

    public Iterable<Review> findAll(Integer page) {
        return repo.findAll(pageAndSortByIdDesc(page));
    }

    public Iterable<Review> findByReviewer(Long id) {
        return repo.findByReviewer(id);
    }

    public Iterable<Review> findByReviewer(Long id, Integer page) {
        return repo.findByReviewer(id, pageAndSortByIdDesc(page));
    }

    public Iterable<Review> findByReviewee(Long id) {
        return repo.findByReviewee(id);
    }

    public Iterable<Review> findByReviewee(Long id, Integer page) {
        return repo.findByReviewee(id, pageAndSortByIdDesc(page));
    }

    @Override
    public Long add(Review entity) {
        return repo.save(entity).getId();
    }

    @Override
    public Long update(Review entity) {

        Long returnedID = -1L;
        if (entity.getId() == null) {    //handles the case of a null id being given for a update
            return -1L;
        }
        try {
            entity = repo.save(entity);
            returnedID = entity.getId();
        }   //below catches the exceptions, need not do anything as returnedID will now stay -1L which is the flag for stale data
        catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
            // Ignore
        }

        return returnedID;

    }

    @Override
    public void delete(Long id) {
        repo.delete(id);
    }

    private PageRequest pageAndSortByIdDesc(Integer page) {
        return new PageRequest(page - 1, MAX_RESULTS,
                new Sort(Sort.Direction.DESC, "id"));
    }
}
