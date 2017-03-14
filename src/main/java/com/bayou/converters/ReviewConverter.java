package com.bayou.converters;

import com.bayou.domains.Review;
import com.bayou.views.ReviewView;
import org.springframework.stereotype.Component;

/**
 * File: ReviewConverter
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
@Component("ReviewConverter")
public class ReviewConverter {
    public ReviewView convertToView(Review domain) {
        ReviewView view = new ReviewView();
        view.setId(domain.getId());
        view.setRating(domain.getRating());
        view.setComments(domain.getComments());
        view.setReviewerId(domain.getReviewer());
        view.setRevieweeId(domain.getReviewee());

        return view;
    }

    public Review convertToDomain(ReviewView view) {
        Review domain = new Review();
        domain.setId(view.getId());
        domain.setRating(view.getRating());
        domain.setComments(view.getComments());
        domain.setReviewer(view.getReviewerId());
        domain.setReviewee(view.getRevieweeId());

        return domain;
    }
}

