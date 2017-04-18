package com.bayou.converters;

import com.bayou.domains.Review;
import com.bayou.utils.DomainMocks;
import com.bayou.utils.ViewMocks;
import com.bayou.views.ReviewView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * File: ReviewConverterTests
 * Package: com.bayou.converters
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReviewConverterTests {
    @Autowired
    ReviewConverter converter;

    @Test
    public void testConvertToView() {
        Review domain = DomainMocks.createReview();

        ReviewView view = converter.convertToView(domain);

        assertEquals(domain.getId(), view.getId());
        assertEquals(domain.getRating(), view.getRating());
        assertEquals(domain.getComments(), view.getComments());
        assertEquals(domain.getReviewer(), view.getReviewerId());
        assertEquals(domain.getReviewee(), view.getRevieweeId());
    }

    @Test
    public void testConvertToDomain() {
        ReviewView view = ViewMocks.createReview();

        Review domain = converter.convertToDomain(view);

        assertEquals(view.getId(), domain.getId());
        assertEquals(view.getRating(), domain.getRating());
        assertEquals(view.getComments(), domain.getComments());
        assertEquals(view.getReviewerId(), domain.getReviewer());
        assertEquals(view.getRevieweeId(), domain.getReviewee());
    }
}
