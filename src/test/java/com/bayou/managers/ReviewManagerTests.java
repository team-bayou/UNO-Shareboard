package com.bayou.managers;

import com.bayou.exceptions.ValidationException;
import com.bayou.managers.impl.ReviewManager;
import com.bayou.managers.impl.UserManager;
import com.bayou.utils.ViewMocks;
import com.bayou.views.ReviewView;
import com.bayou.views.UserView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.NotFoundException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * File: ReviewManagerTests
 * Package: com.bayou.managers
 * Author: Stefan Haselwanter
 * Created on: 4/17/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReviewManagerTests {
    private static final int PAGE_NUMBER = 1;

    @Autowired
    private UserManager userManager;
    @Autowired
    private ReviewManager reviewManager;

    private UserView reviewerView;
    private UserView revieweeView;
    private ReviewView reviewView;

    @Before
    public void setup() throws ValidationException {
        // Create user view and add user to db.
        reviewerView = ViewMocks.createUser();
        Long id = userManager.add(reviewerView);
        reviewerView.setId(id);

        // Create user view and add user to db.
        revieweeView = ViewMocks.createUser();
        id = userManager.add(revieweeView);
        revieweeView.setId(id);

        // Create review view and add review to db.
        reviewView = ViewMocks.createReview();
        reviewView.setReviewerId(reviewerView.getId());
        reviewView.setRevieweeId(revieweeView.getId());
        id = reviewManager.add(reviewView);
        reviewView.setId(id);
    }

    @After
    public void cleanup() {
        // Delete test data.
        reviewManager.delete(reviewView.getId());
        userManager.delete(reviewerView.getId());
        userManager.delete(revieweeView.getId());
    }

    @Test
    public void testGetReviews() {
        List<ReviewView> views = null;

        // Get list of reviews.
        try {
            views = reviewManager.getAll();
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetReviewById() {
        ReviewView view = null;

        // Get review by id.
        try {
            view = reviewManager.get(reviewView.getId());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(view != null);
    }

    @Test
    public void testGetReviewsByReviewer() {
        List<ReviewView> views = null;

        // Get list of reviewer's reviews.
        try {
            views = reviewManager.getAllByReviewer(reviewerView.getId());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetReviewsByReviewerByPage() {
        List<ReviewView> views = null;

        // Get list of reviewer's reviews by page number.
        try {
            views = reviewManager.getAllByReviewer(reviewerView.getId(), PAGE_NUMBER);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetReviewsByReviewee() {
        List<ReviewView> views = null;

        // Get list of reviewee's reviews.
        try {
            views = reviewManager.getAllByReviewee(revieweeView.getId());
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testGetReviewsByRevieweeByPage() {
        List<ReviewView> views = null;

        // Get list of reviewee's reviews by page number.
        try {
            views = reviewManager.getAllByReviewee(revieweeView.getId(), PAGE_NUMBER);
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        }

        assertTrue(views != null && views.size() > 0);
    }

    @Test
    public void testAddReview() {
        // Create review view and add review to db.
        ReviewView reviewView = ViewMocks.createReview();
        reviewView.setReviewerId(reviewerView.getId());
        reviewView.setRevieweeId(revieweeView.getId());

        Long id = reviewManager.add(reviewView);
        reviewView.setId(id);

        assertTrue(id != null && id > 0);

        // Delete test data.
        reviewManager.delete(reviewView.getId());
    }

    @Test
    public void testUpdateReview() {
        // Update some information of review and save it to db.
        reviewView.setComments(reviewView.getComments() + " updated");
        Long id = reviewManager.update(reviewView);

        assertEquals(reviewView.getId(), id);
    }

    @Test
    public void testDeleteReview() {
        // Create review view and add review to db.
        ReviewView reviewView = ViewMocks.createReview();
        reviewView.setReviewerId(reviewerView.getId());
        reviewView.setRevieweeId(revieweeView.getId());

        Long id = reviewManager.add(reviewView);
        reviewView.setId(id);

        assertTrue(id != null && id > 0);

        // Delete review by id.
        reviewManager.delete(reviewView.getId());

        try {
            reviewManager.get(reviewView.getId());
            fail();
        } catch (NotFoundException e) {
            // Test passed.
        }
    }
}
