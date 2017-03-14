package com.bayou.repository;

import com.bayou.domains.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * File: IReviewRepository
 * Package: com.bayou.repository
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
@Repository
public interface IReviewRepository extends CrudRepository<Review, Long> {
    Iterable<Review> findByReviewer(Long id);

    Iterable<Review> findByReviewee(Long id);
}
