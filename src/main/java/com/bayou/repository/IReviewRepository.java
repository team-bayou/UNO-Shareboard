package com.bayou.repository;

import com.bayou.domains.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * File: IReviewRepository
 * Package: com.bayou.repository
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
@Repository
public interface IReviewRepository extends PagingAndSortingRepository<Review, Long> {
    Iterable<Review> findByReviewer(Long id);

    Page<Review> findByReviewer(Long id, Pageable pageable);

    Iterable<Review> findByReviewee(Long id);

    Page<Review> findByReviewee(Long id, Pageable pageable);
}
